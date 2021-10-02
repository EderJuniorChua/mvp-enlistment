package ph.edu.ciit.enlistment;

public class RoomOverCapacityException extends RuntimeException {
    RoomOverCapacityException(String message){
        super(message);
    }
}
