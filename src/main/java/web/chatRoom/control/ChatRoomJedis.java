package web.chatRoom.control;

import java.util.List;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;

public class ChatRoomJedis {
//	public static void main(String[] args) {
//		ChatRoomJedis js = new ChatRoomJedis();
//		js.saveChatMessage("HAHAHA");
//	}
	private static JedisPool pool = JedisPoolUtil.getJedisPool();

	public static List<String> getHistoryMsg() {
		String key = "chatRoom";
		Jedis jedis = null;
		jedis = pool.getResource();
		List<String> historyData = jedis.lrange(key, 0, -1);
		jedis.close();
		return historyData;
	}

	public static void saveChatMessage(String message) {
		// 對雙方來說，都要各存著歷史聊天記錄
		Jedis jedis = pool.getResource();
		jedis.rpush("chatRoom",message);


		jedis.close();
	}

}
