package com.bignerdranch.android.task.Activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.Toast;
import rx.Observable;
import rx.Observer;
import rx.Scheduler;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

import com.bignerdranch.android.task.Activities.pojos.PhotosResponse;
import com.bignerdranch.android.task.R;


public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView ;
    public ApiInterface mApiInterface;
    private Button mButton,mButtonList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.rv);

        mButton =findViewById(R.id.gridButton);


        mButtonList = findViewById(R.id.buttonList);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);

        Observable<PhotosResponse> observable = mApiInterface.getPhotos("flickr.galleries.getPhotos","f53b6e674bdf079eb999bd7d87138d49"
        ,"66911286-72157647277042064","json","1");

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<PhotosResponse>() {
                    @Override
                    public void onCompleted() {
//                        Log.e("onCompleted:","called");
                        Toast.makeText(MainActivity.this, "View has been updated", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MainActivity.this, e.getMessage().toString(), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onNext(PhotosResponse photosResponse) {
                        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
                            mRecyclerView.setAdapter(new RvAdapter(photosResponse.getPhotos().getPhoto()));
                        mRecyclerView.setLayoutManager(linearLayoutManager);
                    }
                });


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mRecyclerView.setLayoutManager(new GridLayoutManager(MainActivity.this,2));
            }
        });

        mButtonList.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    mRecyclerView.setLayoutManager(new LinearLayoutManager(MainActivity.this));
            }
        });

//          getCategories();

    }

//    private void getCategories(){
//        mApiInterface.getCategories().enqueue(new Callback<CategoriesResponse>() {
//            @Override
//            public void onResponse(Call<CategoriesResponse> call, Response<CategoriesResponse> response) {
//                LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MainActivity.this);
//                mRecyclerView.setAdapter(new RvAdapter(response.body().getCategories().getData()));
//                mRecyclerView.setLayoutManager(linearLayoutManager);
//            }
//
//            @Override
//            public void onFailure(Call<CategoriesResponse> call, Throwable t) {
//                Toast.makeText(MainActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
//            }
//        });
//    }

}
