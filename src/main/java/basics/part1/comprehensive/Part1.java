package basics.part1.comprehensive;

import java.time.Duration;
import java.util.List;

/**
 * <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/simple.html">Reference</a>
 */
public class Part1 {

    public static void main(String[] args) throws InterruptedException {
        long delayMs = 9000;

        log("Starting main procedure");
        long startTime = System.currentTimeMillis();
        Thread t = new Thread(new MessageLoop());
        t.start();
        log("Started thread");

        while (t.isAlive()) {
            log("Pending...");
            t.join(Duration.ofSeconds(1));
            long currentTime = System.currentTimeMillis();
            if (currentTime - startTime > delayMs && t.isAlive()) {
                log("Time limit exceeded");
                t.interrupt();
                t.join();
            }
        }
        log("Finished");
    }

    static void log(String message) {
        String threadName = Thread.currentThread().getName();
        System.out.printf("%s: %s%n", threadName, message);
    }

    private static class MessageLoop implements Runnable {
        @Override
        public void run() {
            List<String> messages = List.of(
                    "First I learned about runnables",
                    "Next I learned about threads",
                    "Next I learned about sleeps",
                    "Next I learned about interrupts",
                    "Finally I learned about joins"
            );
            try {
                for (String message : messages) {
                    log(message);
                    Thread.sleep(3000L);
                }
            } catch (InterruptedException e) {
                log("I was interrupted!");
            }
        }
    }

}
