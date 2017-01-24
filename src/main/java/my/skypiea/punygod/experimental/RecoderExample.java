package my.skypiea.punygod.experimental;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

/**
 * Created by punyGod on 17/1/24.
 */

//@BenchmarkMode(Mode.Throughput)
//@Warmup(iterations = 3)
//@Measurement(iterations = 10, time = 5, timeUnit = TimeUnit.SECONDS)
//@Threads(16)
//@OutputTimeUnit(TimeUnit.MILLISECONDS)
@State(Scope.Thread)
public class RecoderExample {

    private int a =0;
    private boolean flag = false;


    public void writer() {
        a =2;
        flag =true;
    }

    public void  reader() {
        if (flag) {
            int i = a*a;
            System.out.print(i);
        }else {
            System.out.print("out");
        }
    }

    @Benchmark
    public void test() {
        Runnable taskA = () -> {writer(); };
        Runnable taskB = () -> {reader(); };
        Thread threadA = new Thread(taskA);
        Thread threadB = new Thread(taskB);
        threadA.start();
        threadB.start();

    }


    public static void main(String[] args) throws InterruptedException, RunnerException {

        Options opt = new OptionsBuilder()
                // Specify which benchmarks to run.
                // You can be more specific if you'd like to run only one benchmark per test.
                .include(RecoderExample.class.getSimpleName())
                // Set the following options as needed
                .mode (Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(3)
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(2)
                .threads(16)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();


    }









}
