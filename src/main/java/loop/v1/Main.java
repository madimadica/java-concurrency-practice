package loop.v1;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Thread.ofVirtual()
                .name("ExecutionLoopThread")
                .start(new ExecutionLoop())
                .join();
    }
}
