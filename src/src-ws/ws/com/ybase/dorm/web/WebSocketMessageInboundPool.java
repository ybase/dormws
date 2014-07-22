package com.ybase.dorm.web;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;

import com.ybase.bas.util.MessageUtil;
import com.ybase.dorm.vo.DrUser;

/**
 * 类说明<br/>
 *
 * @dorm_V1.0, yangxb, 2014-7-22<br/>
 */
public class WebSocketMessageInboundPool {

	private static final Logger log = Logger.getLogger(WebSocketMessageInboundPool.class.getName());
	// 保存连接的MAP容器
	private static final Map<String, WebSocketMessageInbound> connections = new HashMap<String, WebSocketMessageInbound>();

	// 向连接池中添加连接
	public static void addMessageInbound(WebSocketMessageInbound inbound) {
		// 添加连接
		log.info(MessageUtil.getCommText("ws_web_wnipool_online", inbound.getUser().getId(), inbound.getUser().getName()));
		if (!connections.containsKey(String.valueOf(inbound.getUser().getId()))) {
			connections.put(String.valueOf(inbound.getUser().getId()), inbound);
		}
	}

	public static boolean containUser(DrUser user) {
		if (user != null) {
			return connections.containsKey(String.valueOf(user.getId()));
		}
		return true;
	}

	// 获取所有的在线用户
	public static Set<String> getOnlineUser() {
		Set<String> nameSet = new HashSet<String>();
		Set<String> keys = connections.keySet();
		for (Iterator<String> iter = keys.iterator(); iter.hasNext();) {
			WebSocketMessageInbound wmi = connections.get(iter.next());
			nameSet.add(wmi.getUser().getName());
		}
		return nameSet;
	}

	public static void removeMessageInbound(WebSocketMessageInbound inbound) {
		// 移除连接
		log.info(MessageUtil.getCommText("ws_web_wnipool_offline", inbound.getUser().getId(), inbound.getUser().getName()));
		connections.remove(inbound.getUser());
	}

	public static void sendMessageToUser(DrUser user, String message) {
		try {
			// 向特定的用户发送数据
			log.info(MessageUtil.getCommText("ws_web_wnipool_sndmsgtousr", user.getId(), user.getName(), message));
			WebSocketMessageInbound inbound = connections.get(String.valueOf(user.getId()));
			if (inbound != null) {
				inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}

	// 向所有的用户发送消息
	public static void sendMessage(String message) {
		try {
			Set<String> keySet = connections.keySet();
			for (String key : keySet) {
				WebSocketMessageInbound inbound = connections.get(key);
				if (inbound != null) {
					log.info(MessageUtil.getCommText("ws_web_wnipool_sndmsgtousr", inbound.getUser().getId(), inbound.getUser().getName(), message));
					inbound.getWsOutbound().writeTextMessage(CharBuffer.wrap(message));
				}
			}
		} catch (IOException e) {
			log.error(e.getMessage(), e);
		}
	}
}
