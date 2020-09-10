package com.faruking.practiceproject2.adapter;

import android.content.Context;
import android.telecom.TelecomManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faruking.practiceproject2.R;
import com.faruking.practiceproject2.model.RetroSkillData;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SkilliqAdapter extends RecyclerView.Adapter<SkilliqAdapter.SkillIqViewHolder>{
    private List<RetroSkillData> mDataList;
    private Context mContext;

    public SkilliqAdapter(List<RetroSkillData> dataList, Context context) {
        mDataList = dataList;
        mContext = context;
    }

    @NonNull
    @Override
    public SkillIqViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_learning_leaders,parent,false);
        return new SkillIqViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SkillIqViewHolder holder, int position) {

        String score = mDataList.get(position).getScore().toString();
        String country = mDataList.get(position).getCountry();
        String scoreCountry = score + " Skill IQ Score, " + country;

        holder.txtName.setText(mDataList.get(position).getName());
        holder.txtCountry.setText(scoreCountry);

        Picasso.Builder builder = new Picasso.Builder(mContext);
        builder.downloader(new OkHttp3Downloader(mContext));
        builder.build().load(mDataList.get(position).getBadgeUrl())
                .error(R.drawable.ic_launcher_background)
                .into(holder.mImageView);
    }

    @Override
    public int getItemCount() {
        return mDataList.size();
    }

    class SkillIqViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView txtName;
        TextView txtCountry;
        private ImageView mImageView;

        public SkillIqViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.tvName);
            txtCountry = mView.findViewById(R.id.tvScoreCountry);
            mImageView = mView.findViewById(R.id.imageView);
        }
    }
}
