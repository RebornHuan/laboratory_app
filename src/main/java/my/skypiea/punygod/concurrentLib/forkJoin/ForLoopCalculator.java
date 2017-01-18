package my.skypiea.punygod.concurrentLib.forkJoin;

import java.util.stream.LongStream;

/**
 * Created by punyGod on 17/1/12.
 */
public class ForLoopCalculator implements Calculator {
    public long sumUp(long n) {
        long[] numbers = LongStream.rangeClosed(1, n).toArray();

        long total = 0;
        for (long i : numbers) {
            total += i;
        }
        return total;
    }
}
