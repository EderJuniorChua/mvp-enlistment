package ph.edu.ciit.enlistment;


import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3.Validate.*;
import static org.apache.commons.lang3.StringUtils.*;

public class Section {
    private final String sectionId;

    Section (String sectionId) {
        isBlank(sectionId);
        isTrue(StringUtils.isAlphanumeric(sectionId),
                "sectionId must be alphanumeric, was: " + sectionId);
        this.sectionId = sectionId;

    }

    @Override
    public String toString() {
        return sectionId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Section section = (Section) o;

        return sectionId != null ? sectionId.equals(section.sectionId) : section.sectionId == null;
    }

    @Override
    public int hashCode() {
        return sectionId != null ? sectionId.hashCode() : 0;
    }
}