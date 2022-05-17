package web.chatRoom.entity;

import java.util.List;

import redis.clients.jedis.Jedis;

public class ChatModel {
	private String type;
	private String userName;
	private String message;

	public ChatModel(String type, String userName, String message) {
		this.type = type;
		this.userName = userName;
		this.message = message;
	}

	@Override
	public String toString() {
		return "ChatModel [type=" + type + ", userName=" + userName + ", message=" + message + "]";
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
