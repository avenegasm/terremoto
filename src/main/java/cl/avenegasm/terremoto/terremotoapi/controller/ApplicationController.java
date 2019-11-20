package cl.avenegasm.terremoto.terremotoapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alejandro Venegas
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
public class ApplicationController {

    /**
     * Endpoint para verificar estado de la aplicacion
     * @return
     */
    @RequestMapping(value = "/health",method = {RequestMethod.GET,RequestMethod.POST})
    public ResponseEntity health(){
        return new ResponseEntity(HttpStatus.OK);
    }
}
