package basics.part1.sleep;

/**
 * <p>
 * Implements a simulation of a the start of a Mariokart race: <b>3, 2, 1, Go!</b>
 * <br>
 * It uses {@link Thread#sleep(long)} for a non-precise countdown,
 * and carriage returns to make the console output simulate the race GUI.
 * </p>
 *
 * <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/sleep.html">Reference</a>
 */
public class Main {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 3; i --> 0;) {
            System.out.print("\r" + (i + 1));
            Thread.sleep(1_000L);
        }
        System.out.print("\rGo!");
    }
}
