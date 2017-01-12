/*
package my.skypiea.punygod.mq;

*/
/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/5/16.
 *
 * @author Fenglb E-mail:56553655@163.com
 * @version ����ʱ�䣺2009-4-30 ����04:13:38
 * ��˵��
 * @author Fenglb E-mail:56553655@163.com
 * @version ����ʱ�䣺2009-4-30 ����04:13:38
 * ��˵��
 * @author Fenglb E-mail:56553655@163.com
 * @version ����ʱ�䣺2009-4-30 ����04:13:38
 * ��˵��
 * @author Fenglb E-mail:56553655@163.com
 * @version ����ʱ�䣺2009-4-30 ����04:13:38
 * ��˵��
 *//*

*/
/**
 * @author Fenglb E-mail:56553655@163.com
 * @version ����ʱ�䣺2009-4-30 ����04:13:38
 * ��˵��
 *//*


import com.ibm.mq.MQC;
import com.ibm.mq.MQEnvironment;
import com.ibm.mq.MQException;
import com.ibm.mq.MQGetMessageOptions;
import com.ibm.mq.MQMessage;
import com.ibm.mq.MQPutMessageOptions;
import com.ibm.mq.MQQueue;
import com.ibm.mq.MQQueueManager;

import java.io.IOException;

public class MessageByMQ {
    //������й������Ͷ��е�����
    private static String qmName;
    private static String qName;
    private static MQQueueManager qMgr;

    static {
        //���û���:
        //MQEnvironment�а�������MQQueueManager�����еĻ����Ĺ��ɵľ�̬������MQEnvironment��ֵ���趨����MQQueueManager�Ĺ��캯�����ص�ʱ�������ã�
        //��˱����ڽ���MQQueueManager����֮ǰ�趨MQEnvironment�е�ֵ.
        MQEnvironment.hostname = "esbmqdev.cnsuning.com";            //MQ��������IP��ַ
        MQEnvironment.channel = "CHANNEL_DATALEGO";            //���������ӵ�ͨ��
        MQEnvironment.CCSID = 1381;                    //������MQ����ʹ�õı���1381����GBK��1208����UTF(Coded Character Set Identifier:CCSID)
        MQEnvironment.port = 1432;                        //MQ�˿�
        qmName = "OTHER_QM";                            //MQ�Ķ��й���������
        qName = "MBF_syncSystemBD_DATALEGO";                                //MQԶ�̶��е�����
        try {
            //���岢��ʼ�����й�������������
            //MQQueueManager���Ա����̹߳������Ǵ�MQ��ȡ��Ϣ��ʱ����ͬ���ģ��κ�ʱ��ֻ��һ���߳̿��Ժ�MQͨ�š�
            qMgr = new MQQueueManager(qmName);
        } catch (MQException e) {
            // TODO Auto-generated catch block
            System.out.println("��ʹ��MQ����");
            e.printStackTrace();
        }
    }

    */
/**
     * ��MQ������Ϣ
     * @param message
     * @return
     *//*

    public static int sendMessage(String message) {
        int result = 0;
        try {
            //���ý�Ҫ���ӵĶ�������
            // Note. The MQC interface defines all the constants used by the WebSphere MQ Java programming interface
            //(except for completion code constants and error code constants).
            //MQOO_INPUT_AS_Q_DEF:Open the queue to get messages using the queue-defined default.
            //MQOO_OUTPUT:Open the queue to put messages.
             */
/*Ŀ��ΪԶ�̶��У��������ﲻ������MQOO_INPUT_AS_Q_DEF����*//*

            //int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;
             */
/*����ѡ����ʺ�Զ�̶����뱾�ض���*//*

            int openOptions = MQC.MQOO_OUTPUT | MQC.MQOO_FAIL_IF_QUIESCING;
            //���Ӷ���
            //MQQueue provides inquire, set, put and get operations for WebSphere MQ queues.
            //The inquire and set capabilities are inherited from MQManagedObject.
             */
/*�ر��˾����´�*//*

            if (qMgr == null || !qMgr.isConnected()) {
                qMgr = new MQQueueManager(qmName);
            }
            MQQueue queue = qMgr.accessQueue(qName, openOptions);
            //����һ���򵥵���Ϣ
            MQMessage putMessage = new MQMessage();
            //�����ݷ�����Ϣ������
            putMessage.writeUTF(message);
            //����д����Ϣ�����ԣ�Ĭ�����ԣ�
            MQPutMessageOptions pmo = new MQPutMessageOptions();
            //����Ϣд�����
            queue.put(putMessage, pmo);
            queue.close();
        } catch (MQException ex) {
            System.out.println("A WebSphere MQ error occurred : Completion code "
                    + ex.completionCode + " Reason code " + ex.reasonCode);
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("An error occurred whilst writing to the message buffer: " + ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                qMgr.disconnect();
            } catch (MQException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    */
/**
     * �Ӷ�����ȥ��ȡ��Ϣ�����������û����Ϣ���ͻᷢ���쳣������û�й�ϵ����TRY...CATCH������ǵ�����������÷���������޷�����˵������Ϣ
     * ���������Խ��÷�������һ������ѭ����while(true){...}֮�У�����Ҫ���õȴ�����Ϊ�ڸ÷����ڲ���û����Ϣ��ʱ����Զ��ȴ���
     * @return
     *//*

    public static String getMessage() {
        String message = null;
        try {
            //���ý�Ҫ���ӵĶ�������
            // Note. The MQC interface defines all the constants used by the WebSphere MQ Java programming interface
            //(except for completion code constants and error code constants).
            //MQOO_INPUT_AS_Q_DEF:Open the queue to get messages using the queue-defined default.
            //MQOO_OUTPUT:Open the queue to put messages.
            int openOptions = MQC.MQOO_INPUT_AS_Q_DEF | MQC.MQOO_OUTPUT;
            MQMessage retrieve = new MQMessage();
            //����ȡ����Ϣ�����ԣ�Ĭ�����ԣ�
            //Set the put message options.�����÷�����Ϣѡ�
            MQGetMessageOptions gmo = new MQGetMessageOptions();
            gmo.options = gmo.options + MQC.MQGMO_SYNCPOINT;//Get messages under sync point control����ͬ��������»�ȡ��Ϣ��
            gmo.options = gmo.options + MQC.MQGMO_WAIT;  // Wait if no messages on the Queue������ڶ�����û����Ϣ��ȴ���
            gmo.options = gmo.options + MQC.MQGMO_FAIL_IF_QUIESCING;// Fail if Qeue Manager Quiescing��������й�����ͣ����ʧ�ܣ�
            gmo.waitInterval = 1000;  // Sets the time limit for the wait.�����õȴ��ĺ���ʱ�����ƣ�
             */
/*�ر��˾����´�*//*

            if (qMgr == null || !qMgr.isConnected()) {
                qMgr = new MQQueueManager(qmName);
            }
            MQQueue queue = qMgr.accessQueue(qName, openOptions);
            // �Ӷ�����ȡ����Ϣ
            queue.get(retrieve, gmo);
            message = retrieve.readUTF();
            System.out.println("The message is: " + message);
            queue.close();
        } catch (MQException ex) {
            System.out.println("A WebSphere MQ error occurred : Completion code "
                    + ex.completionCode + " Reason code " + ex.reasonCode);
        } catch (IOException ex) {
            System.out.println("An error occurred whilst writing to the message buffer: " + ex);
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            try {
                qMgr.disconnect();
            } catch (MQException e) {
                e.printStackTrace();
            }
        }
        return message;
    }

    public static void main(String args[]) {
       // sendMessage("this is a test333");
        sendMessage("this is a test2");
       // getMessage();
    }
}

*/
