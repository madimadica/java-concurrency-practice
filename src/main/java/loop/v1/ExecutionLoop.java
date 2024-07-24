package loop.v1;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class ExecutionLoop implements Runnable {
    private static final int CONCURRENT_MIGRATION_API_LIMIT = 5;

    @Override
    public void run() {
        try (ExecutorService executor = Executors.newVirtualThreadPerTaskExecutor()) {
            Semaphore apiSemaphore = new Semaphore(CONCURRENT_MIGRATION_API_LIMIT, true);
            while (true) {
                List<Integer> newRequests = MockApi.fetchData();
                System.out.printf("[ExecutionLoopThread]: Found %d new requests%n", newRequests.size());
                if (newRequests.isEmpty() && apiSemaphore.availablePermits() == CONCURRENT_MIGRATION_API_LIMIT) {
                    break;
                }
                for (var request : newRequests) {
                    executor.submit(new ExecutionTask(apiSemaphore, request));
                }
                // Short pause before checking for more requests
                try {
                    Thread.sleep(Duration.ofSeconds(5));
                } catch (InterruptedException e) {}
            }
        }
    }
}
