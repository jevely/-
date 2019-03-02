package com.jeve.gestures.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.AppCompatCheckBox;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.jeve.gestures.R;
import com.jeve.gestures.content.AppContent;
import com.jeve.gestures.content.ContentManager;

import java.util.List;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.ViewHolder> {

    private Context context;
    private List<AppContent> list;

    public MainAdapter(Context context, List<AppContent> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public MainAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.mainadapterlayout, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MainAdapter.ViewHolder viewHolder, int i) {
        viewHolder.setData(list.get(i));
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private TextView adapter_name, adapter_packagename;
        private RelativeLayout adapter_re;
        private AppCompatCheckBox checkbox;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkbox = itemView.findViewById(R.id.checkbox);
            adapter_re = itemView.findViewById(R.id.adapter_re);
            adapter_name = itemView.findViewById(R.id.adapter_name);
            adapter_packagename = itemView.findViewById(R.id.adapter_packagename);
        }

        public void setData(final AppContent content) {
            adapter_name.setText(content.getAppName());
            adapter_packagename.setText(content.getPackageName());

            checkbox.setChecked(content.isCheck());

            checkbox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    content.setCheck(isChecked);
                    ContentManager.getInstance().changeContent(content);
                    if (clickCallBack != null)
                        clickCallBack.clickCallBack(content);
                }
            });
        }
    }

    private ClickCallBack clickCallBack;

    public void setClickCallBack(ClickCallBack clickCallBack) {
        this.clickCallBack = clickCallBack;
    }

    public interface ClickCallBack {
        void clickCallBack(AppContent content);
    }

}
