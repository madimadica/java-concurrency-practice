package basics.part2.locks;


import java.util.function.Consumer;

/**
 * <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/locksync.html">Reference</a>
 * Still really slow though.
 */
public class SafeDualMain {
    private static final int BOUND = 100_000_000;

    public static class Runner implements Runnable {
        private final Consumer<SafeDualCounter> action;
        private final SafeDualCounter counter;

        public Runner(SafeDualCounter counter, Consumer<SafeDualCounter> action) {
            this.action = action;
            this.counter = counter;
        }

        @Override
        public void run() {
            for (int i = 0; i < BOUND; ++i) {
                action.accept(counter);
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SafeDualCounter counter = new SafeDualCounter();
        Thread a = new Thread(new Runner(counter, SafeDualCounter::addApple));
        Thread b = new Thread(new Runner(counter, SafeDualCounter::addOrange));
        a.start();
        b.start();
        a.join();
        b.join();
        final int E = 2 * BOUND;
        boolean error = false;
        if (counter.totalCount() != E) {
            System.out.printf("%d != %d%n", counter.totalCount(), E);
            error = true;
        }
        if (counter.appleCount() != BOUND) {
            System.out.printf("Apple error: %d != %d%n", counter.appleCount(), BOUND);
            error = true;
        }
        if (counter.orangeCount() != BOUND) {
            System.out.printf("Orange error: %d != %d%n", counter.orangeCount(), BOUND);
            error = true;
        }
        if (!error) {
            System.out.println("Perfect");
        }
    }
}
