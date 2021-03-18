package Exceptions;

public abstract class AbstractException extends Exception {

    private final String code;

    private final Object data;

    protected AbstractException(String code, String developerMessage) {
        this(code, developerMessage, null);
    }

    protected AbstractException(String code, String developerMessage, Object data) {
        super(developerMessage);
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public Object getData() {
        return data;
    }


    @Override
    public String toString() {
        return "AbstractServiceException{" +
                "code='" + code + '\'' +
                ", data=" + data +
                "} " + super.toString();
    }
}
