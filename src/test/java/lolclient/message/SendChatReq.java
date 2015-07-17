package lolclient.message;

import server.util.protobuf.ChatMessageContent;

import com.lzx.framework.socket.AbstractMessage;

public class SendChatReq extends AbstractMessage {

	private int channelID;
	private String content;
	private ChatUser toUser;
	private int chatType; // 0:普通；1:系统

	public SendChatReq() {
		this.setCmd(1723);
	}

	@Override
	public byte[] encode() {
		ChatMessageContent.SendChatReq.Builder b = ChatMessageContent.SendChatReq.newBuilder();
		b.setChannelID(this.channelID);
		b.setContent(this.content);
		b.setChatType(this.chatType);
		if (toUser != null) {
			ChatMessageContent.ChatUser.Builder c = ChatMessageContent.ChatUser.newBuilder();
			c.setUserid(toUser.getUserId());
			c.setUsername(toUser.getUsername());
			c.setSex(toUser.isSex());
			b.setToUser(c.build());
		}
		return b.build().toByteArray();
	}

	@Override
	public void decode(byte[] content) throws Exception {
		ChatMessageContent.SendChatReq req = ChatMessageContent.SendChatReq.parseFrom(content);
		this.channelID = req.getChannelID();
		this.content = req.getContent();
		this.chatType = req.getChatType();
		if (req.getToUser() != null) {
			toUser = new ChatUser();
			toUser.setUserId(req.getToUser().getUserid());
			toUser.setUsername(req.getToUser().getUsername());
			toUser.setSex(req.getToUser().getSex());
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
