package edu.ban7.negosud_di_l3;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.Map;

@ControllerAdvice
public class IntercepteurGlobal {

    //Intercepte toutes les erreurs de contrainte SQL (unicité, clé etrangère...)
    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    @ResponseBody // Assure que la réponse est envoyée au format JSON (dans le corp de la réponse)
    public Map<String, Object> handleConflictException(DataIntegrityViolationException ex) {
        return Map.of("message", ex.getMessage());
    }
}
