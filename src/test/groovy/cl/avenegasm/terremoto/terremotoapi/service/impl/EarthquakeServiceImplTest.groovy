package cl.avenegasm.terremoto.terremotoapi.service.impl

import cl.avenegasm.terremoto.terremotoapi.dto.EarthquakeResponseDto
import org.springframework.context.annotation.Import
import org.springframework.test.util.ReflectionTestUtils
import spock.lang.*

class EarthquakeServiceImplTest extends Specification {

    EarthquakeServiceImpl earthquakeServiceImpl

    def setup(){
        earthquakeServiceImpl = new EarthquakeServiceImpl()
        ReflectionTestUtils.setField(earthquakeServiceImpl, "endpointEarthquakeApi",
                "https://earthquake.usgs.gov/fdsnws/event/1/query?format=geojson");
        return earthquakeServiceImpl
    }


    def "test de endpoint que consulta por Rango Fecha"() {
        when:
        EarthquakeResponseDto result = earthquakeServiceImpl.getByRangoFecha(new GregorianCalendar(2019, Calendar.NOVEMBER, 20, 23, 47).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 21, 23, 47).getTime())

        then:
        result.getFeatures().size() == 272
    }

    def "test de endpoint que consulta Magnitud"() {
        when:
        EarthquakeResponseDto result = earthquakeServiceImpl.getByMagnitudRange(7, 8)

        then:
        result.getFeatures().size() == 1
    }

    def "test de endpoint que consulta por dos fechas"() {
        when:
        EarthquakeResponseDto result = earthquakeServiceImpl.getByRangoFecha(new GregorianCalendar(2019, Calendar.NOVEMBER, 20, 23, 47).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 21, 23, 47).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 25, 23, 47).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 26, 23, 47).getTime())

        then:
        result.getFeatures().size() < 600 && result.getFeatures().size() > 500
    }

    def "test de endpoint que consulta por Pais"() {
        when:
        EarthquakeResponseDto result = earthquakeServiceImpl.getByPais("Ecuador")

        then:
        result.getFeatures().size() == 3
    }

    def "test de endpoint que consulta pos dos paises y un rango de fechas"() {
        when:
        EarthquakeResponseDto result = earthquakeServiceImpl.getByPaisFechas("Chile", "Argentina", new GregorianCalendar(2019, Calendar.NOVEMBER, 20, 23, 47).getTime(), new GregorianCalendar(2019, Calendar.NOVEMBER, 26, 23, 47).getTime())

        then:
        result.getFeatures().size() == 6
    }
}