package api.exception;

import org.springframework.http.HttpStatus;

/**
 * @author Marcin Górski
 */
public class ApiException extends RuntimeException {
    private final HttpStatus status;

    public ApiException(String message, HttpStatus status) {
        super(message);
        if (status == null) {
            throw new IllegalArgumentException("HTTP status cannot be null.");
        }
        this.status = status;
    }

    public HttpStatus getStatus() {
        return status;
    }


}
