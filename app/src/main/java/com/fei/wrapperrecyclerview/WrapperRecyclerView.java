package com.fei.wrapperrecyclerview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

/**
 * @ClassName: WrapperRecyclerView
 * @Description: java类作用描述
 * @Author: Fei
 * @CreateDate: 2021-02-17 16:07
 * @UpdateUser: 更新者
 * @UpdateDate: 2021-02-17 16:07
 * @UpdateRemark: 更新说明
 * @Version: 1.0
 */
public class WrapperRecyclerView extends RecyclerView {

    private WrapperRecyclerAdapter mAdapter;

    public WrapperRecyclerView(@NonNull Context context) {
        super(context);
    }

    public WrapperRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public WrapperRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void setAdapter(@Nullable Adapter adapter) {
        mAdapter = new WrapperRecyclerAdapter(adapter);
        super.setAdapter(mAdapter);
    }

    public void addHeaderView(View view) {
        if (mAdapter != null) {
            mAdapter.addHeaderView(view);
        }
    }

    public void addFooterView(View view) {
        if (mAdapter != null) {
            mAdapter.addFooterView(view);
        }
    }

    public void removeHeaderView(View view) {
        if (mAdapter != null) {
            mAdapter.removeHeaderView(view);
        }
    }

    public void removeFooterView(View view) {
        if (mAdapter != null) {
            mAdapter.removeFooterView(view);
        }
    }
}
