/*
package my.skypiea.punygod.module.jarsLib;

*/
/**
 * *****************************
 * **      Just For           **
 * **             Puny God    **
 * *****************************
 * Created by Reborn on 2016/3/18.
 *//*

import java.io.IOException;
import java.net.URL;
import java.net.URLDecoder;
import java.util.Enumeration;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


*/
/**
 * Utility class; returns the locations of various jars.
 *//*

public final class Jars {

    public static final Log LOG = LogFactory.getLog(
            Jars.class.getName());

    private Jars() {
    }

    */
/**
     * @return the path to the main Sqoop jar.
     *//*

    public static String getSqoopJarPath() {
        return getJarPathForClass(Jars.class);
    }

    */
/**
     * Return the jar file path that contains a particular class.
     * Method mostly cloned from o.a.h.mapred.JobConf.findContainingJar().
     *//*

    public static String getJarPathForClass(Class<? extends Object> classObj) {
        ClassLoader loader = classObj.getClassLoader();
        String classFile = classObj.getName().replaceAll("\\.", "/") + ".class";
        try {
            for (Enumeration<URL> itr = loader.getResources(classFile);
                 itr.hasMoreElements();) {
                URL url = (URL) itr.nextElement();
                if ("jar".equals(url.getProtocol())) {
                    String toReturn = url.getPath();
                    if (toReturn.startsWith("file:")) {
                        toReturn = toReturn.substring("file:".length());
                    }
                    // URLDecoder is a misnamed class, since it actually decodes
                    // x-www-form-urlencoded MIME type rather than actual
                    // URL encoding (which the file path has). Therefore it would
                    // decode +s to ' 's which is incorrect (spaces are actually
                    // either unencoded or encoded as "%20"). Replace +s first, so
                    // that they are kept sacred during the decoding process.
                    toReturn = toReturn.replaceAll("\\+", "%2B");
                    toReturn = URLDecoder.decode(toReturn, "UTF-8");
                    return toReturn.replaceAll("!.*$", "");
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    */
/**
     * Return the path to the jar containing the JDBC driver
     * for a ConnManager.
     *//*

    public static String getDriverClassJar(ConnManager mgr) {
        if (null == mgr) {
            return null;
        }

        String driverClassName = mgr.getDriverClass();
        if (null == driverClassName) {
            return null;
        }

        try {
            Class<? extends Object> driverClass = Class.forName(driverClassName);
            return getJarPathForClass(driverClass);
        } catch (ClassNotFoundException cnfe) {
            LOG.warn("No such class " + driverClassName + " available.");
            return null;
        }
    }

}
*/
