package basics.part1.runnable;

/**
 * <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html">Reference</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("This is main");
        MyFirstRunnable myFirstRunnable = new MyFirstRunnable();
        Thread myFirstThread = new Thread(myFirstRunnable);
        myFirstThread.start();
        System.out.println("This is the end of main");
    }
}
