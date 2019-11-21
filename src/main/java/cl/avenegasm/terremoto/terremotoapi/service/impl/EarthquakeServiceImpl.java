package cl.avenegasm.terremoto.terremotoapi.service.impl;

import cl.avenegasm.terremoto.terremotoapi.dto.EarthquakeResponseDto;
import cl.avenegasm.terremoto.terremotoapi.dto.FeatureDto;
import cl.avenegasm.terremoto.terremotoapi.exception.ExternalApiException;
import cl.avenegasm.terremoto.terremotoapi.service.IEarthquakeService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Alejandro Venegas
 * @version 1.0.0
 * @since 1.0.0
 */
@Service
public class EarthquakeServiceImpl implements IEarthquakeService {

    private static final Logger LOGGER = LoggerFactory.getLogger(EarthquakeServiceImpl.class);
    private static final String API_ERROR = "Ocurrio un problema al consultar listado de sismos";
    private static final String date_pattern = "yyyy-MM-dd";

    @Value("${terremoto.url}")
    private String endpointEarthquakeApi;

    /**
     * Implementacion del servicio que consulta listado de sismos dentro de un rango de fechas.
     * @param inicio
     * @param fin
     * @return objeto con listado de sismos.
     */
    @Override
    public EarthquakeResponseDto getByRangoFecha(Date inicio, Date fin) {
        String desde = new SimpleDateFormat(date_pattern).format(inicio);
        String hasta = new SimpleDateFormat(date_pattern).format(fin);
        LOGGER.info("Consultando listado de sismos por rango de fechas :{},{}",desde,hasta);
        final String uri = endpointEarthquakeApi +"&starttime="+desde+"&endtime="+hasta;
        return callApiEarthquake(uri);
    }

    @Override
    public EarthquakeResponseDto getByMagnitudRange(Double inicio, Double fin) {
        LOGGER.info("Consultando listado de sismos por rango de magnitudes :{},{}",inicio,fin);
        final String uri = endpointEarthquakeApi +"&minmagnitude="+inicio+"&maxmagnitude="+fin;
        return callApiEarthquake(uri);
    }

    /**
     * Servicio que permite obtener listado de sismos en dos rangos de fechas,
     * se invoca a la api dos veces, ya que no se conoce otro endpoint que agrupe rangos de fechas.
     * @param inicio1
     * @param fin1
     * @param inicio2
     * @param fin2
     * @return
     */
    @Override
    public EarthquakeResponseDto getByRangoFecha(Date inicio1, Date fin1, Date inicio2, Date fin2) {
        List<FeatureDto> featureDtos = getByRangoFecha(inicio1,fin1).getFeatures();
        featureDtos.addAll(getByRangoFecha(inicio2,fin2).getFeatures());
        return new EarthquakeResponseDto(featureDtos);
    }

    @Override
    public EarthquakeResponseDto getByPais(String pais) {
        EarthquakeResponseDto dataCompleta = this.callApiEarthquake(endpointEarthquakeApi+"&minmagnitude=2.0");
        List<FeatureDto> filtrados= this.filtrarPorPais(dataCompleta.getFeatures(),pais);
        LOGGER.info("Se han encontrado {} sismos filtrados para el pais {}",filtrados.size(),pais);
        return new EarthquakeResponseDto(filtrados);
    }

    /**
     * Metodo que permite filtrar un listado de sismos por un pais dado
     * @param sismos
     * @param pais
     * @return
     */
    private  List<FeatureDto> filtrarPorPais(List<FeatureDto> sismos,String pais){
       List<FeatureDto> filtrados= new ArrayList<>();
       sismos.forEach(featureDto -> {
           String paisCortado = (featureDto.getProperties().getPlace().contains(",")) ? featureDto.getProperties().getPlace().substring(
                   featureDto.getProperties().getPlace().lastIndexOf(",")+1).trim() : null;
           if(pais.equalsIgnoreCase(paisCortado)){
               filtrados.add(featureDto);
           }
       });
       return filtrados;
    }

    private EarthquakeResponseDto callApiEarthquake(String url){
        try {
            LOGGER.info("Consumiendo API , url :{}",url);
            RestTemplate restTemplate = new RestTemplate();
            EarthquakeResponseDto result= restTemplate.getForObject(url, EarthquakeResponseDto.class);
            if(result.getFeatures() == null || result.getFeatures().isEmpty()){
                LOGGER.warn("Consulta exitosa, pero no se han encontrado registros");
            }else {
                LOGGER.info("Consulta exitosa, se han encontrado :{} sismos", result.getFeatures().size());
            }
            return result;
        }catch (RestClientException ex){
            LOGGER.error(API_ERROR,ex);
            throw new ExternalApiException(API_ERROR);
        }
    }
}
