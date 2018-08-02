package com.ttt.liveroom.bean.room;

import java.util.List;

public class ContributionBean {

    /**
     * totalCount : 5
     * page : 1
     * size : 10
     * pageCount : 1
     * list : [{"totalPrice":"400","userId":"300006","userIdReceive":"900022","avatar":"http://wx.qlogo.cn/mmopen/vi_32/M8SSCQKxbtGicBFxhEwrWAplA1h05vNkGYLQ1t00f0OC83JfZ06iaHia1wS3ibM2GnI8DzlC33lvgDGiclkWWmfxchw/132","nickName":"Samael","level":1,"description":""},{"totalPrice":"200","userId":"100007","userIdReceive":"900022","avatar":"http://3tlive.oss-cn-beijing.aliyuncs.com/publishlive/900019/IMG_20180516_115240.png","nickName":"","level":0,"description":""},{"totalPrice":"200","userId":"300003","userIdReceive":"900022","avatar":"http://3tdoc.oss-cn-beijing.aliyuncs.com/wechat/avatar/16.jpg","nickName":"176****1312","level":1,"description":""},{"totalPrice":"200","userId":"400001","userIdReceive":"900022","avatar":"http://3tlive.oss-cn-beijing.aliyuncs.com/publishlive/900019/IMG_20180516_115240.png","nickName":"","level":0,"description":""}]
     */

    private int totalCount;
    private int page;
    private int size;
    private int pageCount;
    private List<ListBean> list;

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getPageCount() {
        return pageCount;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "ContributionBean{" +
                "totalCount=" + totalCount +
                ", page=" + page +
                ", size=" + size +
                ", pageCount=" + pageCount +
                ", list=" + list +
                '}';
    }

    public static class ListBean {
        /**
         * totalPrice : 400
         * userId : 300006
         * userIdReceive : 900022
         * avatar : http://wx.qlogo.cn/mmopen/vi_32/M8SSCQKxbtGicBFxhEwrWAplA1h05vNkGYLQ1t00f0OC83JfZ06iaHia1wS3ibM2GnI8DzlC33lvgDGiclkWWmfxchw/132
         * nickName : Samael
         * level : 1
         * description :
         */

        private String totalPrice;
        private String userId;
        private String userIdReceive;
        private String avatar;
        private String nickName;
        private int level;
        private String description;

        public String getTotalPrice() {
            return totalPrice;
        }

        public void setTotalPrice(String totalPrice) {
            this.totalPrice = totalPrice;
        }

        public String getUserId() {
            return userId;
        }

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public String getUserIdReceive() {
            return userIdReceive;
        }

        public void setUserIdReceive(String userIdReceive) {
            this.userIdReceive = userIdReceive;
        }

        public String getAvatar() {
            return avatar;
        }

        public void setAvatar(String avatar) {
            this.avatar = avatar;
        }

        public String getNickName() {
            return nickName;
        }

        public void setNickName(String nickName) {
            this.nickName = nickName;
        }

        public int getLevel() {
            return level;
        }

        public void setLevel(int level) {
            this.level = level;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        @Override
        public String toString() {
            return "ListBean{" +
                    "totalPrice='" + totalPrice + '\'' +
                    ", userId='" + userId + '\'' +
                    ", userIdReceive='" + userIdReceive + '\'' +
                    ", avatar='" + avatar + '\'' +
                    ", nickName='" + nickName + '\'' +
                    ", level=" + level +
                    ", description='" + description + '\'' +
                    '}';
        }
    }
}
