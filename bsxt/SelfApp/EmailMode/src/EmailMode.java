import com.sun.xml.internal.messaging.saaj.packaging.mime.MessagingException;

import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.FileReader;
import java.io.IOException;
import java.io.Reader;
import java.util.Properties;

public class EmailMode {
    public static void main(String[] args) throws Exception {
        FileReader fr = new FileReader("D:\\bsxt\\SelfApp\\property.properties");
        SendMail(fr);
    }
    private static void SendMail(Reader fr) throws IOException, javax.mail.MessagingException {
        // 1. 创建参数配置, 用于连接邮件服务器的参数配置
        Properties props = new Properties();                  // 参数配置
        props.load(fr);
        // 2. 创建一个Session对象，用于和发送邮件程序进行交互
        Session session = Session.getDefaultInstance(props);
        // 3. 创建一封邮件
        MimeMessage message = new MimeMessage(session) ;
        message.setFrom(new InternetAddress(props.getProperty("form")));                                                  // 设置发件人邮箱地址
        message.setRecipient(MimeMessage.RecipientType.TO, new InternetAddress(props.getProperty("to")));                // 设置收件人邮箱地址
        message.setSubject("入侵告警");                                                // 设置邮箱主题
        message.setContent("您的网站正在遭受攻击。", "text/html;charset=UTF-8"); // 设置邮箱正文
        // 4. 根据 Session 获取邮件传输对象
        Transport transport = session.getTransport();
        // 5、建立连接
        transport.connect(props.getProperty("form"), "这里需要更改为邮箱授权码");
        // 6. 发送邮件, 发到所有的收件地址, message.getAllRecipients() 获取到的是在创建邮件对象时添加的所有收件人, 抄送人, 密送人
        transport.sendMessage(message, message.getAllRecipients());
        // 7. 关闭连接
        transport.close();
    }
}
