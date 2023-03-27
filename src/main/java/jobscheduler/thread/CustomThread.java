package jobscheduler.thread;

import jobscheduler.job.JobStatus;
import jobscheduler.response.ResponseModel;

public class CustomThread<T> extends Thread {
    ResponseModel<T> responseModel;

    public CustomThread(ResponseModel<T> model) {
        responseModel = model;
    }

    @Override
    public void run() {
        System.out.println("Starting client job");
        responseModel.setJobStatus(JobStatus.RUNNING);
        responseModel.getJob().run();
        responseModel.setValue(responseModel.getJob().getResponse());


        System.out.println("Client job is finished");
        responseModel.setJobStatus(JobStatus.FINISHED);
    }
}
