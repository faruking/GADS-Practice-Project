package com.faruking.practiceproject2.service;



import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface PostSubmitData {
    String FORM_ID = "1FAIpQLSf9d1TcNU6zc6KR8bSEM41Z1g1zl35cwZr2xyjIhaMAz8WChQ/formResponse";
    @POST(FORM_ID)
    @FormUrlEncoded

    Call<Void> postSubmission(@Field("entry.1877115667") String name,
                              @Field("entry.2006916086") String lastName,
                              @Field("entry.1824927963") String eMail,
                              @Field("entry.284483984") String linkToProject);


}
