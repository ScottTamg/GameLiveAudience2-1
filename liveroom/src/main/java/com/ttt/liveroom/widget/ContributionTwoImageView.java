package com.ttt.liveroom.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ttt.liveroom.R;
import com.ttt.liveroom.net.NetManager;

/**
 * Created by mrliu on 2018/5/30.
 * 此类用于:排行榜银榜榜单图像
 */

public class ContributionTwoImageView extends RelativeLayout {
    private SimpleDraweeView mViewAvatar;
    private TextView mViewRank;

    public ContributionTwoImageView(Context context) {
        this(context, null);
    }

    public ContributionTwoImageView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ContributionTwoImageView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = View.inflate(context, R.layout.view_contribution_silver, this);
        mViewAvatar = view.findViewById(R.id.solver_sdv_avatar);
        mViewRank = view.findViewById(R.id.solver_tv_rank);
    }

    public void setText(String text) {
        mViewRank.setText(text);
    }

    public void setImageDrawable(String url) {
        mViewAvatar.setImageURI(NetManager.wrapPathToUri(url));
    }
}
