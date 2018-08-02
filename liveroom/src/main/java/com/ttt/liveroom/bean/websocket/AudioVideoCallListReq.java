package com.ttt.liveroom.bean.websocket;

/**
 * @author txw
 * @date 2018-07-13
 */
public class AudioVideoCallListReq extends WsRequest {

    /**
     * data : {"roomId":"123","userId":"123"}
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
         */

        private String roomId;
        private String userId;

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

        @Override
        public String toString() {
            return "DataBean{" +
                    "roomId='" + roomId + '\'' +
                    ", userId='" + userId + '\'' +
                    '}';
        }
    }
}
