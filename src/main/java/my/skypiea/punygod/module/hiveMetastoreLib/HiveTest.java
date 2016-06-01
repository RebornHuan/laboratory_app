package my.skypiea.punygod.module.hiveMetastoreLib;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hadoop.hive.metastore.HiveMetaStoreClient;
import org.apache.hadoop.hive.metastore.IMetaStoreClient;
import org.apache.hadoop.hive.metastore.TableType;
import org.apache.hadoop.hive.metastore.api.Database;
import org.apache.hadoop.hive.ql.metadata.Hive;
import org.apache.hadoop.hive.ql.metadata.HiveException;
import org.apache.hadoop.hive.ql.metadata.Table;
import org.apache.hadoop.security.UserGroupInformation;
import org.apache.hive.service.cli.CLIService;
import org.apache.hive.service.cli.CLIServiceClient;
import org.apache.hive.service.cli.OperationHandle;
import org.apache.hive.service.cli.SessionHandle;

import java.io.IOException;
import java.io.InputStream;
import java.security.PrivilegedExceptionAction;
import java.util.Map;

import static org.apache.hadoop.hive.serde.serdeConstants.SERIALIZATION_FORMAT;

/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/15.
 */
public class HiveTest {

    public static void main(String[] args) throws Exception {


//        System.out.println(System.getProperty("user.name"));
//
//        System.setProperty("user.name","bi");
//
//        System.out.println(System.getProperty("user.name"));
//
//        Configuration hadoopConf = new Configuration();
//
//        InputStream inputStream = HiveTest.class
//                .getClassLoader().getResourceAsStream("hive-site.xml");
//
//        if(inputStream!=null) {
//            hadoopConf.addResource(inputStream);
//        }
//
//        HiveConf hiveConf = new HiveConf(hadoopConf,HiveTest.class);
//
//        hiveConf.set("","");
//
//        hiveConf.addResource("hive-site.xml");
//
//        System.out.println(hiveConf.getAllProperties().toString());

/*        HiveConf hiveConf = new HiveConf();


 /*       System.out.println(hiveConf.get("javax.jdo.option.ConnectionURL"));*//*
        HiveConf hiveConf = new HiveConf();
        HiveMetaStoreClient hiveMetaStoreClient = new HiveMetaStoreClient(hiveConf);
        org.apache.hadoop.hive.metastore.api.Table tTable = hiveMetaStoreClient.getTable("bi_sor", "tsor_saled_zt2032_d");


        // For non-views, we need to do some extra fixes
        if (!TableType.VIRTUAL_VIEW.toString().equals(tTable.getTableType())) {
            // Fix the non-printable chars
            Map<String, String> parameters = tTable.getSd().getParameters();
            String sf = parameters.get(SERIALIZATION_FORMAT);
            if (sf != null) {
                char[] b = sf.toCharArray();
                if ((b.length == 1) && (b[0] < 10)) { // ^A, ^B, ^C, ^D, \t
                    parameters.put(SERIALIZATION_FORMAT, Integer.toString(b[0]));
                }
            }

            // Use LazySimpleSerDe for MetadataTypedColumnsetSerDe.
            // NOTE: LazySimpleSerDe does not support tables with a single column of
            // col
            // of type "array<string>". This happens when the table is created using
            // an
            // earlier version of Hive.
            if (org.apache.hadoop.hive.serde2.MetadataTypedColumnsetSerDe.class
                    .getName().equals(
                            tTable.getSd().getSerdeInfo().getSerializationLib())
                    && tTable.getSd().getColsSize() > 0
                    && tTable.getSd().getCols().get(0).getType().indexOf('<') == -1) {
                tTable.getSd().getSerdeInfo().setSerializationLib(
                        org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe.class.getName());
            }
        }

        Table table = new Table(tTable);
        System.out.println(table.getAllCols());
        System.out.println(tTable);*/

/*
        CLIService cliService = new CLIService();
        cliService.init(hiveConf);
        cliService.start();
*/



  /*    EmbeddedThriftConnection embeddedThriftConnection = new EmbeddedThriftConnection();
        embeddedThriftConnection.init(hiveConf, null);
        CLIServiceClient client =  embeddedThriftConnection.getClient();


        SessionHandle sessionHandle = client.openSession("bi",null);

        HiveConf hiveConf1 = cliService.getSessionManager().getSession(sessionHandle).getHiveConf();*/



        HiveConf hiveConf = new HiveConf();
        Hive hive = Hive.get(hiveConf);

       // hive.dropTable("default" , "whtest");

        System.out.println("end");

        Table table1 = hive.getTable("bi_sor" , "tsor_saled_zt2032_d");


/*        HiveConf hiveConf = new HiveConf();
        TestHive hive = TestHive.get(hiveConf);

        // hive.dropTable("default" , "whtest");

        System.out.println("end");

        Table table1 = hive.getTable("bi_sor" , "tsor_saled_zt2032_d");

        System.out.println(table1.getMetadata());*/


    /*    Table table = hive.getTable("default" , "zpc2");

        System.out.println(table.canWrite());
        System.out.println(table.canDrop());

        System.out.println(table.getParameters().get("wanghuan"));

        System.out.println(table.getLastAccessTime());

        long time = System.currentTimeMillis();

        System.out.println("time : "+time);

        table.setLastAccessTime((int)time);

        table.setSerdeParam("wanghuan1","test");

        hive.alterTable("default.zpc2",table);

        System.out.println(hive.getAllTables("aps"));

        System.out.println(hive.getAllDatabases());*/
    }
}
