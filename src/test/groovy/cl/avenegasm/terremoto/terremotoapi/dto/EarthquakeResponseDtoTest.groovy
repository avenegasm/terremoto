package cl.avenegasm.terremoto.terremotoapi.dto

import spock.lang.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import static org.mockito.Mockito.*

class EarthquakeResponseDtoTest extends Specification {
    @Mock
    List<FeatureDto> features
    @InjectMocks
    EarthquakeResponseDto earthquakeResponseDto

    def setup() {
        MockitoAnnotations.initMocks(this)
        FeatureDto mock= new FeatureDto()
        mock.setType("TipoMock")
        mock.setProperties(null)
        when(earthquakeResponseDto.getFeatures().get(0)).thenReturn(mock);

    }

    def "test constructor all args con mock"() {
        when:
        earthquakeResponseDto = new EarthquakeResponseDto(features)

        then:
        earthquakeResponseDto.getFeatures().get(0).getType() == "TipoMock"
    }


}