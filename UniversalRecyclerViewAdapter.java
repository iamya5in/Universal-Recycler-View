package com.yasin.hosain.reusablefullscreendialog.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by YASIN on 13,July,2019
 * Email: yasinenubd5@gmail.com
 */
public class UniversalRecyclerViewAdapter extends RecyclerView.Adapter<UniversalRecyclerViewAdapter.UniversalViewHolder> {

    private Context context;
    private ArrayList arrayList;
    private int layoutResourceId;
    private OnRecyclerViewItemSingleViewSet recyclerViewItemSet;
    private OnRecyclerViewItemDualViewSet onRecyclerViewItemDualViewSet;
    private boolean isItemViewDual=false;

    public UniversalRecyclerViewAdapter(Context context, ArrayList arrayList, int layoutResourceId, OnRecyclerViewItemSingleViewSet recyclerViewItemSet) {
        this.context=context;
        this.arrayList=arrayList;
        this.layoutResourceId=layoutResourceId;
        this.recyclerViewItemSet=recyclerViewItemSet;
    }

    public UniversalRecyclerViewAdapter(Context context, ArrayList arrayList, OnRecyclerViewItemDualViewSet onRecyclerViewItemDualViewSet) {
        this.context = context;
        this.arrayList = arrayList;
        this.onRecyclerViewItemDualViewSet = onRecyclerViewItemDualViewSet;
        this.isItemViewDual=true;
    }

    @NonNull
    @Override
    public UniversalViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        if (isItemViewDual){
            return onRecyclerViewItemDualViewSet.onCreateView(viewGroup,i);
        }else {
            return new UniversalViewHolder(LayoutInflater.from(context).inflate(layoutResourceId,viewGroup,false));
        }
    }

    @Override
    public void onBindViewHolder(@NonNull UniversalViewHolder universalViewHolder, int i) {
        if (isItemViewDual){
            onRecyclerViewItemDualViewSet.onItemViewSet(universalViewHolder,arrayList.get(i),i);
        }else {
            recyclerViewItemSet.onItemViewSet(universalViewHolder,arrayList.get(i),i);
        }
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }

    public class UniversalViewHolder extends RecyclerView.ViewHolder {
        public UniversalViewHolder(@NonNull View itemView) {
            super(itemView);
        }
    }

    @Override
    public int getItemViewType(int position) {
        if (isItemViewDual){
            return onRecyclerViewItemDualViewSet.setViewType(position);
        }else {
            return super.getItemViewType(position);
        }
    }

    public interface OnRecyclerViewItemSingleViewSet {
        void onItemViewSet(UniversalViewHolder universalViewHolder,Object itemData, int position);
    }
    public interface OnRecyclerViewItemDualViewSet {
        int setViewType(int position);
        UniversalViewHolder onCreateView(ViewGroup viewGroup,int viewType);
        void onItemViewSet(UniversalViewHolder universalViewHolder,Object itemData, int position);
    }
}
