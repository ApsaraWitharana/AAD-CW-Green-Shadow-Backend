package lk.ijse.gdse68.greenshadowbackend.exception;

/**
 * @author : sachini
 * @date : 2024-11-02
 **/
public class CropNotFoundException extends RuntimeException{
    public CropNotFoundException() {
        super();
    }

    public CropNotFoundException(String message) {
        super(message);
    }

    public CropNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
