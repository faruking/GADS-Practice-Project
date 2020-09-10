package com.faruking.practiceproject2.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.faruking.practiceproject2.R;
import com.faruking.practiceproject2.model.RetroLLData;
import com.squareup.picasso.OkHttp3Downloader;
import com.squareup.picasso.Picasso;

import java.util.List;

public class LLAdapter extends RecyclerView.Adapter<LLAdapter.LLDataViewHolder>{
    private List<RetroLLData> mDataList;
    private Context mContext;

    public LLAdapter(List<RetroLLData> dataList, Context context) {
        mDataList = dataList;
        mContext = context;
    }

    @NonNull
    @Override
    public LLDataViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_learning_leaders,parent,false);
        return new LLDataViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LLDataViewHolder holder, int position) {

        String hours = mDataList.get(position).getHours().toString();
        String country = mDataList.get(position).getCountry();
        String hoursCountry = hours + " learning hours, " + country;

        holder.txtName.setText(mDataList.get(position).getName());
        holder.txtCountry.setText(hoursCountry);

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

    static class LLDataViewHolder extends RecyclerView.ViewHolder{
        public final View mView;
        TextView txtName;
        TextView txtCountry;
        private ImageView mImageView;

        public LLDataViewHolder(@NonNull View itemView) {
            super(itemView);
            mView = itemView;

            txtName = mView.findViewById(R.id.tvName);
            txtCountry = mView.findViewById(R.id.tvScoreCountry);
            mImageView = mView.findViewById(R.id.imageView);
        }
    }
}
