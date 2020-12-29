package multi_thread.design_pattern.immutable;

import java.util.stream.IntStream;

/**
 * @Author Tom
 * @Date 2020/5/27 14:26
 * @Version 1.0
 * @Description
 */
public class ImmutableClient {
    public static void main(String[] args) {
        Person person = new Person("Tom", "Dalian");
        IntStream.rangeClosed(0, 5).forEach(i ->
            new UsePersonThread(String.valueOf(i), person).start()
        );
    }
}
