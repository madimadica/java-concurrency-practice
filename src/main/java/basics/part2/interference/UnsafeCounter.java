package basics.part2.interference;

public class UnsafeCounter {
    private int value = 0;

    public void increment() {
        value++;
    }

    public void decrement() {
        value--;
    }

    public int value() {
        return value;
    }
}
