package com.lzx.framework.socket.netty3.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzx.framework.server.IServer;
import com.lzx.framework.socket.CommandEvent;
import com.lzx.framework.socket.ICommand;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.ISession;
import com.lzx.framework.socket.netty3.message.ChatRequest;

@Service("chatRequestAction_ActionLZX")
public class ChatRequestAction  implements ICommand {

	@Autowired
	private IServer server;
	
	@Override
	public IMessage doAction(ISession session,IMessage message) {
		ChatRequest tr = (ChatRequest) message;
		
		System.out.println("test chat:" + tr.getMsg()+",serverName:"+server.getName());
		return null;
	}

	
	public IServer getServer() {
		return server;
	}

	public void setServer(IServer server) {
		this.server = server;
	}

}
