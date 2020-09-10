package com.faruking.practiceproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.faruking.practiceproject2.adapter.SkilliqAdapter;
import com.faruking.practiceproject2.model.RetroSkillData;
import com.faruking.practiceproject2.network.RetrofitClientInstance;
import com.faruking.practiceproject2.service.GetDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class SkillIQDataFragment extends Fragment {
    private SkilliqAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private View mView;

    public SkillIQDataFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetDataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(GetDataService.class);
        Call<List<RetroSkillData>> call = service.getAllSkillIqData();
        call.enqueue(new Callback<List<RetroSkillData>>() {
            @Override
            public void onResponse(Call<List<RetroSkillData>> call, Response<List<RetroSkillData>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroSkillData>> call, Throwable t) {
                Toast.makeText(mView.getContext(),"Error loading data",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void generateDataList(List<RetroSkillData> SkillIqList) {
        mRecyclerView = mView.findViewById(R.id.rvSkillIq);
        mAdapter = new SkilliqAdapter(SkillIqList,mView.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_skill_i_q_data, container, false);
        return mView;
    }
}