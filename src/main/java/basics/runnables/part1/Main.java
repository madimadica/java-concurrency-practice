package basics.runnables.part1;

public class Main {
    public static void main(String[] args) {
        System.out.println("This is main");
        MyFirstRunnable myFirstRunnable = new MyFirstRunnable();
        Thread myFirstThread = new Thread(myFirstRunnable);
        myFirstThread.start();
        System.out.println("This is the end of main");
    }
}
