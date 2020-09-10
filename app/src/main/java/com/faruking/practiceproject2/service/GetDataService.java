package com.faruking.practiceproject2.service;

import com.faruking.practiceproject2.model.RetroSkillData;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GetDataService {
    @GET("/api/skilliq")
    Call<List<RetroSkillData>> getAllSkillIqData();
}
