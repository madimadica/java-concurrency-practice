package loop.v1;

import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class ExecutionTask implements Runnable {

    private final Semaphore apiLimit;
    private final Object request;

    public ExecutionTask(Semaphore apiLimit, Object request) {
        this.apiLimit = apiLimit;
        this.request = request;
    }

    @Override
    public void run() {
        try {
            apiLimit.acquire();
            System.out.println("Current queue size (approx): " + apiLimit.getQueueLength());
            System.out.println("Beginning task API calls");
            try {
                Thread.sleep(ThreadLocalRandom.current().nextInt(1000, 5000));
            } catch (InterruptedException e) {}
        } catch (InterruptedException e) {
        } finally {
            System.out.println("Finished API calls");
            apiLimit.release();
        }
    }
}
