package lolclient;

import java.util.ArrayList;
import java.util.List;

import lolclient.message.EnterChatChannelReq;
import lolclient.message.SendChatReq;

public class ClientsSuit {


	public static void main(String[] args) {
		int clientCount=1;
		String host="127.0.0.1";
		int port=8080;//
				
		List<LolClient> clientList=new ArrayList<LolClient>();
		
		for(int i=1;i<=clientCount;i++) {
			
			LolClient client=new LolClient(host,port);
			boolean isSucess=client.connect();
			if(isSucess) {
				clientList.add(client);
				client.login(i);

				
				
			}
		}
		try {
			Thread.sleep(1000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		for(LolClient client:clientList) {
			EnterChatChannelReq req=new EnterChatChannelReq(0);
			client.send(req);
		}
		for(LolClient client:clientList) {
			SendChatReq req=new SendChatReq();
			req.setChannelID(0);
			req.setContent("hello word from "+client.getId());
			req.setChatType(0);
			client.send(req);
			if(client.getId()==0) {
				client.client.close();
			}
		}
	}

}
