package com.ttt.liveroom.room.contribution;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.github.magiepooh.recycleritemdecoration.ItemDecorations;
import com.ttt.liveroom.R;
import com.ttt.liveroom.base.BaseFragment;
import com.ttt.liveroom.bean.room.ContributionBean;
import com.ttt.liveroom.net.NetManager;
import com.ttt.liveroom.widget.ContributionImageView;
import com.ttt.liveroom.widget.ContributionThreeImageView;
import com.ttt.liveroom.widget.ContributionTwoImageView;

import java.util.List;

import in.srain.cube.views.ptr.PtrClassicFrameLayout;
import in.srain.cube.views.ptr.PtrDefaultHandler2;
import in.srain.cube.views.ptr.PtrFrameLayout;
import retrofit2.Response;
import rx.functions.Action1;

public class ContributionFragment extends BaseFragment implements ContributionInterface {
    private static final String CHANNEL_ITEM_ID = "item_id";
    private static final String CHANNEL_USER_ID = "user_id";

    private static final int CONTRIBUTION_ONE = 0;
    private static final int CONTRIBUTION_TWO = 1;
    private static final int CONTRIBUTION_THREE = 2;
    private static final int CONTRIBUTION_MORE = 3;

    RecyclerView mRvContribution;
    TextView mTvEmpty;
    PtrClassicFrameLayout mPtrContribution;

    private int mItemId = -1;
    private int mPage = 1;
    private String mSize = "10";
    private String mUserId;
    private ContributionPresenter mPresenter;
    private List<ContributionBean.ListBean> mListBeans;
    private MyAdapter mMyAdapter;

    public static ContributionFragment newInstance(int itemId, String userId) {
        Bundle bundle = new Bundle();
        bundle.putInt(CHANNEL_ITEM_ID, itemId);
        bundle.putString(CHANNEL_USER_ID, userId);
        ContributionFragment fragment = new ContributionFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_contribution;
    }

    @Override
    protected void initViews(View view) {
        mRvContribution = (RecyclerView) view.findViewById(R.id.rv_contribution);
        mTvEmpty = (TextView) view.findViewById(R.id.tv_empty);
        mPtrContribution = (PtrClassicFrameLayout) view.findViewById(R.id.ptr_contribution);
        subscribeClick(mTvEmpty, new Action1<Void>() {
            @Override
            public void call(Void aVoid) {
                mPage = 1;
                if (mListBeans != null) {
                    mListBeans.clear();
                }
                mPresenter.getContribution(mPage, mItemId, mSize, mUserId);
            }
        });

        Bundle arguments = getArguments();
        mItemId = arguments.getInt(CHANNEL_ITEM_ID);
        mUserId = arguments.getString(CHANNEL_USER_ID);
        mRvContribution.setLayoutManager(new LinearLayoutManager(getContext()));
        mRvContribution.addItemDecoration(ItemDecorations.vertical(mContext)
                .type(0, R.drawable.divider_decoration_transparent_h1)
                .type(1, R.drawable.divider_decoration_transparent_h1)
                .type(2, R.drawable.divider_decoration_transparent_h1)
                .type(3, R.drawable.divider_decoration_transparent_h1)
                .create() );
        mMyAdapter = new MyAdapter();
        mRvContribution.setAdapter(mMyAdapter);
        mPtrContribution.disableWhenHorizontalMove(true);
        mPtrContribution.setPtrHandler(new PtrDefaultHandler2() {
            @Override
            public void onLoadMoreBegin(PtrFrameLayout frame) {
                mPresenter.getContribution(mPage, mItemId, mSize, mUserId);
            }

            @Override
            public void onRefreshBegin(PtrFrameLayout frame) {
                mPage = 1;
                if (mListBeans != null) {
                    mListBeans.clear();
                }
                mPresenter.getContribution(mPage, mItemId, mSize, mUserId);
            }
        });
        mPresenter = new ContributionPresenter(this);
        mPresenter.getContribution(mPage, mItemId, mSize, mUserId);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void showData(List<ContributionBean.ListBean> listBeans) {
        if (!listBeans.isEmpty()) {
            mTvEmpty.setVisibility(View.GONE);
            mRvContribution.setVisibility(View.VISIBLE);
            mPage++;
            if (mListBeans == null || mListBeans.size() == 0) {
                mListBeans = listBeans;
            } else {
                mListBeans.addAll(listBeans);
            }
            mMyAdapter.notifyDataSetChanged();
        } else {
            if (mPage == 1) {
                mTvEmpty.setVisibility(View.VISIBLE);
                mRvContribution.setVisibility(View.GONE);
            } else {
                mTvEmpty.setVisibility(View.GONE);
                mRvContribution.setVisibility(View.VISIBLE);
            }
        }

        mPtrContribution.refreshComplete();
    }

    @Override
    public void onError(String error) {
        if (mPage == 1) {
            mTvEmpty.setVisibility(View.VISIBLE);
            mRvContribution.setVisibility(View.GONE);
        } else {
            mTvEmpty.setVisibility(View.GONE);
            mRvContribution.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showLoadingComplete() {
        mPtrContribution.refreshComplete();
    }

    @Override
    public void onSucceed(int what, Response<Bitmap> response) {

    }

    private void gotoOtherUserActivity(String userId) {
        Intent intent = new Intent();
        intent.setAction("com.ttt.OtherUserActivity");
        intent.putExtra("id", userId);
        startActivity(intent);
    }

    private class MyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            View view;
            if (CONTRIBUTION_ONE == viewType) {
                view = inflater.inflate(R.layout.layout_contribution_one, parent, false);
                return new OneHolder(view);
            } else if (CONTRIBUTION_TWO == viewType) {
                view = inflater.inflate(R.layout.layout_contribution_two, parent, false);
                return new TwoHolder(view);
            } else if (CONTRIBUTION_THREE == viewType) {
                view = inflater.inflate(R.layout.layout_contribution_three, parent, false);
                return new ThreeHolder(view);
            } else {
                view = inflater.inflate(R.layout.item_contribution_for_more, parent, false);
                return new MoreHolder(view);
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (0 == position) {
                return CONTRIBUTION_ONE;
            } else if (1 == position) {
                return CONTRIBUTION_TWO;
            } else if (2 == position) {
                return CONTRIBUTION_THREE;
            } else {
                return CONTRIBUTION_MORE;
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
            if (holder instanceof OneHolder) {
                OneHolder oneHolder = (OneHolder) holder;
                oneHolder.mAvatar.setText(getString(R.string.default_text, "NO.1"));
                oneHolder.mAvatar.setImageDrawable(mListBeans.get(0).getAvatar());
                oneHolder.mNoOneTvName.setText(getString(R.string.default_text, mListBeans.get(0).getNickName()));
                oneHolder.mNoOneNum.setText(getString(R.string.default_text, mListBeans.get(0).getTotalPrice()));
                subscribeClick(oneHolder.mAvatar,
                        new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                gotoOtherUserActivity(mListBeans.get(0).getUserId());
                            }
                        });

            } else if (holder instanceof TwoHolder) {
                TwoHolder twoHolder = (TwoHolder) holder;
                twoHolder.mNoTwoIv.setText(getString(R.string.default_text, "NO.2"));
                twoHolder.mNoTwoIv.setImageDrawable(mListBeans.get(1).getAvatar());
                twoHolder.mNoTwoTvName.setText(mListBeans.get(1).getNickName());
                twoHolder.mNoTwoNum.setText(mListBeans.get(1).getTotalPrice());
                subscribeClick(twoHolder.mNoTwoIv,
                        new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                gotoOtherUserActivity(mListBeans.get(1).getUserId());
                            }
                        });
            } else if (holder instanceof ThreeHolder) {
                ThreeHolder threeHolder = (ThreeHolder) holder;
                threeHolder.mNoThreeIv.setText(getString(R.string.default_text, "NO.3"));
                threeHolder.mNoThreeIv.setImageDrawable(mListBeans.get(2).getAvatar());
                threeHolder.mNoTwoTvName.setText(mListBeans.get(2).getNickName());
                threeHolder.mNoTwoNum.setText(mListBeans.get(2).getTotalPrice());
                subscribeClick(threeHolder.mNoThreeIv, new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                gotoOtherUserActivity(mListBeans.get(2).getUserId());
                            }
                        });
            } else {
                MoreHolder moreHolder = (MoreHolder) holder;
                moreHolder.mRank.setText(getString(R.string.default_text, "NO." + (position + 1)));
                moreHolder.mSdvAvatar.setImageURI(NetManager.wrapPathToUri(mListBeans.get(position).getAvatar()));
                moreHolder.mTvName.setText(mListBeans.get(position).getNickName());
                moreHolder.mNum.setText(mListBeans.get(position).getTotalPrice());
                subscribeClick(moreHolder.mSdvAvatar, new Action1<Void>() {
                            @Override
                            public void call(Void aVoid) {
                                gotoOtherUserActivity(mListBeans.get(position).getUserId());
                            }
                        });
            }
        }

        @Override
        public int getItemCount() {
            return mListBeans == null ? 0 : mListBeans.size();
        }

    }

    private class OneHolder extends RecyclerView.ViewHolder {
        ContributionImageView mAvatar;
        TextView mNoOneTvName;
        TextView mNoOneNum;

        public OneHolder(View itemView) {
            super(itemView);
            mAvatar = (ContributionImageView) itemView.findViewById(R.id.avatar);
            mNoOneTvName = (TextView) itemView.findViewById(R.id.no_one_tv_name);
            mNoOneNum = (TextView) itemView.findViewById(R.id.no_one_num);
        }
    }

    private class TwoHolder extends RecyclerView.ViewHolder {
        ImageView mNoTwo;
        ContributionTwoImageView mNoTwoIv;
        TextView mNoTwoTvName;
        TextView mNoTwoNum;

        public TwoHolder(View itemView) {
            super(itemView);
            mNoTwoIv = (ContributionTwoImageView) itemView.findViewById(R.id.no_two_iv);
            mNoTwoTvName = (TextView) itemView.findViewById(R.id.no_two_tv_name);
            mNoTwoNum = (TextView) itemView.findViewById(R.id.no_two_num);
        }
    }

    private class ThreeHolder extends RecyclerView.ViewHolder {
        ImageView mNoTwo;
        ContributionThreeImageView mNoThreeIv;
        TextView mNoTwoTvName;
        TextView mNoTwoNum;

        public ThreeHolder(View itemView) {
            super(itemView);
            mNoThreeIv = (ContributionThreeImageView) itemView.findViewById(R.id.no_three_iv);
            mNoTwoTvName = (TextView) itemView.findViewById(R.id.no_two_tv_name);
            mNoTwoNum = (TextView) itemView.findViewById(R.id.no_two_num);
        }
    }

    private class MoreHolder extends RecyclerView.ViewHolder {
        TextView mRank;
        SimpleDraweeView mSdvAvatar;
        TextView mTvName;
        TextView mNum;

        public MoreHolder(View itemView) {
            super(itemView);
            mRank = (TextView) itemView.findViewById(R.id.rank);
            mSdvAvatar = (SimpleDraweeView) itemView.findViewById(R.id.sdv_avatar);
            mTvName = (TextView) itemView.findViewById(R.id.tv_name);
            mNum = (TextView) itemView.findViewById(R.id.num);
        }
    }
}
