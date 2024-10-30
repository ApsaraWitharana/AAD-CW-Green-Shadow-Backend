package lk.ijse.gdse68.greenshadowbackend.exception;

/**
 * @author : sachini
 * @date : 2024-10-30
 **/
public class StaffNoteFoundException extends RuntimeException {
    public StaffNoteFoundException() {
        super();
    }

    public StaffNoteFoundException(String message) {
        super(message);
    }

    public StaffNoteFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
