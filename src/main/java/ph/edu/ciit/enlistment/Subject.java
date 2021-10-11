package ph.edu.ciit.enlistment;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.Validate.isTrue;

public class Subject {
    private final String subjectID;

    Subject(String subjectID){
        isBlank(subjectID);
        isTrue(StringUtils.isAlphanumeric(subjectID),
                "subjectID must be alphanumeric, was: " + subjectID);
        this.subjectID = subjectID;
    }
}
