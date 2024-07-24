package basics.part2.locks;

public class SafeDualCounter {
    private int appleCount;
    private int orangeCount;

    private final Object appleLock = new Object();
    private final Object orangeLock = new Object();

    public void addApple() {
        synchronized (appleLock) {
            appleCount++;
        }
    }

    public void addOrange() {
        synchronized (orangeLock) {
            orangeCount++;
        }
    }

    public int appleCount() {
        return appleCount;
    }

    public int orangeCount() {
        return orangeCount;
    }

    public int totalCount() {
        return appleCount + orangeCount;
    }

}
