package basics.part1.join;

import basics.part1.subclass.MyFirstThread;

/**
 * <a href="https://docs.oracle.com/javase/tutorial/essential/concurrency/join.html">Reference</a>
 */
public class MyFirstJoin {
    public static void main(String[] args) throws InterruptedException {
        System.out.println("I am in the main thread!");
        MyFirstThread myFirstThread = new MyFirstThread();
        myFirstThread.start();
        myFirstThread.join(); // Blocked until execution finishes
        System.out.println("I am at the end of the main thread!");
    }
}
