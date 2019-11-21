package cl.avenegasm.terremoto.terremotoapi.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_GATEWAY)
public class ExternalApiException extends RuntimeException{

    public ExternalApiException(String message) {
        super(message);
    }
}
