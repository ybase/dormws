package com.ybase.dorm.web;

import javax.servlet.http.HttpServletRequest;

import org.apache.catalina.websocket.StreamInbound;
import org.apache.catalina.websocket.WebSocketServlet;
import org.apache.log4j.Logger;

import com.ybase.bas.util.MessageUtil;
import com.ybase.dorm.vo.DrUser;

/**
 * 网站socket server<br/>
 *
 * @dormitory_V1.0, yangxb, 2014-7-16<br/>
 */
@SuppressWarnings("deprecation")
public class DormWSServer extends WebSocketServlet {

	private static final long serialVersionUID = -1174784249481264789L;
	private static final Logger log = Logger.getLogger(DormWSServer.class.getName());
	public static int ONLINE_USER_COUNT = 1;

	// 跟平常Servlet不同的是，需要实现createWebSocketInbound，在这里初始化自定义的WebSocket连接对象
	@Override
	protected StreamInbound createWebSocketInbound(String subProtocol, HttpServletRequest request) {
		DrUser user = (DrUser) request.getSession().getAttribute("loginusr");
		if (user != null) {
			ONLINE_USER_COUNT++;
			return new WebSocketMessageInbound(user);
		} else {
			log.info(MessageUtil.getCommText("ws_web_dwsserver_loginusrnull"));
			return null;
		}
	}

}
