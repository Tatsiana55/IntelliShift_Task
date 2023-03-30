package IntelliShift.custom_exceptions;

public class NoSuchBrowserException extends RuntimeException {
    public NoSuchBrowserException(String errorMessage){
        super(errorMessage);
    }
}
