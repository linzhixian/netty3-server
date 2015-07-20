package lolclient.command;

import lolclient.message.LoginSucessRes;

import com.lzx.framework.socket.CommandEvent;
import com.lzx.framework.socket.ICommand;
import com.lzx.framework.socket.IMessage;
import com.lzx.framework.socket.ISession;

public class LoginSucessResAction implements ICommand {



	public LoginSucessResAction() {

	}

	@Override
	public IMessage doAction(ISession session,IMessage message) {
		LoginSucessRes lsr=(LoginSucessRes)message;
		System.out.println("loginSucess:id="+lsr.getId());
		return null;
	}

}
