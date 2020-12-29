package multi_thread.juc.completable_future;



import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

/**
 * @Author Tom
 * @Date 2020/6/4 18:03
 * @Version 1.0
 * @Description
 */
public class CompletableFutureExample1 {
    public static void main(String[] args) throws InterruptedException {
        CompletableFuture<Integer> future = CompletableFuture.supplyAsync(() -> {
            return 10;
        });
        CompletableFuture<String> future1 = future.thenApplyAsync(value -> {
            return value.toString();
        });
        CompletableFuture<Integer> future2 = future.thenCompose(i -> {
            return CompletableFuture.supplyAsync(() -> {
                return i.intValue();
            });
        });
        System.out.println(future);
        System.out.println(future1);
        System.out.println(future2);

        TimeUnit.SECONDS.sleep(3);

    }
}
