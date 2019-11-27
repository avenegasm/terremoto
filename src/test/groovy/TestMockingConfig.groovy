import cl.avenegasm.terremoto.terremotoapi.security.AuthenticationEntrypoint
import cl.avenegasm.terremoto.terremotoapi.security.AuthenticationProvider
import cl.avenegasm.terremoto.terremotoapi.security.JwtUtil
import cl.avenegasm.terremoto.terremotoapi.service.IEarthquakeService
import org.springframework.boot.test.context.TestConfiguration
import org.springframework.context.annotation.Bean
import spock.mock.DetachedMockFactory

@TestConfiguration
class TestMockingConfig {
    private DetachedMockFactory factory = new DetachedMockFactory()

    @Bean
    AuthenticationProvider authenticationProvider(){
        factory.Mock(AuthenticationProvider)
    }

    @Bean
    JwtUtil jwtUtil(){
        factory.Mock(JwtUtil)
    }
    @Bean
    AuthenticationEntrypoint externalRankingService() {
        factory.Mock(AuthenticationEntrypoint)
    }

    @Bean
    IEarthquakeService earthquakeService(){
        factory.Mock(IEarthquakeService)
    }
}