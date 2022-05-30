import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.nio.charset.StandardCharsets;

public class Server {
    public static void main(String[] args) throws IOException {
        DatagramSocket ds = new DatagramSocket(12345);
        while (true) {
            //创建一个数据包，用于接收数据
            byte[] bys = new byte[1024];
            DatagramPacket dp = new DatagramPacket(bys, bys.length);
            //调用DatagramSocket对象的方法接收数据
            ds.receive(dp);
            //解析数据包，并把数据在控制台显示
            String s = new String(dp.getData(), 0, dp.getLength());
            //判断是否含有攻击特征
            if (s.contains("z1")){
                DatagramPacket ret = new DatagramPacket("拒绝访问。".getBytes(StandardCharsets.UTF_8),
                        "拒绝访问。".getBytes(StandardCharsets.UTF_8).length,
                        dp.getAddress(),
                        dp.getPort());
                ds.send(ret);
            }else {
                DatagramPacket ret = new DatagramPacket("请求成功。".getBytes(StandardCharsets.UTF_8),
                        "请求成功。".getBytes(StandardCharsets.UTF_8).length,
                        dp.getAddress(),
                        dp.getPort());
                ds.send(ret);
                System.out.println("数据是：" + new String(dp.getData(), 0, dp.getLength()));
            }
        }
        //关闭接收端
//        ds.close();
    }
}
