package com.ttt.liveroom.room.contribution;

import android.support.annotation.NonNull;

import com.ttt.liveroom.base.BaseObserver;
import com.ttt.liveroom.base.BasePresenter;
import com.ttt.liveroom.bean.BaseResponse;
import com.ttt.liveroom.bean.room.ContributionBean;
import com.ttt.liveroom.net.NetManager;

import java.util.ArrayList;

import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ContributionPresenter extends BasePresenter<ContributionInterface> {

    protected ContributionPresenter(ContributionInterface uiInterface) {
        super(uiInterface);
    }

    /**
     * 贡献榜
     *
     * @param page   页数
     * @param type   类型
     * @param size   条数
     * @param userId 主播ID
     */
    public void getContribution(int page, int type, String size, @NonNull String userId) {
        Subscription subscription = NetManager.getInstance().create(ContributionApi.class)
                .getContribution(String.valueOf(page), String.valueOf(type), size, userId)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<BaseResponse<ContributionBean>>(getUiInterface()) {
                    @Override
                    public void onSuccess(BaseResponse<ContributionBean> response) {
                        if (response.getData() != null && !response.getData().getList().isEmpty()) {
                            getUiInterface().showData(response.getData().getList());
                        } else {
                            getUiInterface().showData(new ArrayList<ContributionBean.ListBean>());
                        }
                    }

                    @Override
                    protected void onDataFailure(BaseResponse<ContributionBean> response) {
                        // empty 处理
                        getUiInterface().onError("数据空");
                    }

                    @Override
                    public void onError(Throwable e) {
                        getUiInterface().onError("请求报错");
                    }
                });
        addSubscription(subscription);
    }
}
