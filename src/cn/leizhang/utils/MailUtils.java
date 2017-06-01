package cn.leizhang.utils;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMessage.RecipientType;

/**
 * 发送邮件的工具类
 * @author 传智.郭嘉
 *
 */
public class MailUtils {
	public static void sendMail(String to,String code) throws Exception{
		Properties props = new Properties();
		props.setProperty("mail.smtp", "localhost");
		// 1.Session对象.连接(与邮箱服务器连接)
		Session session = Session.getInstance(props, new Authenticator() {

			@Override
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication("zl_hebei@126.com", "123");
			}
			
		});
		
		// 2.构建邮件信息:
		Message message = new MimeMessage(session);
		// 发件人:
		message.setFrom(new InternetAddress("zl_hebei@126.com"));
		// 收件人:
		message.setRecipient(RecipientType.TO, new InternetAddress(to));
		// 设置标题
		message.setSubject("来自SHOP激活邮件");
		// 设置正文
		message.setContent("<h1>来自SHOP的官网激活邮件</h1><h3><a href='http://192.168.40.99:8080/shop/user_active.action?code="+code+"'>http://192.168.40.99:8080/shop/user_active.action?code="+code+"</a></h3>", "text/html;charset=UTF-8");
	
		// 3.发送对象
		Transport.send(message);
	}
}
