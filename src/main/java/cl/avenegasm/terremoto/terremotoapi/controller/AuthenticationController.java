package cl.avenegasm.terremoto.terremotoapi.controller;

import cl.avenegasm.terremoto.terremotoapi.security.JwtUtil;
import cl.avenegasm.terremoto.terremotoapi.security.SessionUser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

/**
 * @author Alejandro Venegas
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/authenticate")
public class AuthenticationController {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthenticationController.class);

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtUtil;

    /**
     * Autenticacion por token unico
     * @param token
     * @return
     */
    @RequestMapping(value = "/token/{token}", produces = {MediaType.TEXT_PLAIN_VALUE},method = {RequestMethod.GET,RequestMethod.POST})
    public String authenticate(@PathVariable String token) {
        LOGGER.info("Iniciando autenticacion por token...");
        UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken("", token);
        UsernamePasswordAuthenticationToken authenticationToken = (UsernamePasswordAuthenticationToken) authenticationManager.authenticate(auth);
        LOGGER.info("Autenticacion exitosa!");
        return jwtUtil.generateNewToken((SessionUser) authenticationToken.getPrincipal());
    }

}
