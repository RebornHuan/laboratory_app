package my.skypiea.punygod.algorithm.base;

import java.util.Stack;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/5/3.
 */
public class StackTest {

    public static void main(String[] args) {
        Stack<String> stack = new Stack<String>();

        stack.push("111");
        stack.push("222");
        stack.push("333");
        stack.push("444");

        for (String string : stack) {
            System.out.println(string);
        }
    }
}
