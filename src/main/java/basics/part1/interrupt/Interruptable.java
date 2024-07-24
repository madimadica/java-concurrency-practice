package basics.part1.interrupt;


import java.util.Optional;

public class Interruptable implements Runnable {

    private final int n;
    private Long output;

    public Interruptable(int n) {
        if (n < 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
    }

    @Override
    public void run() {
        try {
            output = slowFibonacci(n);
            System.out.println("The result was " + output);
        } catch (InterruptedException e) {
            System.out.println("I was interrupted for taking too long!");
        }
    }

    public Optional<Long> output() {
        return Optional.ofNullable(output);
    }

    private long slowFibonacci(long n) throws InterruptedException {
        if (n <= 1) return n;
        if (Thread.interrupted()) {
            throw new InterruptedException();
        }
        return slowFibonacci(n - 2) + slowFibonacci(n - 1);
    }
}
