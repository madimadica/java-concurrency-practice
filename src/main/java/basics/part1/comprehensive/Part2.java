package basics.part1.comprehensive;

import basics.part1.interrupt.Interruptable;

import java.time.Duration;
import java.util.Scanner;

/**
 * Using the same ref as part 1 (<a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/simple.html">Ref</a>),
 * we will finish implementing {@link basics.part1.interrupt.MyFirstInterrupt} to end early.
 */
public class Part2 {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.print("fibonacci number input: ");
        int n = Integer.parseInt(in.nextLine());
        Interruptable fibonacciComputation = new Interruptable(n);
        Thread fibonacciThread = new Thread(fibonacciComputation);
        fibonacciThread.start();

        long startMs = System.currentTimeMillis();

        while (fibonacciThread.isAlive()) {
            long currentTime = System.currentTimeMillis();
            if (currentTime - startMs > 3000) {
                System.out.println("Time limit exceeded");
                fibonacciThread.interrupt();
                fibonacciThread.join();
            }
            fibonacciThread.join(Duration.ofMillis(50));
        }
        fibonacciComputation.output().ifPresent(
                (out) -> System.out.println("Result: " + out)
        );
    }
}
