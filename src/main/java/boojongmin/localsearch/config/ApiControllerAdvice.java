package boojongmin.localsearch.config;

import boojongmin.localsearch.exception.ApiServerException;
import boojongmin.localsearch.model.ErrorModel;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ApiControllerAdvice {
    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorModel handleBindException(IllegalArgumentException ex) {
        return ErrorModel.builder().cause("invalid paramter").build();

    }

    @ExceptionHandler(ApiServerException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorModel handleBindException(ApiServerException ex) {
        return ErrorModel.builder().cause(ex.getMessage()).build();

    }
}
