package cl.avenegasm.terremoto.terremotoapi.controller;

import cl.avenegasm.terremoto.terremotoapi.dto.EarthquakeResponseDto;
import cl.avenegasm.terremoto.terremotoapi.service.IEarthquakeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.Date;

/**
 * @author Alejandro Venegas
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/terremoto")
public class TerremotoController {

    @Autowired
    IEarthquakeService earthquakeService;

    /**
     * Rest de acceso restringido,permite consultar los sismos dentro de un rango de dos fechas.
     * @return objeto de transferencia con todos los sismos
     */
    @GetMapping("/fecha")
    public EarthquakeResponseDto fechas(@RequestParam @Valid @DateTimeFormat(pattern="yyyy-MM-dd") Date inicio,
                                        @RequestParam @Valid @DateTimeFormat(pattern="yyyy-MM-dd") Date fin){
      return earthquakeService.getByRangoFecha(inicio,fin);
    }

    /**
     * Rest de acceso restringido, permite consultar los sismos dentro de un rango de magnitudes.
     * @return objeto de transferencia con todos los sismos
     */
    @GetMapping("/magnitud")
    public EarthquakeResponseDto magnitudes(@RequestParam @Valid Double inicio,
                                            @RequestParam @Valid Double fin){
        return earthquakeService.getByMagnitudRange(inicio,fin);
    }

    /**
     * Rest que permite consultar los sismos dentro de un rango de cuatro fechas, en dos rangos.
     * @return objeto de transferencia con todos los sismos
     */
    @GetMapping("/fechas")
    public EarthquakeResponseDto fechas(@RequestParam @Valid @DateTimeFormat(pattern="yyyy-MM-dd") Date inicio1,
                                        @RequestParam @Valid @DateTimeFormat(pattern="yyyy-MM-dd") Date fin1,
                                        @RequestParam @Valid @DateTimeFormat(pattern="yyyy-MM-dd") Date inicio2,
                                        @RequestParam @Valid @DateTimeFormat(pattern="yyyy-MM-dd") Date fin2){
        return earthquakeService.getByRangoFecha(inicio1,fin1,inicio2,fin2);
    }

    /**
     * Rest que permite obtener listado de sismos por determinado pais
     * @return objeto de transferencia con todos los sismos
     */
    @GetMapping("/pais")
    public EarthquakeResponseDto pais(@RequestParam @Valid String pais){
        return earthquakeService.getByPais(pais);
    }
}
