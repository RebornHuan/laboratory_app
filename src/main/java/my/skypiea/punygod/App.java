package my.skypiea.punygod;


import com.google.common.base.Preconditions;
import com.sun.tools.javac.code.Attribute;

import java.util.HashSet;
import java.util.Set;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {

        FileType file = FileType.valueOfa(" ".trim());

        System.out.println(file);

    }


    public enum FileType {

        PYTHON, SQL, JAR, XML, SHELL;


        public static FileType valueOfa(String value) {
           Preconditions.checkArgument(value != null, "file suffix can not be null");
            value = value.toLowerCase();
            switch (value) {
                case ".py":
                    return PYTHON;
                case ".sql":
                    return SQL;
                case ".jar":
                    return JAR;
                case ".xml":
                    return XML;
                case ".sh":
                    return SHELL;
                case "":
                    return SHELL;
                default:
                    throw new IllegalArgumentException("invalid FileType " + value);
            }
        }

    }
}
