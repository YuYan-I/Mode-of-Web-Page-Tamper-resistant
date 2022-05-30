import org.apache.commons.codec.digest.DigestUtils;
import java.io.*;
import java.util.Properties;
public class ScanMain {
    public static void ScanMethod(File file, Properties properties,FileReader fr) throws IOException {
        properties.load(fr);
        File[] files = file.listFiles();
        for (File file1 : files) {
            if (file1.isFile()){
                FileInputStream fis =  new FileInputStream(file1);

//                properties.setProperty(file1.getAbsolutePath()+" "+file1.getName(),DigestUtils.md5Hex(fis));
//                FileWriter fw = new FileWriter("D:\\bsxt\\SelfApp\\prop1.properties");
//                properties.store(fw,null);
//                fw.close();

                String s = properties.getProperty(file1.getAbsolutePath()+" "+file1.getName());
                if (DigestUtils.md5Hex(fis).equals(s)){
                    System.out.println(file1.getAbsolutePath()+"  文件未被篡改！");
                }else {
                    System.out.println(file1.getAbsolutePath()+"  文件已经被篡改！");
                }
                fis.close();
            }else {
                ScanMethod(file1,properties,fr);
            }
        }
    }
}
