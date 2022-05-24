package web.member.controller;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import web.member.dao.MemDAO;

@WebServlet(urlPatterns = { "/member/SendEmail" })
public class SendEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		
		req.setCharacterEncoding("UTF-8");
	    res.setContentType("text/html; charset=UTF-8");
		
		
		String action = req.getParameter("action");
		String mem_account = req.getParameter("mem_account"); // 使用者信箱
		String errMessage;
		
		MemDAO dao = new MemDAO();
		String newPwd = genAuthCode();
		
		if("forget".equals(action)) {
		
			try {
				// 設定使用SSL連線至 Gmail smtp Server
				Properties props = new Properties();
				props.put("mail.smtp.host", "smtp.gmail.com");
				props.put("mail.smtp.socketFactory.port", "465");
				props.put("mail.smtp.socketFactory.class","javax.net.ssl.SSLSocketFactory");
				props.put("mail.smtp.auth", "true");
				props.put("mail.smtp.port", "465");
				
				// ●設定 gmail 的帳號 & 密碼 (將藉由你的Gmail來傳送Email)
				// ●須將myGmail的【安全性較低的應用程式存取權】打開
				final String myGmail = "tga101mumimovie@gmail.com";
				final String myGmail_password = "mumimovie";
				Session session = Session.getInstance(props, new Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(myGmail, myGmail_password);
					}
				});
				
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(myGmail));
				message.setRecipients(Message.RecipientType.TO,InternetAddress.parse(mem_account));
				
				//設定信中的主旨  
				message.setSubject("請確認你的新帳號密碼");
				
				// 呼叫DAO更新帳號密碼(英數亂數8碼)
				dao.changePWD(mem_account, newPwd);
				
				//設定信中的內容 
				message.setText("您的新密碼為 : " + newPwd);
				
				Transport.send(message);
				
				res.sendRedirect(req.getContextPath() + "/view/index/index.jsp");
				
			}catch (MessagingException e){
				e.printStackTrace();
			}
		}
		
		
		if ("checkaccount".equals(action)) {
			
			try {
				if (dao.checkAccount(mem_account) == null) {
					errMessage = "查無您的帳號!!";
				} 
			} catch (Exception e) {
				// TODO: handle exception
			}
			
		}

	}
	
	// 隨機產生英數大小寫混和字串
	public String genAuthCode() {
		String randomCode = "";
		String result = "";

		for(int i = 0; i < 10; i++) {
			randomCode += i;
		}
		for(int i = 65; i < 91; i++) {
			randomCode += (char)i;
		}
		for(int i = 97; i < 123; i++) {
			randomCode += (char)i;
		}
		
		char[] codes = randomCode.toCharArray();
		// 隨機選號印出
		for(int i = 0; i < 8; i++) {
			result += codes[(int)(Math.random() * 62)];
		}
		
		return result;
	}
}
