package com.ttt.liveroom.bean.websocket;

import java.util.List;

public class AudioVideoCallListRes extends WsResponse {
    /**
     * data : {"userId":"123","roomId":"123","userList":[{"userId":"123","nickName":"123","avatar":"123","roomId":"123","type":"1","lmType":"video"}]}
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
         * userId : 123
         * roomId : 123
         * userList : [{"userId":"123","nickName":"123","avatar":"123","roomId":"123","type":"1","lmType":"video"}]
         */

        private String userId;
        private String roomId;
        private List<LmDataBean> userList;

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getRoomId() {
            return roomId;
        }

        public void setRoomId(String roomId) {
            this.roomId = roomId;
        }

        public List<LmDataBean> getUserList() {
            return userList;
        }

        public void setUserList(List<LmDataBean> userList) {
            this.userList = userList;
        }
    }
}
