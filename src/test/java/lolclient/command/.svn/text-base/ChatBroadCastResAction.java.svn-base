package lolclient.command;

import lolclient.message.ChatBroadCastRes;

import com.lzx.framework.socket.CommandEvent;
import com.lzx.framework.socket.ICommand;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.ISession;
import com.lzx.framework.utils.StringUtil;

public class ChatBroadCastResAction implements ICommand {

	@Override
	public IMessage doAction(ISession session,IMessage message) {
		ChatBroadCastRes lsr=(ChatBroadCastRes)message;
		System.out.println("ChatBroadCastRes:"+StringUtil.beanToString(lsr));
		return null;
	}



}
