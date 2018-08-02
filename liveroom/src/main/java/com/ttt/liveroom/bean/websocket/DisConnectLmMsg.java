package com.ttt.liveroom.bean.websocket;

/**
 * Created by mrliu on 2018/2/2.
 * 此类用于:
 */

public class DisConnectLmMsg extends WsResponse {

    private DisConnectLmMsgData data;

    public DisConnectLmMsgData getData() {
        return data;
    }

    public void setData(DisConnectLmMsgData data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return "DisConnectLmMsg{" +
                "data=" + data +
                '}';
    }

    public static class DisConnectLmMsgData {
        private String userId;
        private int type;
        private String adminUserId;
        private String roomId;
        private String lmType;
        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
        public String getAdminUserId() {
            return adminUserId;
        }

        public void setAdminUserId(String adminUserId) {
            this.adminUserId = adminUserId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getLmType() {
            return lmType;
        }

        public void setLmType(String lmType) {
            this.lmType = lmType;
        }
        @Override
        public String toString() {
            return "DisConnectLmMsgData{" +
                    "userId='" + userId + '\'' +
                    ", type='" + type + '\'' +
                    '}';
        }
    }
}
