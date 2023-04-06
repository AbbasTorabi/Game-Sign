package com.abbas.gamesign.ui.element;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

public class PageableRecyclerView extends RecyclerView {

    private int maxListSize;
    private OnScrollChanged onScrollChanged;
    public String recyclerTag;

    public PageableRecyclerView(@NonNull Context context) {
        super(context);
    }

    public PageableRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public PageableRecyclerView(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public void onScrolled(int dx, int dy) {
        super.onScrolled(dx, dy);

        if (dy > 0) {

            int visibleItemCount = getLayoutManager().getChildCount();
            int totalItemCount = getLayoutManager().getItemCount();
            int pastVisibleItems = ((LinearLayoutManager) getLayoutManager()).findFirstVisibleItemPosition();

            if (getTag().toString().equals("ready")) {
                int cellNumberToStartLoadingBeforeLastCell = 5;
                if ((visibleItemCount + pastVisibleItems) >= totalItemCount - cellNumberToStartLoadingBeforeLastCell && (maxListSize == 0 || maxListSize > totalItemCount)) {
                    setTag("loading");
                    onScrollChanged.onLoadMore(recyclerTag);
                }
            }
        }
    }

    public void setMaxListSize(int maxSize) {
        this.maxListSize = maxSize;
    }

    public void setOnScrollChanged(OnScrollChanged onScrollChanged) {
        this.onScrollChanged = onScrollChanged;
    }

    public interface OnScrollChanged {
        void onLoadMore(String tag);
    }


}
