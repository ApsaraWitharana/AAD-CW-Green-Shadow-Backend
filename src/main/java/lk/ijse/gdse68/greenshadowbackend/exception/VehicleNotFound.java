package lk.ijse.gdse68.greenshadowbackend.exception;

public class VehicleNotFound extends RuntimeException{
    public VehicleNotFound() {
        super();
    }

    public VehicleNotFound(String message) {
        super(message);
    }

    public VehicleNotFound(String message, Throwable cause) {
        super(message, cause);
    }
}
