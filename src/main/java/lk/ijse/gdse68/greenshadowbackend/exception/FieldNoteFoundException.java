package lk.ijse.gdse68.greenshadowbackend.exception;
/**
 * @author : sachini
 * @date : 2024-11-01
 **/
public class FieldNoteFoundException extends RuntimeException{
    public FieldNoteFoundException() {
        super();
    }

    public FieldNoteFoundException(String message) {
        super(message);
    }

    public FieldNoteFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
