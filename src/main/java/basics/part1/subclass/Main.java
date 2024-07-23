package basics.part1.subclass;

/**
 * <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/runthread.html">Reference</a>
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("I am in the main thread!");
        MyFirstThread myFirstThread = new MyFirstThread();
        myFirstThread.start();
        System.out.println("I am at the end of the main thread!");
    }
}
