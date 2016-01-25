package org.exschool.pocketworld.schedule;

import org.exschool.pocketworld.buildQueue.model.Status;
import org.exschool.pocketworld.buildQueue.service.BuildQueueService;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;


/**
 * Created by skandy on 25.01.16.
 */
@Component
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class ScheduleJob extends QuartzJobBean {

    @Autowired
    private BuildQueueService buildQueueService;

    private static final String KEY="status";
        public ScheduleJob() {
        super();
    }

    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {
        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        Status status = (Status)dataMap.get(KEY);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        buildQueueService.deleteAllByStatus(status);

    }
}
