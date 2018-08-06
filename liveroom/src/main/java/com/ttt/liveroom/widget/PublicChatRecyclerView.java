package com.ttt.liveroom.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Log;

import com.ttt.liveroom.room.pubmsg.PublicChatAdapter;

/**
 * 此类用于直播聊天窗口 聊天列表
 *      处理收到新消息会滑动到底部问题
 */
public class PublicChatRecyclerView extends RecyclerView {
    private PublicChatRecyclerViewListener listener;
    private boolean isShowUnread = false;
    public PublicChatRecyclerView(Context context) {
        super(context);
        //添加滑动监听
        addOnScrollListener(on);
    }

    public PublicChatRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        addOnScrollListener(on);
    }

    public PublicChatRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        addOnScrollListener(on);
    }

    /**
     * 设置是否滑动到最后一条监听
     * @param listener
     */
    public void setPublicChatRecyclerViewListener(PublicChatRecyclerViewListener listener){
        this.listener = listener;
    }
    /**
     * 检测recyclerview是否滑动到了底部
     * @return
     */
    public boolean isVisBottom(){
        LinearLayoutManager layoutManager = (LinearLayoutManager) getLayoutManager();
        //屏幕中最后一个可见子项的position
        int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();
        //当前屏幕所看到的子项个数
        int visibleItemCount = layoutManager.getChildCount();
        //当前RecyclerView的所有子项个数
        int totalItemCount = layoutManager.getItemCount();
        //RecyclerView的滑动状态
        int state = getScrollState();
        if(visibleItemCount > 0 && lastVisibleItemPosition == totalItemCount - 1 && state == SCROLL_STATE_IDLE){
            return true;
        }else {
            return false;
        }
    }
    private int unreadCount = 0;

    public int getUnreadCount() {
        return unreadCount;
    }

    public void setUnreadCount(int unreadCount) {
        this.unreadCount = unreadCount;
    }
    public void addUnreadCount(int count){
        if (!isVisBottom()){
            unreadCount++;
        }else{
            scrollToPosition(((PublicChatAdapter)getAdapter()).getItemCount() - 1);
        }
    }
    //滑动监听
    OnScrollListener on = new OnScrollListener(){
        @Override
        public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
            super.onScrolled(recyclerView, dx, dy);
            Log.e("###","onScrolled");
            //是否可向下滑动
            if (canScrollVertically(1)){
                isShowUnread = true;
            }else{
                isShowUnread = false;
            }
            listener.lastViewIsVisible(isShowUnread);
//            boolean visBottom = isVisBottom();
//                if (visBottom)
//                    unreadCount=0;
//                if (listener!=null){
//                    listener.lastViewIsVisible(visBottom);
//                    Log.e("###","lastViewIsVisible");
//                }
        }
        @Override
        public void onScrollStateChanged(RecyclerView recyclerView, int newState) {
            super.onScrollStateChanged(recyclerView, newState);

        }

    };
    public interface PublicChatRecyclerViewListener{
        /**
         * 该方法是监听recyclerview滑动结束后，最后一条是否显示
         * @param isVisible  true为显示，false为不显示
         */
        void lastViewIsVisible(boolean isVisible);
    }
}
