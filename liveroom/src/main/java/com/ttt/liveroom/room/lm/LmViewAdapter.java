package com.ttt.liveroom.room.lm;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.jakewharton.rxbinding.view.RxView;
import com.ttt.liveroom.R;
import com.ttt.liveroom.bean.websocket.LmDataBean;
import com.ttt.liveroom.net.Constants;
import com.ttt.liveroom.util.GlideCircleTransform;

import java.util.List;
import java.util.concurrent.TimeUnit;

import rx.functions.Action1;
import rx.functions.Func1;

/**
 * @author txw
 * @date 2018/7/12
 * 语音连麦用户界面
 */
public class LmViewAdapter extends RecyclerView.Adapter<LmViewAdapter.LmViewHolder> {

    private Context mContext;
    private List<LmDataBean> mList;
    private LmViewItemListener mListener;

    public LmViewAdapter(Context context, List<LmDataBean> list, LmViewItemListener listener) {
        this.mContext = context;
        this.mList = list;
        this.mListener = listener;
    }

    public void setList(List<LmDataBean> list) {
        mList = list;
        notifyDataSetChanged();
    }

    public void addData(LmDataBean dataBean) {
        mList.add(dataBean);
        notifyDataSetChanged();
    }

    public void removeData(LmDataBean dataBean) {
        mList.remove(dataBean);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public LmViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.layout_lm_view, parent, false);
        return new LmViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LmViewHolder holder, final int position) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(holder.ivAvatarBg, "alpha", 0f, 1f);
        if (position >= mList.size()) {
            holder.ivAvatarBg.setVisibility(View.INVISIBLE);
            holder.ivMute.setVisibility(View.GONE);
            animator.cancel();
            holder.ivAvatar.setImageResource(R.drawable.ic_default_head);
            holder.tvNickname.setText(R.string.audio_lm_empty_name);
        } else {
            Glide.with(mContext)
                    .load(mList.get(position).getAvatar())
                    .placeholder(R.drawable.ic_default_head)
                    .transform(new GlideCircleTransform(mContext))
                    .error(R.drawable.ic_default_head)
                    .into(holder.ivAvatar);
            holder.tvNickname.setText(mList.get(position).getNickName());
            if (LmDataBean.MUTE_CLOSE.equals(mList.get(position).getMute())) {
                holder.ivAvatarBg.setVisibility(View.VISIBLE);
                holder.ivMute.setVisibility(View.GONE);
                animator.setRepeatCount(ValueAnimator.INFINITE);
                animator.setRepeatMode(ValueAnimator.RESTART);
                animator.setDuration(5000).start();
            } else {
                holder.ivAvatarBg.setVisibility(View.INVISIBLE);
                holder.ivMute.setVisibility(View.VISIBLE);
                animator.cancel();
            }
        }
        RxView.clicks(holder.itemView)
                .throttleFirst(Constants.VIEW_THROTTLE_TIME, TimeUnit.SECONDS)
                .filter(new Func1<Void, Boolean>() {
                    @Override
                    public Boolean call(Void aVoid) {
                        return position < mList.size();
                    }
                })
                .subscribe(new Action1<Void>() {
                    @Override
                    public void call(Void aVoid) {
                        mListener.onItemClick(mList.get(position));
                    }
                });
    }

    @Override
    public int getItemCount() {
        return 12;
    }

    class LmViewHolder extends RecyclerView.ViewHolder {
        ImageView ivAvatarBg;
        ImageView ivAvatar;
        ImageView ivMute;
        TextView tvNickname;
        FrameLayout mFlLayout;
        View itemView;

        public LmViewHolder(View itemView) {
            super(itemView);
            ivAvatarBg = itemView.findViewById(R.id.iv_avatar_bg);
            ivAvatar = itemView.findViewById(R.id.iv_avatar);
            ivMute = itemView.findViewById(R.id.iv_mute);
            tvNickname = itemView.findViewById(R.id.tv_nickname);
            mFlLayout = itemView.findViewById(R.id.fl_layout);
            this.itemView = itemView;
        }
    }

    public interface LmViewItemListener {
        void onItemClick(LmDataBean bean);
    }
}