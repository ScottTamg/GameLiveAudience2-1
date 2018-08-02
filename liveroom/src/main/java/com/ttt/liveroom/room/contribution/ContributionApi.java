package com.ttt.liveroom.room.contribution;

import com.ttt.liveroom.bean.BaseResponse;
import com.ttt.liveroom.bean.room.ContributionBean;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

public interface ContributionApi {
    @GET("/gift/contribution")
    Observable<BaseResponse<ContributionBean>> getContribution(@Query("page") String page, @Query("type") String type,
                                                               @Query("size") String size, @Query("userId") String userId);
}
