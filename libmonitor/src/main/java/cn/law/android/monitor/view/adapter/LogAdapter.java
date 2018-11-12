package cn.law.android.monitor.view.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import cn.law.android.monitor.R;
import cn.law.android.monitor.view.entity.Logger;

public class LogAdapter extends RecyclerView.Adapter<LogAdapter.ViewHolder> {
    private Context mContext;
    private List<Logger> mLoggers;

    public LogAdapter(Context context, List<Logger> loggers) {
        this.mContext = context;
        this.mLoggers = loggers;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View convertView = LayoutInflater.from(mContext)
                .inflate(R.layout.adapter_logs, viewGroup, false);
        return new ViewHolder(convertView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int index) {
        Logger logger = mLoggers.get(index);
        holder.mTvTagName.setText("Tag-name " + index);
        holder.mTvTagContent.setText("Tag-content " + index);
    }

    @Override
    public int getItemCount() {
        return mLoggers == null ? 0 : mLoggers.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private TextView mTvTagName;
        private TextView mTvTagContent;

        private ViewHolder(@NonNull View itemView) {
            super(itemView);
            mTvTagName = itemView.findViewById(R.id.tv_tag_name);
            mTvTagContent = itemView.findViewById(R.id.tv_tag_content);
        }
    }
}
