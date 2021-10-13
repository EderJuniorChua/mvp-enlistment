package ph.edu.ciit.enlistment;

import org.apache.commons.lang3.StringUtils;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

import static org.apache.commons.lang3.StringUtils.isBlank;
import static org.apache.commons.lang3.Validate.isTrue;
import static org.apache.commons.lang3.Validate.notNull;

public class Subject {
    private final String subjectID;
    private final Collection<Subject> prerequisites;

    Subject(String subjectID, Collection<Subject> prerequisites){
        isBlank(subjectID);
        isTrue(StringUtils.isAlphanumeric(subjectID),
                "subjectID must be alphanumeric, was: " + subjectID);
        this.subjectID = subjectID;
        notNull(prerequisites);
        this.prerequisites = new HashSet<>(prerequisites);
        this.prerequisites.removeIf(Objects::isNull);
    }

    public void checkPrereqs(Collection<Subject> subjectsTaken) {
        prerequisites.forEach(subject -> {
            if(!subjectsTaken.contains(subject)) {
                throw new PrerequisiteException("The subject : " + subjectID + " has prereqs "
                        + prerequisites + " not yet taken");
            }
        });
    }
}
