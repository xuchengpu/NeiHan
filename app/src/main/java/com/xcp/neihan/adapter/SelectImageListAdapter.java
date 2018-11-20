package com.xcp.neihan.adapter;

import android.content.Context;
import android.text.TextUtils;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.xcp.baselibrary.recycleview.BaseAdapter;
import com.xcp.neihan.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 许成谱 on 2018/11/20 14:57.
 * qq:1550540124
 * 热爱生活每一天！
 */
public class SelectImageListAdapter extends BaseAdapter<String> {
    private  int mMaxCount;
    private  ArrayList<String> mResultList;

    public SelectImageListAdapter(Context context, List<String> datas, int layoutId, ArrayList<String> mResultList, int mMaxCount) {
        super(context, datas, layoutId);
        this.mResultList=mResultList;
        this.mMaxCount=mMaxCount;
    }

    @Override
    public void convert(ViewHolder holder, List<String> datas, int position) {
        final String data = datas.get(position);
        if(TextUtils.isEmpty(data)) {
            //显示拍照
            holder.getView(R.id.camera_ll).setVisibility(View.VISIBLE);
            holder.getView(R.id.media_selected_indicator).setVisibility(View.GONE);
            holder.getView(R.id.image).setVisibility(View.GONE);
            //设置点击事件去拍照

        }else{
            holder.getView(R.id.camera_ll).setVisibility(View.GONE);
            holder.getView(R.id.media_selected_indicator).setVisibility(View.VISIBLE);
            holder.getView(R.id.image).setVisibility(View.VISIBLE);
            Glide.with(mContext).load(data).into((ImageView) holder.getView(R.id.image));
            //设置选中状态
            if(mResultList.contains(data)) {
                holder.getView(R.id.media_selected_indicator).setSelected(true);
            }else{
                holder.getView(R.id.media_selected_indicator).setSelected(false);
            }
            //设置点击事件 选中的就设为非选中，非选中的就设为选中
            holder.getView(R.id.image).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mResultList.contains(data)) {
                        mResultList.remove(data);
                    }else {
                        if(mResultList.size()>=mMaxCount) {
                            Toast.makeText(mContext, "最多只能选"+mMaxCount+"张图片", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        mResultList.add(data);
                    }
                    notifyDataSetChanged();

                    //接口回调通知数字改变
                    if(listener!=null) {
                        listener.select();
                    }

                }
            });


        }

    }
    public interface ImageSelectListener{
        void  select();
    }
    private ImageSelectListener listener;
    public void setOnImageSelectListener(ImageSelectListener listener){
        this.listener=listener;
    }
}
