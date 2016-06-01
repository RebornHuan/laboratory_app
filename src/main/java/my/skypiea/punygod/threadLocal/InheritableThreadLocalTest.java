package my.skypiea.punygod.threadLocal;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/5/27.
 */
public class InheritableThreadLocalTest extends AbstractThread {

    public static void main(String[] args) {
        final ThreadLocal<String> local = new InheritableThreadLocal<String>();
        InheritableThreadLocalTest.work(local);
    }
}
