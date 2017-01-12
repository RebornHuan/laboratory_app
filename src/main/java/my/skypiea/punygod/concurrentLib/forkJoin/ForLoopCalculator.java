package my.skypiea.punygod.concurrentLib.forkJoin;

/**
 * Created by punyGod on 17/1/12.
 */
public class ForLoopCalculator implements Calculator {
    public long sumUp(long[] numbers) {
        long total = 0;
        for (long i : numbers) {
            total += i;
        }
        return total;
    }
}
