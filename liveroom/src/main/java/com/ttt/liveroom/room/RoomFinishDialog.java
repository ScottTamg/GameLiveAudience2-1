package com.ttt.liveroom.room;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import com.ttt.liveroom.R;
import com.ttt.liveroom.base.DataManager;
import com.ttt.liveroom.bean.LoginInfo;

import rx.Subscriber;

/**
 * @author liujing
 * Created by 刘景 on 2017/06/11.
 */
public class RoomFinishDialog extends Dialog {

    private final RoomActivity activity;
    private final String mRoomId;
    private TextView mSeePeople, mCoin, mLongTime;
    private TextView mBack;
    private LoginInfo loginInfo;
    private FinishDialogListener mListener;
    private int mCount;
    private String mIncome;
    private String mDuration;
    private Subscriber subscription;

    public RoomFinishDialog(RoomActivity context, String roomId, int count, String income, String duration) {
        super(context, R.style.DialogStyle);
        this.activity = context;
        this.mRoomId = roomId;
        this.mCount = count;
        this.mIncome = income;
        this.mDuration = duration;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.dialog_room_publish_finish);
        loginInfo = DataManager.getInstance().getLoginInfo();
        findView();
        initView();
    }

    private void findView() {
        mSeePeople = (TextView) findViewById(R.id.users_num);
        mCoin = (TextView) findViewById(R.id.my_gain);
        mBack = (TextView) findViewById(R.id.finish);
        mLongTime = (TextView) findViewById(R.id.time);
    }

    private void initView() {
        mSeePeople.setText(String.valueOf(mCount));
        mCoin.setText(TextUtils.isEmpty(mIncome) ? "0" : mIncome);
        mLongTime.setText(TextUtils.isEmpty(mDuration) ? "0" : mDuration);
        setCancelable(false);
        setOnDismissListener(new OnDismissListener() {
            @Override
            public void onDismiss(DialogInterface dialog) {
                if (subscription != null) {
                    subscription.unsubscribe();
                }
            }
        });

        mBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
                mListener.onFinish();
            }
        });
    }

    public void setListener(FinishDialogListener listener) {
        mListener = listener;
    }

    public interface FinishDialogListener {
        void onFinish();
    }

    @Override
    public void onBackPressed() {
        if (mListener == null) {
            super.onBackPressed();
        } else {
            mListener.onFinish();
        }
    }

}
