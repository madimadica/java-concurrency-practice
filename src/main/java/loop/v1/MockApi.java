package loop.v1;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

public class MockApi {
    private static final List<Integer> responses = List.of(15, 5, 4, 3, 4, 2, 0, 1);
    private static final AtomicInteger index = new AtomicInteger(0);

    public static List<Integer> fetchData() {
        try {
            int length = responses.get(index.getAndIncrement());
            return IntStream.range(0, length).boxed().toList();
        } catch (IndexOutOfBoundsException e) {
            return List.of();
        }
    }
}
