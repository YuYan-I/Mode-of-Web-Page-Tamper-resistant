import org.apache.commons.codec.digest.DigestUtils;

import java.io.*;
import java.util.Properties;

public class Test {
    public static void main(String[] args) throws IOException {

        File file = new File("D:\\bsxt\\Test");
        Properties prop = new Properties();
        FileReader fr = new FileReader("D:\\bsxt\\SelfApp\\prop1.properties");

        ScanMain.ScanMethod(file,prop,fr);
        fr.close();


    }
}
