package com.ttt.liveroom.widget.giftview;

import android.content.Context;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ttt.liveroom.R;
import com.ttt.liveroom.bean.gift.Gift;
import com.ttt.liveroom.net.NetManager;
import com.ttt.liveroom.util.FrescoUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 刘景 on 2017/06/11.
 */

public class GiftGridViewAdapter extends BaseAdapter {

    private List<Gift> mGiftKeys;
    private ArrayList<Boolean> mselect;
    private Context mContext;
    private int mItemPadding = 0;

    public GiftGridViewAdapter(Context context, List<Gift> datas, int padding) {
        this.mGiftKeys = datas;
        this.mContext = context;
        this.mItemPadding = padding;
        if (datas == null) {
            this.mGiftKeys = new ArrayList<>();

        } else {
            this.mselect = new ArrayList<>();
            for (int i = 0; i < mGiftKeys.size(); i++) {
                mselect.add(false);
            }
        }
    }

    public GiftGridViewAdapter(Context context) {
        this.mGiftKeys = new ArrayList<>();
        this.mselect = new ArrayList<>();
        this.mContext = context;
    }

    public void updateAdapter(ArrayList<Gift> datas) {
        this.mGiftKeys = datas;
        this.mselect = new ArrayList<>();
        this.notifyDataSetChanged();
    }

    public void updateAdapter(int selectPosition, int unSelectPosition) {
        if (selectPosition != -1) {
            mselect.set(selectPosition, Boolean.TRUE);
        }
        if (unSelectPosition != -1) {
            mselect.set(unSelectPosition, Boolean.FALSE);
        }
        this.notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return mGiftKeys.size();
    }

    @Override
    public Object getItem(int position) {
        return mGiftKeys.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vHolder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.item_gift, null);
            vHolder = new ViewHolder(convertView);
            convertView.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) convertView.getTag();
        }
        vHolder.showData(mGiftKeys.get(position), mselect.get(position));
        return convertView;
    }

    class ViewHolder {
        private SimpleDraweeView mGiftImg;
        private ImageView mContinue;
        private TextView mMoney;
        private TextView mName;
        private int size = 0;
        private LinearLayout mLyGiftBg;

        public ViewHolder(View view) {
            mGiftImg = (SimpleDraweeView) view.findViewById(R.id.item_gift_icon);
            mMoney = (TextView) view.findViewById(R.id.item_gift_money);
            mName = (TextView) view.findViewById(R.id.item_gift_name);
            mContinue = (ImageView) view.findViewById(R.id.item_gift_continue);
            size = (int) mContext.getResources().getDimension(R.dimen.item_gift_icon_size);
            mLyGiftBg = (LinearLayout) view.findViewById(R.id.ll_gift_bg);
        }

        public void showData(Gift gift, boolean isSelect) {
            if (isSelect) {
                mLyGiftBg.setBackgroundColor(mContext.getResources().getColor(R.color.color_gift_selected));
            } else {
                mLyGiftBg.setBackgroundColor(mContext.getResources().getColor(R.color.transparent));
            }
            mName.setText(gift.getName());
            mMoney.setText(gift.getPrice() + mContext.getResources().getString(R.string.userinfo_dialog_poll));
            if (!TextUtils.isEmpty(gift.getImgSrc())) {
                FrescoUtil.frescoResize(NetManager.wrapPathToUri(gift.getImgSrc()), size,
                        size, mGiftImg);
            }

        }
    }
}
