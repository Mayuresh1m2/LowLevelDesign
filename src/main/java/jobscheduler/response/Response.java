package jobscheduler.response;

import jobscheduler.job.JobStatus;

public interface Response<T> {
    T getResult();
    JobStatus getStatus();
}
