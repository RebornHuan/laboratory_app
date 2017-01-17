package my.skypiea.punygod.concurrentLib.forkJoin;

import java.util.stream.LongStream;

/**
 * Created by punyGod on 17/1/12.
 */
public class Main {
    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 1000_000).toArray();
        Calculator calculator = new ForkJoinCalculator();


        long start = System.nanoTime();
        System.out.println(calculator.sumUp(numbers)); // 打印结果500500
        long duration = (System.nanoTime() - start) / 1_000_000;

        System.out.println(duration);

    }
}
