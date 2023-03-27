package jobscheduler.job;


import lombok.Getter;

public abstract class Job<T> implements Runnable {

    @Getter T response;

    public Job() {

    }


    @Override
    public void run() {
        response = runJob();
    }

    public abstract T runJob();
}
