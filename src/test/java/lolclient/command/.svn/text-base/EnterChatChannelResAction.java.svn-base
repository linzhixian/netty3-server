package lolclient.command;

import lolclient.message.EnterChatChannelRes;

import com.lzx.framework.socket.CommandEvent;
import com.lzx.framework.socket.ICommand;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.ISession;
import com.lzx.framework.utils.StringUtil;

public class EnterChatChannelResAction implements ICommand {

	@Override
	public IMessage doAction(ISession session,IMessage message) {
		EnterChatChannelRes lsr=(EnterChatChannelRes)message;
		System.out.println("EnterChatChannelResAction:"+StringUtil.beanToString(lsr));
		return null;
	}



}
