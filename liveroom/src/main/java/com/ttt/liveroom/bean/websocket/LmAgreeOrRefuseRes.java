package com.ttt.liveroom.bean.websocket;

public class LmAgreeOrRefuseRes extends WsRequest {

    private LmDataBean data;

    public LmDataBean getData() {
        return data;
    }

    public void setData(LmDataBean data) {
        this.data = data;
    }
}
