package my.skypiea.punygod.module.hiveMetastoreLib;

import java.io.Closeable;


import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hive.service.cli.CLIServiceClient;

/**
 * The Interface ThriftConnection.
 */
public interface ThriftConnection extends Closeable {

  /**
   * Gets the client.
   *
   * @return the client
   * @throws Exception the exception
   */
  CLIServiceClient getClient() throws Exception;

  /**
   * Initializes connection with conf.
   *
   * @param conf
   * @param user
   */
  void init(HiveConf conf, String user);
}