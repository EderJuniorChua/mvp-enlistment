package ph.edu.ciit.enlistment;

public class InvalidPeriodException extends RuntimeException{
    //check student enlistment schedule
    // if schedule end time is earlier than start time
    //throw invalid period exception
    InvalidPeriodException(String message){
        super(message);
    }
}

