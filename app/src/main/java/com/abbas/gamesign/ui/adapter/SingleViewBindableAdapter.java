package com.abbas.gamesign.ui.adapter;

import androidx.lifecycle.ViewModel;

import java.util.List;

public class SingleViewBindableAdapter extends BindableBaseAdapter {

    private List<? extends ViewModel> viewModels;
    private int layoutId;

    public SingleViewBindableAdapter(List<? extends ViewModel> viewModels, int layoutId) {
        this.viewModels = viewModels;
        this.layoutId = layoutId;
    }

    @Override
    List<? extends ViewModel> getViewModelList() {
        return viewModels;
    }

    @Override
    int getLayoutId() {
        return layoutId;
    }

}
