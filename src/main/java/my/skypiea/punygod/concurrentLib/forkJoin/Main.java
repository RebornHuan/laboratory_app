package my.skypiea.punygod.concurrentLib.forkJoin;

import java.util.function.Function;
import java.util.stream.LongStream;

/**
 * Created by punyGod on 17/1/12.
 */
public class Main {
    public static void main(String[] args) {

        Calculator forkJoinCalculator = new ForkJoinCalculator();
        Calculator forkJoinCalculator2 = new ForkJoinCalculator2();
        Calculator forLoopCalculator = new ForLoopCalculator();
        Calculator executorServiceCalculator = new ExecutorServiceCalculator();

        System.out.println("forkJoinCalculator Sum done in: " + measurePerf(forkJoinCalculator::sumUp, 100_000_000L) + " msecs");
        System.out.println("forkJoinCalculator2 Sum done in: " + measurePerf(forkJoinCalculator2::sumUp, 100_000_000L) + " msecs");
        System.out.println("forLoopCalculator Sum done in: " + measurePerf(forLoopCalculator::sumUp, 100_000_000L) + " msecs");
        System.out.println("executorServiceCalculator Sum done in: " + measurePerf(executorServiceCalculator::sumUp, 100_000_000L) + " msecs");


//        long[] numbers = LongStream.rangeClosed(1, 1000_000).toArray();
//        Calculator calculator = new ForkJoinCalculator();
//
//
//        long start = System.nanoTime();
//        System.out.println(calculator.sumUp(numbers)); // 打印结果500500
//        long duration = (System.nanoTime() - start) / 1_000_000;
//
//        System.out.println(duration);

    }

    public static <T, R> long measurePerf(Function<T, R> f, T input) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            R result = f.apply(input);
            long duration = (System.nanoTime() - start) / 1_000_000;
            System.out.println("Result: " + result);
            if (duration < fastest) fastest = duration;
        }
        return fastest;
    }
}
