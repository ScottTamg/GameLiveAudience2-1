package com.ttt.liveroom.room.contribution;

import com.ttt.liveroom.base.BaseUiInterface;
import com.ttt.liveroom.bean.room.ContributionBean;

import java.util.List;

public interface ContributionInterface extends BaseUiInterface {
    //展示数据
    void showData(List<ContributionBean.ListBean> listBeans);

    void onError(String error);
}
