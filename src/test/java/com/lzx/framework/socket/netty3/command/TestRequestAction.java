package com.lzx.framework.socket.netty3.command;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lzx.framework.server.IServer;
import com.lzx.framework.socket.ICommand;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.ISession;
import com.lzx.framework.socket.netty3.message.TestRequest;


@Service("testRequestAction_ActionLZX")
public class TestRequestAction implements ICommand {

	@Autowired
	private IServer server;
	@Override
	public IMessage doAction(ISession session,IMessage message) {
		TestRequest tr = (TestRequest)message;
		System.out.println("test action:" + tr.getName());
		return null;
	}

}
