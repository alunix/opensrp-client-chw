package org.smartregister.chw.util;

import org.smartregister.chw.anc.repository.VisitDetailsRepository;
import org.smartregister.chw.anc.repository.VisitRepository;

public interface RepositoryUtilsFlv {

    String[] UPGRADE_V9 = {
            "DROP TABLE " + VisitRepository.VISIT_TABLE + ";",
            "DROP TABLE " + VisitDetailsRepository.VISIT_DETAILS_TABLE + ";",
    };

    String[] UPDATE_V12 = {
            "ALTER TABLE ec_child ADD COLUMN low_birth_weight VARCHAR;"
    };
}
