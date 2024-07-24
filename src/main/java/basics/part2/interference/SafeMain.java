package basics.part2.interference;

/**
 * <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/syncmeth.html">Reference</a>
 */
public class SafeMain {
    private static final int BOUND = 100_000_000;

    private static class Runner implements Runnable {
        private final SafeCounter counterReference;

        public Runner(SafeCounter counter) {
            this.counterReference = counter;
        }

        public void run() {
            for (int i = 0; i < BOUND; ++i) {
                counterReference.increment();
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SafeCounter counter = new SafeCounter();
        Thread a = new Thread(new Runner(counter));
        Thread b = new Thread(new Runner(counter));
        a.start();
        b.start();
        a.join();
        b.join();
        final int E = 2 * BOUND;
        if (counter.value() != E) {
            System.out.printf("%d != %d%n", counter.value(), E);
        } else {
            System.out.println("No errors, wow!");
        }
    }
}
