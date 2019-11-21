package cl.avenegasm.terremoto.terremotoapi.service;

import cl.avenegasm.terremoto.terremotoapi.dto.EarthquakeResponseDto;

import java.util.Date;

public interface IEarthquakeService {

    EarthquakeResponseDto getByRangoFecha(Date inicio,Date fin);
    EarthquakeResponseDto getByMagnitudRange(Double inicio,Double fin);
    EarthquakeResponseDto getByRangoFecha(Date inicio1,Date fin1,Date inicio2,Date fin2);
    EarthquakeResponseDto getByPais(String pais);
}
