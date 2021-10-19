package ph.edu.ciit.enlistment;

public class InvalidPeriodException extends RuntimeException{
    //check student enlistment schedule
    //If schedule end time is earlier than or equal to start time
    //throw invalid period exception
    InvalidPeriodException(String message){
        super(message);
    }
}

