package eu.urmas.petshop.infrastructure.rest;

import eu.urmas.petshop.infrastructure.rest.error.ApiError;
import eu.urmas.petshop.infrastructure.rest.exception.DataNotFoundException;
import eu.urmas.petshop.infrastructure.rest.exception.ForbiddenException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * This class watches for exceptions thrown by REST controllers and turns them into friendly JSON
 * error responses.
 */
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    /**
     * Handles cases where a user is not allowed to perform an action. Produces a 403 Forbidden
     * response with an error message and request path.
     */
    @ExceptionHandler(ForbiddenException.class)
    public ResponseEntity<ApiError> handleForbiddenException(ForbiddenException exception, HttpServletRequest request) {

        return buildErrorResponse(HttpStatus.FORBIDDEN, exception.getMessage(), request.getRequestURI());
    }

    /**
     * Handles cases where requested data cannot be found. Produces a 404 Not Found response with an
     * error message and request path.
     */
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<ApiError> handleDataNotFoundException(DataNotFoundException exception, HttpServletRequest request) {

        return buildErrorResponse(HttpStatus.NOT_FOUND, exception.getMessage(), request.getRequestURI());
    }

    /**
     * Handles validation failures on method arguments. Pulls out the first validation error, then
     * returns a 400 Bad Request response with a simple message and the request path.
     */
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException exception, HttpHeaders headers, HttpStatusCode status, WebRequest webRequest) {

        FieldError firstError = exception.getBindingResult().getFieldErrors().get(0);

        ServletWebRequest servletReq = (ServletWebRequest) webRequest;
        String path = servletReq.getRequest().getRequestURI();

        return buildErrorResponseObject(HttpStatus.BAD_REQUEST, firstError.getField() + ": " + firstError.getDefaultMessage(), path);
    }

    private ApiError buildApiError(HttpStatus status, String message, String path) {
        ApiError apiError = new ApiError();
        apiError.setStatus(status);
        apiError.setMessage(message);
        apiError.setPath(path);
        return apiError;
    }

    private ResponseEntity<ApiError> buildErrorResponse(HttpStatus status, String message, String path) {
        ApiError apiError = buildApiError(status, message, path);
        return new ResponseEntity<>(apiError, status);
    }

    private ResponseEntity<Object> buildErrorResponseObject(HttpStatus status, String message, String path) {
        ApiError apiError = buildApiError(status, message, path);
        return new ResponseEntity<>(apiError, status);
    }
}
