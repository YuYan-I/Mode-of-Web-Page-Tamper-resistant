import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class BackupMode {
    public static void main(String[] args) throws IOException {
        //将备份文件读取到内存中
        File fileBcakup = new File("D:\\bsxt\\backups\\新建文本文档.txt");
        FileInputStream fis = new FileInputStream(fileBcakup);

        //删除已经被修改的文件
        File file = new File("D:\\bsxt\\Test\\新建文本文档.txt");
        if (file.isFile()){
            file.delete();
        }
        //将备份文件放到被篡改文件的位置
        FileOutputStream fos = new FileOutputStream(file);
        //读写数据，复制文本文件(一次读取一个字节，一次写入一个字节)
        int by;
        while ((by=fis.read())!=-1) {
            fos.write(by);
        }

        //释放资源
        fos.close();
        fis.close();
    }
}
