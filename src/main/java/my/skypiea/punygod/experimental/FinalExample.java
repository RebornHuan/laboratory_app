package my.skypiea.punygod.experimental;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;

import java.util.concurrent.TimeUnit;

/**
 * Created by punyGod on 17/1/24.
 */
@State(Scope.Benchmark)
public class FinalExample {
    int i;
    final int j;
    static FinalExample object;


    public FinalExample() {
        i=1;
        j=2;
    }

    public void write() {
        object = new FinalExample();
    }
    public void reader() {
        FinalExample obj = object;
        int a= obj.i;
        int b=obj.j;
        System.out.println("a: " +a+ " b: "+b);
    }
    @Benchmark
    public void test() {
        Runnable taskA = () -> {write(); };
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
                .include(FinalExample.class.getSimpleName())
                // Set the following options as needed
                .mode (Mode.AverageTime)
                .timeUnit(TimeUnit.MICROSECONDS)
                .warmupTime(TimeValue.seconds(1))
                .warmupIterations(1)
                .measurementTime(TimeValue.seconds(1))
                .measurementIterations(2)
                .threads(8)
                .forks(1)
                .shouldFailOnError(true)
                .shouldDoGC(true)
                //.jvmArgs("-XX:+UnlockDiagnosticVMOptions", "-XX:+PrintInlining")
                //.addProfiler(WinPerfAsmProfiler.class)
                .build();

        new Runner(opt).run();


    }


}
