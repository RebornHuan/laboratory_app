/*
package my.skypiea.punygod.mq;

*/
/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/5/16.
 *//*


import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

public class TestMQ {
    static MQQueueManager qMgr;
    static int CCSID = 1381;
    static String queueString = "LOCALQUEUE";

    public static void connect() throws MQException {
        MQEnvironment.hostname = "esbmqdev.cnsuning.com";
        MQEnvironment.channel = "CHANNEL_DATALEGO";
        MQEnvironment.port = 1432;
        MQEnvironment.CCSID = CCSID;

        qMgr = new MQQueueManager("myTest");

    }

    public static void sendMsg(String msgStr) {
        int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE;
        MQQueue queue = null;
        try {
            //����Q1ͨ��������
            queue = qMgr.accessQueue(queueString, openOptions, null, null, null);
            MQMessage msg = new MQMessage();// Ҫд����е���Ϣ
            msg.format = MQC.MQFMT_STRING;
            msg.characterSet = CCSID;
            msg.encoding = CCSID;
            // msg.writeObject(msgStr); //����Ϣд����Ϣ������
            msg.writeString(msgStr);
            MQPutMessageOptions pmo = new MQPutMessageOptions();
            msg.expiry = -1;    // ������Ϣ�ò�����
            queue.put(msg, pmo);// ����Ϣ�������
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (queue != null) {
                try {
                    queue.close();
                } catch (MQException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void receiveMsg() {
        int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT | MQC.MQOO_INQUIRE;
        MQQueue queue = null;
        try {
            queue = qMgr.accessQueue(queueString, openOptions, null, null, null);
            System.out.println("�ö��е�ǰ�����Ϊ:" + queue.getCurrentDepth());
            System.out.println("===========================");
            int depth = queue.getCurrentDepth();
            //�����е������Ϣ������
            while (depth-- > 0) {
                MQMessage msg = new MQMessage();// Ҫ���Ķ��е���Ϣ
                MQGetMessageOptions gmo = new MQGetMessageOptions();
                queue.get(msg, gmo);
                System.out.println("��Ϣ�Ĵ�СΪ��" + msg.getDataLength());
                System.out.println("��Ϣ�����ݣ�\n" + msg.readStringOfByteLength(msg.getDataLength()));
                System.out.println("---------------------------");
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } finally {
            if (queue != null) {
                try {
                    queue.close();
                } catch (MQException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws MQException {
        connect();
      //  sendMsg("fuck ���ү��");
        receiveMsg();
    }
}
*/
