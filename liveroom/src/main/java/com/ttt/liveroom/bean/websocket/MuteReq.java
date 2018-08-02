package com.ttt.liveroom.bean.websocket;

/**
 * @author txw
 * @date 2018-07-13
 */
public class MuteReq extends WsRequest {

    /**
     * data : {"roomId":"123","userId":"123","mute":"open","adminUserId":"123"}
     */

    private DataBean data;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * roomId : 123
         * userId : 123
         * mute : open
         * adminUserId : 123
         */

        private String roomId;
        private String userId;
        private String mute;
        private String adminUserId;

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getMute() {
            return mute;
        }

        public void setMute(String mute) {
            this.mute = mute;
        }

        public String getAdminUserId() {
            return adminUserId;
        }

        public void setAdminUserId(String adminUserId) {
            this.adminUserId = adminUserId;
        }
    }
}
