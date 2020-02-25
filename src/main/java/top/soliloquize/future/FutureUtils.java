package top.soliloquize.future;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;

/**
 * future工具类
 *
 * @author wb
 * @date 2020/2/25
 */
public class FutureUtils {

    /**
     * 出现异常立马结束所有future
     *
     * @param futures futures
     */
    public static void fastDownWhenException(List<CompletableFuture<Void>> futures) {
        CompletableFuture<Void> allWithFailFast = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        futures.forEach(future -> future.exceptionally(e -> {
            allWithFailFast.completeExceptionally(e);
            return null;
        }));
    }

    /**
     * 设置超时时间
     *
     * @param future  future
     * @param timeOut 超时时间
     * @param unit    时间单位
     * @param <T>     T
     * @return CompletableFuture
     */
    public static <T> CompletableFuture<T> completeOnTimeout(CompletableFuture<T> future, long timeOut, TimeUnit unit) {
        ScheduledExecutorService e = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<Boolean> job = e.schedule(() -> future.completeExceptionally(new TimeoutException()), timeOut, unit);
        return future.whenComplete((x, y) -> {
            job.cancel(false);
            e.shutdown();
        });
    }

    /**
     * 等待一定时间,按提交顺序获取数据
     *
     * @param futures futures
     * @param timeOut 超时时间
     * @param unit    时间单位
     * @param <T>     T
     * @return List
     */
    public static <T> List<T> allOf(List<CompletableFuture<T>> futures, long timeOut, TimeUnit unit) {
        return completeOnTimeout(
                CompletableFuture.allOf(futures.toArray(new CompletableFuture[0])), timeOut, unit)
                .thenApply(v -> futures.stream()
                        .filter(CompletableFuture::isDone)
                        .map(CompletableFuture::join)
                        .collect(Collectors.toList())).join();
    }

    public static void main(String[] args) {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future1";
        });
        CompletableFuture<String> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future2";
        });
        CompletableFuture<String> future3 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            return "future3";
        });
        List<String> result = allOf(Arrays.asList(future1, future2, future3), 4, TimeUnit.SECONDS);
        result.forEach(System.out::println);
    }
}
