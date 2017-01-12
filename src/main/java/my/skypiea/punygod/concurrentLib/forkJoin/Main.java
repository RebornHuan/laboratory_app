package my.skypiea.punygod.concurrentLib.forkJoin;

import java.util.stream.LongStream;

/**
 * Created by punyGod on 17/1/12.
 */
public class Main {
    public static void main(String[] args) {
        long[] numbers = LongStream.rangeClosed(1, 1000).toArray();
        Calculator calculator = new ForkJoinCalculator();
        System.out.println(calculator.sumUp(numbers)); // 打印结果500500
    }
}
