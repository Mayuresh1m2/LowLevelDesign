# Problem Statement

Using the basic data stuctures, basic threading concepts and basic language constructs (avoid using ThreadPool/ExecutorService/Future/etc) develop an Executor class which provides following functionality: 

1. submit(Job) - submits the Job to be run asynchronously i.e. returns immediately without waiting for the job to finish, multiple jobs can be submitted in parallel and multiple jobs should be run in parallel. Returns an immediate response object which has the following properties:
1. getStatus() - Client can use a non-blocking method in the response to check if the execution of job has finished.
1. Client can use a method in the response to get the result of execution. If execution hasn't finished yet, this method blocks until it does and then returns the response

P.S: The Executor  should support multiple clients. For the scope of this problem, assume that 
everything is running in the same process as threads.


submit(Job ) - non-blocking ( Response )

getStatus() - on response object

getResults() - on response object



Running a thread:
>Runnable task = new MyRunnable() // has implement run which contains the business logic
>
>Thread t = new Thread(task)
>
>t.start() // thread runs `task.run()` and as soon as task.run() method finishes the thread 
> dies