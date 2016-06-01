package my.skypiea.punygod.threadLocal;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/5/27.
 */
public class ThreadLocalTest extends AbstractThread {

    public static void main(String[] args) {
        final ThreadLocal<String> local = new ThreadLocal<String>();
        ThreadLocalTest.work(local);
    }
}
