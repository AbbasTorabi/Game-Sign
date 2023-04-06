package com.abbas.gamesign.ui.adapter;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.lifecycle.ViewModel;
import androidx.recyclerview.widget.RecyclerView;

import com.abbas.gamesign.BR;

import java.util.List;

public abstract class BindableBaseAdapter extends RecyclerView.Adapter<BindableBaseAdapter.BaseViewHolder> {

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(viewGroup.getContext()), getLayoutId(), viewGroup, false);
        return new BaseViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int i) {
        viewHolder.bind(i);
    }

    @Override
    public int getItemCount() {
        return getViewModelList().size();
    }

    class BaseViewHolder extends RecyclerView.ViewHolder {

        private ViewDataBinding binding;

        BaseViewHolder(ViewDataBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }

        void bind(int position) {
            Object viewModel = getViewModelList().get(position);
            binding.setVariable(BR.viewmodel, viewModel);
            binding.executePendingBindings();
        }
    }

    abstract List<? extends ViewModel> getViewModelList();

    abstract int getLayoutId();

}
