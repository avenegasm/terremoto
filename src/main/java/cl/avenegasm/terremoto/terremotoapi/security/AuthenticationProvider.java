package cl.avenegasm.terremoto.terremotoapi.security;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/**
 * @author Alejandro Venegas
 * @version 1.0.0
 * @since 1.0.0
 */
@Component
public class AuthenticationProvider implements org.springframework.security.authentication.AuthenticationProvider {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationProvider.class);

    @Value("${terremoto.allowed-key}")
    private String key;

    /**
     * Autenticador con un token permitido
     * @param authentication
     * @return
     * @throws AuthenticationException
     */
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LOGGER.debug("AuthenticationProvider::authenticate ->" + authentication);
        Object credentials  = authentication.getCredentials();
        SessionUser sesionUsuario = null;
        //Login con Token
        if(credentials.getClass().equals(String.class)){
             String token = (String) credentials;
             if(!token.equals(key))
                 throw new BadCredentialsException("Token no valido");
             else
                 sesionUsuario = new SessionUser("Data");
        }
        return new UsernamePasswordAuthenticationToken(sesionUsuario,null);
    }

    /**
     *
     * @param aClass
     * @return
     */
    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(UsernamePasswordAuthenticationToken.class);
    }
}
