package lolclient.message;

import server.util.protobuf.ChatMessageContent;

import com.lzx.framework.socket.AbstractMessage;

public class UserOnlineStatusRes extends AbstractMessage{

    private int channelID;
	private ChatUser user;	
	private boolean online;  //true:上线; false:下线
	
	public UserOnlineStatusRes() {
		this.setCmd(1725);
	}
	
	@Override
	public byte[] encode() {
		ChatMessageContent.UserOnlineStatusRes.Builder b=ChatMessageContent.UserOnlineStatusRes.newBuilder();
		b.setChannelID(channelID);
		ChatMessageContent.ChatUser.Builder u=ChatMessageContent.ChatUser.newBuilder();
		u.setUserid(user.getUserId());
		u.setUsername(user.getUsername());
		u.setSex(user.isSex());
		b.setUser(u.build());
		b.setOnline(online);
		return b.build().toByteArray();
	}

	@Override
	public void decode(byte[] content) throws Exception {
		ChatMessageContent.UserOnlineStatusRes res=ChatMessageContent.UserOnlineStatusRes.parseFrom(content);
		this.channelID=res.getChannelID();
		this.online=res.getOnline();
		user=new ChatUser();
		user.setUserId(res.getUser().getUserid());
		user.setUsername(res.getUser().getUsername());
		user.setSex(res.getUser().getSex());
	}

	public int getChannelID() {
		return channelID;
	}

	public void setChannelID(int channelID) {
		this.channelID = channelID;
	}

	public ChatUser getUser() {
		return user;
	}

	public void setUser(ChatUser user) {
		this.user = user;
	}

	public boolean isOnline() {
		return online;
	}

	public void setOnline(boolean online) {
		this.online = online;
	}


}
