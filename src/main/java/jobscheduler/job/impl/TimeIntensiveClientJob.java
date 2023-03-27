package jobscheduler.job.impl;

import jobscheduler.job.Job;

public class TimeIntensiveClientJob extends Job<Long> {
    @Override
    public Long runJob() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return System.currentTimeMillis();

    }
}
