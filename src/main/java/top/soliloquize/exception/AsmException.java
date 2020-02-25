package top.soliloquize.exception;

/**
 * @author wb
 * @date 2020/2/24
 */
public class AsmException extends RuntimeException {

    public AsmException() {
    }

    public AsmException(String message) {
        super(message);
    }

    public AsmException(String message, Throwable cause) {
        super(message, cause);
    }

    public AsmException(Throwable cause) {
        super(cause);
    }

    public AsmException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
