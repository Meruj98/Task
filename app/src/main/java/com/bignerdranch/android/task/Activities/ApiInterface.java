package com.bignerdranch.android.task.Activities;


import com.bignerdranch.android.task.Activities.pojos.PhotosResponse;
import com.bignerdranch.android.task.Activities.pojos.SizesResponse;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;


public interface ApiInterface {

//    Call<CategoriesResponse> getCategories();
    @GET("services/rest/")
    Observable<PhotosResponse> getPhotos(@Query("method")String method,@Query("api_key")String api_key,
                                         @Query("gallery_id")String gallery_id,@Query("format")String format
                                            ,@Query("nojsoncallback")String nojsoncallback);
    @GET("services/rest/")
    Observable<SizesResponse> getSizes(@Query("method")String method,@Query("api_key")String api_key,
                                       @Query("photo_id")String photo_id,@Query("format")String format,
                                       @Query("nojsoncallback")String nojsoncallback);
}
