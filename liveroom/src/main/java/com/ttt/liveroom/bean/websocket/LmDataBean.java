package com.ttt.liveroom.bean.websocket;

/**
 * @author txw
 * @date 2018-07-14
 */
public class LmDataBean {

    //连麦申请
    public static final String TYPE_CALL = "1";
    //连麦同意
    public static final String TYPE_AGREE = "2";
    //连麦拒绝
    public static final String TYPE_REFUSE = "3";
    //断开连麦
    public static final String TYPE_CANCEL = "4";
    //用户离线
    public static final String TYPE_EXIT = "6";

    public static final String LM_TYPE_AUDIO = "audio";
    public static final String LM_TYPE_VIDEO = "video";

    public static final String MUTE_OPEN = "open";
    public static final String MUTE_CLOSE = "close";

    private String roomId;
    private String adminUserId;
    private String userId;
    private String type;
    private String lmType;
    private String nickName;
    private String avatar;
    private String mute;
    private String fd;

    public String getRoomId() {
        return roomId;
    }

    public void setRoomId(String roomId) {
        this.roomId = roomId;
    }

    public String getAdminUserId() {
        return adminUserId;
    }

    public void setAdminUserId(String adminUserId) {
        this.adminUserId = adminUserId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getLmType() {
        return lmType;
    }

    public void setLmType(String lmType) {
        this.lmType = lmType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getMute() {
        return mute;
    }

    public void setMute(String mute) {
        this.mute = mute;
    }

    public String getFd() {
        return fd;
    }

    public void setFd(String fd) {
        this.fd = fd;
    }

    @Override
    public String toString() {
        return "LmDataBean{" +
                "roomId='" + roomId + '\'' +
                ", adminUserId='" + adminUserId + '\'' +
                ", userId='" + userId + '\'' +
                ", type='" + type + '\'' +
                ", lmType='" + lmType + '\'' +
                ", nickName='" + nickName + '\'' +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
