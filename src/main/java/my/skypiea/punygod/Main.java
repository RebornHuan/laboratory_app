package my.skypiea.punygod;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.TimeUnit;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/25.
 */
public class Main {

    public static void main(String[] args) throws Exception {


        //ben

        while (true) {

    //        List<String> demo =    new CopyOnWriteArrayList();

            List<String> demo =    new ArrayList<String>();

            for(int i=0 ; i<5; i++) {
                demo.add(i+"");
            }

            HashMap<String , List<String>>  aaa = new HashMap<String , List<String>>();
            MyThread my1 = new MyThread("my1" , demo,aaa);

            my1.setName("my1");
            MyThread my2 = new MyThread("my2" , demo,aaa);
            my2.setName("my2");

            my1.start();

            my2.start();


            my2.join();
            my1.join();

            List<String> aa = aaa.get("my1");
            List<String> bb = aaa.get("my2");

            if(aa == null){
                aa = new ArrayList<>();
            }

            if(bb == null) {
                bb = new ArrayList<>();
            }

            if(aa!=null && bb!=null) {

                System.out.println("total: " + (aa.size() + bb.size()));
            }




            boolean flag = false;
            for(String a : aa) {
                for(String b : bb) {
                    if(a==b) {
                        System.out.println("my1=my2: " + a);
                        flag=true;
                    }
                }
            }

            if(flag) {

                System.out.println(aa.size());
                System.out.println(bb.size());

                System.out.println(aa);
                System.out.println(bb);
                System.out.println(demo);
                break;
            }



        }


    }

    static class MyThread extends Thread {

        List<String> demo ;
        List<String> record ;
        HashMap<String , List<String>>  aaa;

        public MyThread(String name, List<String> demo , HashMap<String , List<String>>  aaa ) {
            super(name);
            this.demo = demo;
            this.record = new ArrayList<String>();
            this.aaa = aaa;
        }

        public String get(){
            synchronized(MyThread.class) {
                String instance = null;
                try{
                    if(demo.size()>0) {
                        instance = demo.remove(0);
                    }
                }catch(Exception e){
                    System.out.println(e.getCause());
                    e.printStackTrace();
                }
                return instance;
           }

        }

        @Override
        public void run() {

            while (demo.size() > 0 ) {

                String aaa = get();

                if(aaa!=null) {
                    record.add(aaa);
                }


        /*        try {
                    TimeUnit.SECONDS.sleep(3);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }*/
            }

         //   System.out.println(Thread.currentThread().getName()+ "  " + record.size());
            aaa.put(Thread.currentThread().getName(),record);

        }


    }
}
