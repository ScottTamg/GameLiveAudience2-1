package com.ttt.liveroom.widget;

import android.content.Context;
import android.net.Uri;
import android.text.TextUtils;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.ttt.liveroom.R;
import com.ttt.liveroom.bean.UserInfo;
import com.ttt.liveroom.util.UiUtils;

public class UserInfoPopup extends PopupWindow implements View.OnTouchListener,
        View.OnClickListener, View.OnKeyListener {
    private static final String TAG = "UserInfoPopup";

    private UserInfo mUserInfo;


    private Context mContext;
    private View mRootView;
    /**
     * 取消
     */
    private TextView btn_cancel;
    /**
     * 名字
     */
    private TextView info_name;
    /**
     * 签名
     */
    private TextView info_signature;
    /**
     * 爱好
     */
    private TextView info_hobby;
    /**
     * 特长
     */
    private TextView info_speciality;
    /**
     * 身高
     */
    private TextView info_stature;
    /**
     * 头像
     */
    private SimpleDraweeView info_photo;

    public void setUserInfo(UserInfo userInfo) {
        this.mUserInfo = userInfo;
        initData();
    }

    public UserInfo getUserInfo() {
        return mUserInfo;
    }

    public UserInfoPopup(Context context, UserInfo userInfo) {
        this.mContext = context;
        this.mUserInfo = userInfo;
        initViews();
    }

    private void initViews() {
        LayoutInflater inflater = LayoutInflater.from(mContext);
        mRootView = inflater.inflate(R.layout.dialog_userinfo, null);
        this.setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        this.setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        this.setFocusable(true);
//        ColorDrawable dw = new ColorDrawable(0x00000000);
//        this.setBackgroundDrawable(dw);
        //设置动画效果
        this.setAnimationStyle(R.style.MyPopupWindow_anim_style);
        setContentView(mRootView);
        //获取相应的控件
        btn_cancel = mRootView.findViewById(R.id.dialog_user_info_cancel);
        info_name = mRootView.findViewById(R.id.dialog_user_info_name);
        info_signature = mRootView.findViewById(R.id.dialog_user_info_signature);
        info_hobby = mRootView.findViewById(R.id.dialog_user_info_hobbydetai);
        info_speciality = mRootView.findViewById(R.id.dialog_user_info_specialitydetail);
        info_stature = mRootView.findViewById(R.id.dialog_user_info_staturedetail);
        info_photo = mRootView.findViewById(R.id.dialog_user_info_photo);
        btn_cancel.setOnClickListener(this);
        initData();
    }

    private void initData() {
        //设置头像
        info_photo.setImageURI(Uri.parse(mUserInfo.getAvatar()));
        //设置爱好
        info_hobby.setText(mUserInfo.getHobby());
        //设置特长
        info_speciality.setText(mUserInfo.getSpeciality());
        //设置身高
        info_stature.setText(mUserInfo.getHeight());
        //设置签名.
        if (!TextUtils.isEmpty((mUserInfo.getDescription().trim()))){
            info_signature.setText(mUserInfo.getDescription());
        }

        //名称
        info_name.setText(mUserInfo.getUserName());
    }


    @Override
    public void onClick(View v) {
        int i = v.getId();
        if (i == R.id.dialog_user_info_cancel) {
            dismiss();
        }
        //else if (i == R.id.dialog_user_info_star) {
//            mListener.onStar(mUserInfo, mUserInfo.getIsAttention() == 1);
//        } else if (i == R.id.dialog_user_info_prv_chat) {
//            mListener.onPrvChat(mUserInfo);
//            dismiss();
//        } else if (i == R.id.dialog_user_info_photo) {
//            mListener.onUserPhoto(mUserInfo);
//        } else {
//
//        }
    }

    //点击外部popup消失
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        int height = mRootView.findViewById(R.id.rl_user_info).getTop();
        int y = (int) event.getY();
        if (event.getAction() == MotionEvent.ACTION_UP) {
            if (y < height) {
                dismiss();
            }
        }
        return true;
    }

    //点back键消失
    @Override
    public boolean onKey(View v, int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && this.isShowing()) {
            this.dismiss();
            return true;
        }
        return false;
    }
}
