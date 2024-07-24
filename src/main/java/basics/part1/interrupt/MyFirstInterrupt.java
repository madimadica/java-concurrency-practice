package basics.part1.interrupt;

import java.util.Scanner;

/**
 * <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/interrupt.html">Reference</a>
 *
 * <hr>
 * <ul>
 *     <li><pre>Thread.interrupted()</pre> checks if the current thread has been interrupted and clears the signal</li>
 *     <li><pre>thread.interrupt()</pre> sets the interrupt signal to true</li>
 *     <li><pre>thread.isInterrupted()</pre> checks if this thread has been interrupted, keeps the signal</li>
 *     <li><pre>InterruptedException</pre> Thrown from a blocking state thread</li>
 * </ul>
 */
public class MyFirstInterrupt {
    public static void main(String[] args) throws InterruptedException {
        Scanner in = new Scanner(System.in);
        System.out.print("fibonacci number input: ");
        int n = Integer.parseInt(in.nextLine());
        Interruptable fibonacciComputation = new Interruptable(n);
        Thread fibonacciThread = new Thread(fibonacciComputation);
        fibonacciThread.start();
        // Always end the computation after 3 seconds
        // Don't know enough yet to stop the sleep early if it finished early.
        Thread.sleep(3_000);
        fibonacciThread.interrupt();
        fibonacciComputation.output().ifPresentOrElse(
                (out) -> System.out.println("Result: " + out),
                () -> System.out.println("Time limit exceeded...")
        );
    }
}
