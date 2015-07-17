package lolclient;

import lolclient.command.ChatBroadCastResAction;
import lolclient.command.EnterChatChannelResAction;
import lolclient.command.LoginSucessResAction;
import lolclient.command.UserOnlineStatusResAction;
import lolclient.message.ChatBroadCastRes;
import lolclient.message.EnterChatChannelRes;
import lolclient.message.LoginReq;
import lolclient.message.LoginSucessRes;
import lolclient.message.UserOnlineStatusRes;

import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.MessageDispatch;
import com.lzx.framework.socket.netty3.Netty3LolClient;

public class LolClient {

	final Netty3LolClient client;
	private int id;
	
	public LolClient(String host, int port) {
		
		MessageDispatch messageDispath = new MessageDispatch();
		messageDispath.registerMessage(2, LoginSucessRes.class, new LoginSucessResAction());
		messageDispath.registerMessage(1721, EnterChatChannelRes.class, new EnterChatChannelResAction());
		messageDispath.registerMessage(1724, ChatBroadCastRes.class, new ChatBroadCastResAction());
		messageDispath.registerMessage(1725, UserOnlineStatusRes.class, new UserOnlineStatusResAction());
		client = new Netty3LolClient(host, port, messageDispath);
	}




	public boolean connect() {
		return client.connect();
	}

	public void login(int id) {
		this.id=id;
		LoginReq loginReq=new LoginReq("lzx");
		client.send(loginReq);
	}
	public void send(IMessage msg) {
		this.client.send(msg);
	}




	public int getId() {
		return id;
	}




	public void setId(int id) {
		this.id = id;
	}
}
