package lolclient.message;

import java.util.ArrayList;
import java.util.List;

import server.util.protobuf.ChatMessageContent;

import com.lzx.framework.socket.AbstractMessage;

public class EnterChatChannelRes extends AbstractMessage {

	private int channelId;
	private int status;
	private List<ChatUser> onlineUserList;

	public EnterChatChannelRes() {
		this.setCmd(1721);
	}

	@Override
	public byte[] encode() {
		ChatMessageContent.EnterChatChannelRes.Builder builder = ChatMessageContent.EnterChatChannelRes.newBuilder();
		builder.setChannelID(channelId);
		builder.setStatus(status);
		if (onlineUserList != null && onlineUserList.size() > 0) {
			for (ChatUser u : onlineUserList) {
				ChatMessageContent.ChatUser.Builder cBuilder=ChatMessageContent.ChatUser.newBuilder();
				cBuilder.setUserid(u.getUserId());
				cBuilder.setSex(u.isSex());
				cBuilder.setUsername(u.getUsername());
				builder.addOnlineUserList(cBuilder.build());
			}
		}

		return builder.build().toByteArray();
	}

	@Override
	public void decode(byte[] content) throws Exception {
		ChatMessageContent.EnterChatChannelRes res=ChatMessageContent.EnterChatChannelRes.parseFrom(content);
		this.channelId=res.getChannelID();
		this.status=res.getStatus();
		this.onlineUserList=new ArrayList<ChatUser>();
		for(ChatMessageContent.ChatUser u:res.getOnlineUserListList()) {
			ChatUser cu=new ChatUser();
			cu.setUserId(u.getUserid());
			cu.setUsername(u.getUsername());
			cu.setSex(u.getSex());
			this.onlineUserList.add(cu);
		}
	}

	public int getChannelId() {
		return channelId;
	}

	public void setChannelId(int channelId) {
		this.channelId = channelId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<ChatUser> getOnlineUserList() {
		return onlineUserList;
	}

	public void setOnlineUserList(List<ChatUser> onlineUserList) {
		this.onlineUserList = onlineUserList;
	}

}
