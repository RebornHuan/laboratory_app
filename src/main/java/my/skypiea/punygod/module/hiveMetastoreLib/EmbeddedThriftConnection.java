package my.skypiea.punygod.module.hiveMetastoreLib;

import org.apache.hadoop.hive.conf.HiveConf;
import org.apache.hive.service.cli.thrift.EmbeddedThriftBinaryCLIService;
import org.apache.hive.service.cli.thrift.ThriftCLIServiceClient;

/**
 * The Class EmbeddedThriftConnection.
 */
public class EmbeddedThriftConnection implements ThriftConnection {

  /** The client. */
  private ThriftCLIServiceClient client;

  /** The connected. */
  private boolean connected;

  /*
   * (non-Javadoc)
   *
   * @see org.apache.lens.driver.hive.ThriftConnection#getClient(org.apache.hadoop.hive.conf.HiveConf)
   */
  @Override
  public ThriftCLIServiceClient getClient() throws Exception {
    if (!connected) {
      client = new ThriftCLIServiceClient(new EmbeddedThriftBinaryCLIService());
      connected = true;
    }
    return client;
  }

  /*
   * (non-Javadoc)
   *
   * @see java.io.Closeable#close()
   */
  @Override
  public void close() {
    // Does nothing
  }

  @Override
  public void init(HiveConf conf, String user) {
  }
}