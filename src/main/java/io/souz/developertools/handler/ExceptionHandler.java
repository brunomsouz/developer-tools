package io.souz.developertools.handler;

import com.google.common.collect.Sets;
import io.souz.developertools.exception.BadRequestException;
import io.souz.developertools.model.Error;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@RestController
@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler
    public ResponseEntity<Object> handlerBadRequestException(BadRequestException ex) {
        return new ResponseEntity<>(Sets.newHashSet(new Error().message(ex.getMessage())), BAD_REQUEST);
    }

}
