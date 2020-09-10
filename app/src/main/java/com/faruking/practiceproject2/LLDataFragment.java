package com.faruking.practiceproject2;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.faruking.practiceproject2.adapter.LLAdapter;
import com.faruking.practiceproject2.model.RetroLLData;
import com.faruking.practiceproject2.network.RetrofitClientInstance;
import com.faruking.practiceproject2.service.GetLLDataService;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LLDataFragment extends Fragment {
    private View mView;

    public LLDataFragment() {
        // Required empty public constructor
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        GetLLDataService service = RetrofitClientInstance.getRetrofitInstance()
                .create(GetLLDataService.class);
        Call<List<RetroLLData>> call = service.getAllLLData();
        call.enqueue(new Callback<List<RetroLLData>>() {
            @Override
            public void onResponse(Call<List<RetroLLData>> call, Response<List<RetroLLData>> response) {
                generateDataList(response.body());
            }

            @Override
            public void onFailure(Call<List<RetroLLData>> call, Throwable t) {
                Toast.makeText(mView.getContext(),"Error loading data",Toast.LENGTH_LONG).show();
            }
        });
    }

    private void generateDataList(List<RetroLLData> retroLLDataList) {
        RecyclerView recyclerView = mView.findViewById(R.id.rv_LLData);
        LLAdapter adapter = new LLAdapter(retroLLDataList, mView.getContext());
        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext(),RecyclerView.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        mView = inflater.inflate(R.layout.fragment_l_l_data, container, false);
        return mView;
    }

}
