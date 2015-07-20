package lolclient.message;

import server.util.protobuf.ChatMessageContent;

import com.lzx.framework.socket.AbstractMessage;

public class ChatBroadCastRes extends AbstractMessage {
	private int channelID;
	private String content;
	private ChatUser fromUser;
	private ChatUser toUser;
	private int chatType; // 0:普通；1:系统
	
	public ChatBroadCastRes() {
		this.setCmd(1724);
	}
	@Override
	public byte[] encode() {
		ChatMessageContent.ChatBroadCastRes.Builder b=ChatMessageContent.ChatBroadCastRes.newBuilder();
		b.setChannelID(channelID);
		b.setContent(this.content);
		b.setChatType(this.chatType);
		
		ChatMessageContent.ChatUser.Builder pfromUser=ChatMessageContent.ChatUser.newBuilder();
		pfromUser.setUsername(fromUser.getUsername());
		pfromUser.setUserid(fromUser.getUserId());
		pfromUser.setSex(fromUser.isSex());
		b.setFromUser(pfromUser);
		if(this.toUser!=null) {
			ChatMessageContent.ChatUser.Builder pToUser=ChatMessageContent.ChatUser.newBuilder();
			pToUser.setUsername(toUser.getUsername());
			pToUser.setUserid(toUser.getUserId());
			pToUser.setSex(toUser.isSex());
			b.setToUser(pToUser);
		}
		
		return b.build().toByteArray();
	}

	@Override
	public void decode(byte[] content) throws Exception {
		ChatMessageContent.ChatBroadCastRes res=ChatMessageContent.ChatBroadCastRes.parseFrom(content);
		this.channelID=res.getChannelID();
		this.chatType=res.getChatType();
		this.content=res.getContent();
		this.fromUser=new ChatUser();
		fromUser.setUserId(res.getFromUser().getUserid());
		fromUser.setUsername(res.getFromUser().getUsername());
		fromUser.setSex(res.getFromUser().getSex());
		if(res.getToUser()!=null) {
			this.toUser=new ChatUser();
			toUser.setUserId(res.getToUser().getUserid());
			toUser.setUsername(res.getToUser().getUsername());
			toUser.setSex(res.getToUser().getSex());
		}
		
	}
	public int getChannelID() {
		return channelID;
	}
	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public ChatUser getFromUser() {
		return fromUser;
	}
	public void setFromUser(ChatUser fromUser) {
		this.fromUser = fromUser;
	}
	public ChatUser getToUser() {
		return toUser;
	}
	public void setToUser(ChatUser toUser) {
		this.toUser = toUser;
	}
	public int getChatType() {
		return chatType;
	}
	public void setChatType(int chatType) {
		this.chatType = chatType;
	}

}
