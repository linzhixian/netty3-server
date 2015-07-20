package lolclient.command;

import lolclient.message.UserOnlineStatusRes;

import com.lzx.framework.socket.ICommand;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.ISession;

public class UserOnlineStatusResAction implements ICommand {

	@Override
	public IMessage doAction(ISession session,IMessage message) {
		UserOnlineStatusRes res=(UserOnlineStatusRes)message;
		System.out.println("UserOnlineStatusResAction:"+res.getUser().getUserId()+":oline="+res.isOnline());
		return null;
	}

}
