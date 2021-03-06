package my.skypiea.punygod.concurrentLib.forkJoin;

import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.RecursiveTask;
import java.util.stream.LongStream;

/**
 * Created by punyGod on 17/1/12.
 */
public class ForkJoinCalculator2 implements Calculator {
    private ForkJoinPool pool;

    private static class SumTask extends RecursiveTask<Long> {
        private long[] numbers;
        private int from;
        private int to;

        public SumTask(long[] numbers, int from, int to) {
            this.numbers = numbers;
            this.from = from;
            this.to = to;
        }

        @Override
        protected Long compute() {
            // 当需要计算的数字小于6时，直接计算结果
            if (to - from < 6) {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
                // 否则，把任务一分为二，递归计算
            } else {
                int middle = (from + to) / 2;
                SumTask taskLeft = new SumTask(numbers, from, middle);
                taskLeft.fork();
                SumTask taskRight = new SumTask(numbers, middle + 1, to);
                Long rightResult = taskRight.compute();
                Long leftResult = taskLeft.join();
                return rightResult + leftResult;
            }
        }
    }

    public ForkJoinCalculator2() {
        // 也可以使用公用的 ForkJoinPool：
        // pool = ForkJoinPool.commonPool()
        pool = new ForkJoinPool();
    }

    @Override
    public long sumUp(long n) {

        long[] numbers = LongStream.rangeClosed(1, n).toArray();

        return pool.invoke(new SumTask(numbers, 0, numbers.length - 1));
    }
}
