package cl.avenegasm.terremoto.terremotoapi.dto

import spock.lang.*
import org.mockito.InjectMocks
import org.mockito.Mock
import org.mockito.MockitoAnnotations
import static org.mockito.Mockito.*

class FeatureDtoTest extends Specification {
    @Mock
    PropertieDto properties
    @InjectMocks
    FeatureDto featureDto

    def setup() {
        MockitoAnnotations.initMocks(this)
        when(properties.getAlert()).thenReturn("Alerta roja")
    }

    def "test set Type"() {
        when:
        featureDto.setType("type")

        then:
        featureDto.getType() == "type"
    }

    def "test set Properties"() {
        when:
        featureDto.setProperties(properties)

        then:
        featureDto.getProperties().getAlert() == "Alerta roja"
    }
}