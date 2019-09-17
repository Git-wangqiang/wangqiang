package cn.wmyskxz.springboot.exceptions;

public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String format, Object... args) {
        super(String.format(format, args));
    }
}