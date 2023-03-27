package jobscheduler.response;

import lombok.Getter;
import lombok.Setter;
import jobscheduler.job.Job;
import jobscheduler.job.JobStatus;

public class ResponseModel<T> implements Response<T> {
    @Getter
    @Setter
    T value;
    @Getter
    Job<T> job;
    @Setter
    JobStatus jobStatus;

    public ResponseModel(Job<T> job) {
        this.job = job;
        jobStatus = JobStatus.NOT_PICKED_UP;
    }


    @Override
    public T getResult() {

        while (jobStatus != JobStatus.FINISHED) {
            try {
                System.out.println("Waiting for the result");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        return value;
    }

    @Override
    public JobStatus getStatus() {
        return jobStatus;
    }


}