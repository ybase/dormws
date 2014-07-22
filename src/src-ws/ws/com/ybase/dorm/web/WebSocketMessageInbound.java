package com.ybase.dorm.web;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;

import net.sf.json.JSONObject;

import org.apache.catalina.websocket.MessageInbound;
import org.apache.catalina.websocket.WsOutbound;
import org.apache.log4j.Logger;

import com.ybase.bas.util.MessageUtil;
import com.ybase.dorm.constants.WsConstants;
import com.ybase.dorm.vo.DrUser;

/**
 * 类说明<br/>
 *
 * @dorm_V1.0, yangxb, 2014-7-22<br/>
 */
@SuppressWarnings("deprecation")
public class WebSocketMessageInbound extends MessageInbound {

	private static final Logger log = Logger.getLogger(WebSocketMessageInbound.class.getName());

	/** 当前连接的用户 */
	private final DrUser user;

	public WebSocketMessageInbound(DrUser user) {
		this.user = user;
	}

	public DrUser getUser() {
		return this.user;
	}

	// 建立连接的触发的事件
	@Override
	protected void onOpen(WsOutbound outbound) {
		if (WebSocketMessageInboundPool.containUser(getUser())) {
			return;
		}

		// 触发连接事件，在连接池中添加连接
		JSONObject result = new JSONObject();
		result.element(WsConstants.WS_RST_TYPE, WsConstants.WS_RST_TYPE_ONLINE);
		result.element("user", getUser());
		// 向所有在线用户推送当前用户上线的消息
		WebSocketMessageInboundPool.sendMessage(result.toString());

		result = new JSONObject();
		result.element(WsConstants.WS_RST_TYPE, WsConstants.WS_RST_TYPE_ONL_NUM);
		result.element("list", WebSocketMessageInboundPool.getOnlineUser());
		// 向连接池添加当前的连接对象
		WebSocketMessageInboundPool.addMessageInbound(this);
		// 向当前连接发送当前在线用户的列表
		WebSocketMessageInboundPool.sendMessageToUser(getUser(), result.toString());
	}

	@Override
	protected void onClose(int status) {
		// 触发关闭事件，在连接池中移除连接
		WebSocketMessageInboundPool.removeMessageInbound(this);
		JSONObject result = new JSONObject();
		result.element(WsConstants.WS_RST_TYPE, WsConstants.WS_RST_TYPE_OFFLINE);
		result.element("user", getUser());
		// 向在线用户发送当前用户退出的消息
		WebSocketMessageInboundPool.sendMessage(result.toString());
	}

	@Override
	protected void onBinaryMessage(ByteBuffer message) throws IOException {
		log.error(MessageUtil.getCommText("ws_web_wmi_binns"));
		throw new UnsupportedOperationException(MessageUtil.getCommText("ws_web_wmi_binns"));
	}

	// 客户端发送消息到服务器时触发事件
	@Override
	protected void onTextMessage(CharBuffer message) throws IOException {
		// 向所有在线用户发送消息
		WebSocketMessageInboundPool.sendMessage(message.toString());
	}
}
