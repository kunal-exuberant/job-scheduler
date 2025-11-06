import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class JobScheduler {

    private final ScheduledExecutorService scheduler;

    public JobScheduler() {
        // Create a scheduled executor service with a single thread
        this.scheduler = Executors.newScheduledThreadPool(1);
    }

    public void scheduleJob(Runnable job, long initialDelay, long period, TimeUnit unit) {
        // Schedule the job to run at a fixed rate
        scheduler.scheduleAtFixedRate(job, initialDelay, period, unit);
    }

    public void shutdown() {
        // Shutdown the scheduler gracefully
        scheduler.shutdown();
    }

    public static void main(String[] args) {
        JobScheduler jobScheduler = new JobScheduler();

        // Define a job to be scheduled
        Runnable job = () -> {
            System.out.println("Job executed at: " + System.currentTimeMillis());
        };

        // Schedule the job to run every 2 seconds with an initial delay of 1 second
        jobScheduler.scheduleJob(job, 1, 2, TimeUnit.SECONDS);

        // Keep the main thread alive for 10 seconds to observe the scheduled job
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Shutdown the scheduler
        jobScheduler.shutdown();
    }
}