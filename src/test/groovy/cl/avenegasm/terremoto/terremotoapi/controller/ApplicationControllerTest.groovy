package cl.avenegasm.terremoto.terremotoapi.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest
import org.springframework.context.annotation.Import
import org.springframework.test.web.servlet.MockMvc
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders
import org.springframework.test.web.servlet.result.MockMvcResultMatchers
import spock.lang.*

@Title("Especificacion de Controlador")
@Narrative("En esta clase se especifican los test unitarios relacionados al controlador que checkea la salud del aplicativo owo")
@AutoConfigureMockMvc
@WebMvcTest()
@Import([TestMockingConfig])
class ApplicationControllerTest extends Specification {
    @Autowired
    private MockMvc mvc

    def "Cuando es llamado el endpoint de salud este responde un status 200"() {
        expect: "El response entity del controlador responde un httpd code 200"
        mvc.perform(MockMvcRequestBuilders.get("/health")).
                andExpect(MockMvcResultMatchers.status().isOk())
    }

}