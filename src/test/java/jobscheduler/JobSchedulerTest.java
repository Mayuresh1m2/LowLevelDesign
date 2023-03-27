package jobscheduler;

import jobscheduler.job.JobStatus;
import jobscheduler.job.impl.ClientJob;
import jobscheduler.job.impl.TimeIntensiveClientJob;
import jobscheduler.response.Response;
import jobscheduler.scheduler.JobScheduler;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class JobSchedulerTest {
    JobScheduler<String> jobScheduler;

    @Before
    public void setup() {
        jobScheduler = new JobScheduler<>(2);
    }

    @Test
    public void testGetStatusApis() {

        Response x = jobScheduler.submitJob(new ClientJob());
        Assert.assertEquals(JobStatus.NOT_PICKED_UP, x.getStatus());
        while (x.getStatus() == JobStatus.NOT_PICKED_UP) {
            System.out.println("Job is not picked up yet");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Assert.assertEquals(JobStatus.RUNNING, x.getStatus());
        while (x.getStatus() == JobStatus.RUNNING) {
            System.out.println("Job is in progress");
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
        Assert.assertEquals(JobStatus.FINISHED, x.getStatus());
    }

    @Test
    public void testBlockingGetResultCall() {
        Response x = jobScheduler.submitJob(new ClientJob());
        x.getResult();
        Assert.assertEquals(JobStatus.FINISHED, x.getStatus());
    }

    @Test
    public void testAMixOfJobs() {
        Response<String> x = jobScheduler.submitJob(new ClientJob());
        Response<String> y = jobScheduler.submitJob(new TimeIntensiveClientJob());
        x.getResult();
        y.getResult();
        Assert.assertEquals(JobStatus.FINISHED, x.getStatus());
        Assert.assertEquals(JobStatus.FINISHED, y.getStatus());
    }

    @Test
    public void testSchedulerWithMoreJobsThenCapacity() {
        int size = 10;
        Response<String>[] responses = new Response[size];
        for (int i = 0; i < size; i++) {
            responses[i] = jobScheduler.submitJob(new ClientJob());
        }
        boolean allJobsComplete = false;
        while (!allJobsComplete) {
            allJobsComplete = true;
            for (int i = 0; i < size; i++) {
                if (responses[i].getStatus() != JobStatus.FINISHED) {
                    allJobsComplete = false;
                }
            }
        }
        for (Response<String> response:responses){
            Assert.assertEquals(JobStatus.FINISHED, response.getStatus());
        }
    }
}
