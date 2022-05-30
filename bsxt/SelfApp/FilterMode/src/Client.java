import java.io.IOException;
import java.net.*;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) throws IOException {

        //创建发送端的Socket对象(DatagramSocket)
        DatagramSocket ds = new DatagramSocket();
        //键盘录入数据
        Scanner sc = new Scanner(System.in);
        while (true) {
            String s = sc.nextLine();
            //输入的数据是886，发送数据结束
            if ("886".equals(s)) {
                break;
            }
            //创建数据，并把数据打包
            byte[] bys = s.getBytes();
            DatagramPacket dp = new DatagramPacket(bys, bys.length, InetAddress.getByName("127.0.0.1"), 12345);
            ds.send(dp);

            //等待时间
            ds.setSoTimeout(1000);
            byte[] bys1 = new byte[1024];
            DatagramPacket rec = new DatagramPacket(bys1, bys1.length);
            ds.receive(rec);
            System.out.println(new String(rec.getData(), 0, rec.getLength()));
        }
        //关闭发送端
        ds.close();
    }
}
