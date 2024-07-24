package basics.part2.interference;

public class SafeCounter {
    private int value = 0;

    public synchronized void increment() {
        value++;
    }

    public synchronized void decrement() {
        value--;
    }

    public synchronized int value() {
        return value;
    }
}
