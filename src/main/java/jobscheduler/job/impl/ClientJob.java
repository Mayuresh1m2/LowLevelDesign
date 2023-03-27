package jobscheduler.job.impl;

import jobscheduler.Employee;
import jobscheduler.job.Job;

import java.util.UUID;

public class ClientJob extends Job<Employee> {


    @Override
    public Employee runJob() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        return Employee.builder().name("Employee1").JobTitle("SDE").id(UUID.randomUUID().toString()).build();
    }
}
