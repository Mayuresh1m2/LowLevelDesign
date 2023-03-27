package jobscheduler.scheduler;

import jobscheduler.job.Job;
import jobscheduler.response.Response;
import jobscheduler.response.ResponseModel;
import jobscheduler.thread.CustomThread;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.concurrent.atomic.AtomicInteger;

public class JobScheduler<T> implements Runnable {
    Queue<ResponseModel<T>> jobQueue;
    List<CustomThread<T>> threads;
    int capacity;
    int activeJobs;
    AtomicInteger index;

    public JobScheduler(int size) {
        jobQueue = new LinkedList<>();
        threads = new ArrayList<>();
        capacity = size;
        activeJobs = 0;
        index = new AtomicInteger(0);
        new Thread(this).start();


    }

    public Response submitJob(Job job) {
        System.out.println("Adding " + job + " to job queue at " + index.getAndIncrement());
        ResponseModel<T> jobResponse = new ResponseModel<>(job);
        jobQueue.add(jobResponse);

        return jobResponse;
    }


    @Override
    public void run() {
        while (true) {
            try {
                Thread.sleep(1000);
                scheduleJobs();
                makeRoomForNewJobs();
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    private void scheduleJobs() {
        if (activeJobs < capacity) {
            if (!jobQueue.isEmpty()) {
                CustomThread<T> runningJob = new CustomThread<>(jobQueue.remove());
                runningJob.start();
                threads.add(runningJob);
                activeJobs++;
            }
        }
    }

    private void makeRoomForNewJobs() {
//        System.out.println("Making room for new jobs by removing jobs which are done");
        List<Integer> indexesToRemove = new LinkedList<>();
        for (int i = 0; i < threads.size(); i++) {
            if (!threads.get(i).isAlive()) {
                indexesToRemove.add(i);
            }
        }
        for (int index : indexesToRemove) {
            threads.remove(index);
            activeJobs--;
        }
    }


}
