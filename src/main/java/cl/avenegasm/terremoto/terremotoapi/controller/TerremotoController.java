package cl.avenegasm.terremoto.terremotoapi.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author Alejandro Venegas
 * @version 1.0.0
 * @since 1.0.0
 */
@RestController
@RequestMapping("/terremoto")
public class TerremotoController {

    /**
     * Rest de acceso restringido, arquetipo
     * @return
     */
    @GetMapping("/hola")
    public String hola(){
        return "Hola";
    }
}
