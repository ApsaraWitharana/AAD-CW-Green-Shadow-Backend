package lk.ijse.gdse68.greenshadowbackend.exception;

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
