package com.fei.wrapperrecyclerview;

import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName: WrapperRecyclerAdapter
 * @Description: 装饰模式
 * @Author: Fei
 * @CreateDate: 2021-02-17 15:48
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-02-17 15:48
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WrapperRecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final RecyclerView.Adapter mAdapter;
    ArrayList<View> mHeaderViews;
    ArrayList<View> mFooterViews;


    public WrapperRecyclerAdapter(RecyclerView.Adapter mAdapter) {
        this.mAdapter = mAdapter;
        mHeaderViews = new ArrayList<>();
        mFooterViews = new ArrayList<>();
    }

    public void addHeaderView(View view) {
        if (!mHeaderViews.contains(view)) {
            mHeaderViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void addFooterView(View view) {
        if (!mFooterViews.contains(view)) {
            mFooterViews.add(view);
            notifyDataSetChanged();
        }
    }

    public void removeHeaderView(View view) {
        if (mHeaderViews.contains(view)) {
            mHeaderViews.remove(view);
            notifyDataSetChanged();
        }
    }

    public void removeFooterView(View view) {
        if (mFooterViews.contains(view)) {
            mFooterViews.remove(view);
            notifyDataSetChanged();
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int position) {

        // Header (negative positions will throw an IndexOutOfBoundsException)
        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return createHeadFootViewHolder(mHeaderViews.get(position));
        }

        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mAdapter.onCreateViewHolder(parent, mAdapter.getItemViewType(adapterCount));
            }
        }

        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
        return createHeadFootViewHolder(mFooterViews.get(adjPosition - adapterCount));

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //头部，底部不用处理

        int numHeaders = getHeadersCount();
        if (position < numHeaders) {
            return;
        }

        // Adapter
        final int adjPosition = position - numHeaders;
        int adapterCount = 0;
        if (mAdapter != null) {
            adapterCount = mAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                mAdapter.onBindViewHolder(holder, adjPosition);
                return;
            }
        }

        // Footer (off-limits positions will throw an IndexOutOfBoundsException)
        return;
    }

    private RecyclerView.ViewHolder createHeadFootViewHolder(View view) {
        return new HeadFootViewHolder(view);
    }

    private class HeadFootViewHolder extends RecyclerView.ViewHolder {
        public HeadFootViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }


    @Override
    public int getItemViewType(int position) {
        return position;
    }

    @Override
    public int getItemCount() {
        if (mAdapter != null) {
            return getFootersCount() + getHeadersCount() + mAdapter.getItemCount();
        } else {
            return getFootersCount() + getHeadersCount();
        }
    }

    public int getHeadersCount() {
        return mHeaderViews.size();
    }

    public int getFootersCount() {
        return mFooterViews.size();
    }

    public void registerAdapterDataObserver(RecyclerView.AdapterDataObserver observer) {
        if (mAdapter != null) {
            mAdapter.registerAdapterDataObserver(observer);
        }
    }

    public void unregisterDataSetObserver(RecyclerView.AdapterDataObserver observer) {
        if (mAdapter != null) {
            mAdapter.unregisterAdapterDataObserver(observer);
        }
    }
}
