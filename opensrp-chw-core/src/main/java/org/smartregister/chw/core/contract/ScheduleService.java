package org.smartregister.chw.core.contract;

import java.util.List;

public interface ScheduleService {

    /**
     * generating the schedules
     *
     * @param baseEntityID
     * @return
     */
    List<ScheduleTask> generateTasks(String baseEntityID);

    /**
     * add logic to delete the schedule from the database
     */
    void scheduleMaintenance();

    String getScheduleName();

    /**
     * clears the schedules from the database
     */
    void resetSchedule(String baseEntityID, String scheduleName);
}