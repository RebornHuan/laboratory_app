package my.skypiea.punygod.experimental;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

/**
 * Created by punyGod on 17/1/20.
 */
public class Demo {

    public static void main(String[] args) {
        ConcurrentLinkedQueueDemo<Node> aaa = new ConcurrentLinkedQueueDemo<>();

        Thread produce_1 = new Thread(new ProduceTask(aaa) );
        Thread produce_2 = new Thread(new ProduceTask(aaa) );

        Thread consumer_1 = new Thread(new ConsumerTask(aaa) );

        produce_1.start();
//        produce_2.start();
//        consumer_1.start();



    }


    static class  ProduceTask implements Runnable {

        final ConcurrentLinkedQueueDemo<Node> aaa;

        public ProduceTask(ConcurrentLinkedQueueDemo<Node> aaa) {
            this.aaa= aaa;
        }

        @Override
        public void run() {
            for(int i=0; i<100; i++) {

                Node node = new Node("name-"+i,i);

                System.out.println(node);
                aaa.offer(node);
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        }
    }


    static class ConsumerTask implements Runnable {

        final ConcurrentLinkedQueueDemo<Node> aaa;

        public ConsumerTask(ConcurrentLinkedQueueDemo<Node> aaa) {
            this.aaa= aaa;
        }

        @Override
        public void run() {
            while (true) {
                Node node = aaa.poll();
                if(node!=null) {
                    System.out.println(node);
                    try {
                        TimeUnit.SECONDS.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }else continue;

            }

        }
    }





    public static class Node{
        String name;
        long id;

        public Node(String name, long id) {
            this.name = name;
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public long getId() {
            return id;
        }

        public void setId(long id) {
            this.id = id;
        }

        @Override
        public String toString() {
            return "Node{" +
                    "name='" + name + '\'' +
                    ", id=" + id +
                    '}';
        }
    }
}

