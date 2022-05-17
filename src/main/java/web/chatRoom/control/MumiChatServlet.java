package web.chatRoom.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.websocket.CloseReason;
import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.PathParam;
import javax.websocket.server.ServerEndpoint;

import com.google.gson.Gson;

import redis.clients.jedis.Jedis;
import web.chatRoom.entity.ChatModel;

@ServerEndpoint("/MumiChat/{userName}")
public class MumiChatServlet {
	private static final Set<Session> connectedSessions = Collections.synchronizedSet(new HashSet<>());
	Gson gson = new Gson();

	/*
	 * 如果想取得HttpSession與ServletContext必須實作
	 * ServerEndpointConfig.Configurator.modifyHandshake()，
	 * 參考https://stackoverflow.com/questions/21888425/accessing-servletcontext-and-
	 * httpsession-in-onmessage-of-a-jsr-356-serverendpoint
	 */
	@OnOpen
	public void onOpen(@PathParam("userName") String userName, Session userSession) throws IOException {
		Jedis jedis = new Jedis("localhost", 6379);
		connectedSessions.add(userSession);
		String text = String.format("Session ID = %s, connected; userName = %s", userSession.getId(), userName);
		System.out.println(text);
			List<String> message = jedis.lrange("chatRoom", 0, -1);
			String historyMsg = gson.toJson(message);
			StringBuilder sb = new StringBuilder("{"+"\"type\""+":"+"\"history\""+","+"\"message\""+":"+historyMsg);
//			for(String str : message ) {
//				ChatModel model = gson.fromJson(str,ChatModel.class);
//				System.out.println("確認MODEL:" + model);
//				sb.append(model.getMessage()+",");
//			}
//			sb.deleteCharAt(sb.length() - 1);
			sb.append("}");
			System.out.println("確認加工後的JSON物件"+sb);
			if(historyMsg != null && historyMsg != "")
			{
				System.out.println(historyMsg);
				ChatModel model = new ChatModel("history", "", historyMsg);
				System.out.println("歷史訊息整理後傳回前端 :" + model);
				userSession.getAsyncRemote().sendText(sb.toString());
								
			}
		
		jedis.close();
	}

	@OnMessage
	public void onMessage(Session userSession, String message) {
		ChatModel model = gson.fromJson(message, ChatModel.class);
		System.out.println("後端接收資料轉換model : " + model);
		if ("chat".equals(model.getType())) {
			for (Session session : connectedSessions) {
				if (session.isOpen())
					session.getAsyncRemote().sendText(message);
				Jedis jedis = new Jedis("localhost", 6379);
				model.setType("history");
				model.setType("history");
				System.out.println("後端接收資料轉換model型別存入 : " + model);
				jedis.rpush("chatRoom", gson.toJson(model));
				jedis.close();
			}
		}

		System.out.println("Message received: " + message);
	}

	@OnClose
	public void onClose(Session userSession, CloseReason reason) {
		connectedSessions.remove(userSession);
		String text = String.format("session ID = %s, disconnected; close code = %d; reason phrase = %s",
				userSession.getId(), reason.getCloseCode().getCode(), reason.getReasonPhrase());
		System.out.println(text);
	}

	@OnError
	public void onError(Session userSession, Throwable e) {
		System.out.println("Error: " + e.toString());
	}

}
