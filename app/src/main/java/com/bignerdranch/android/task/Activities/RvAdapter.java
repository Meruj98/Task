package com.bignerdranch.android.task.Activities;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bignerdranch.android.task.Activities.pojos.PhotosResponse;
import com.bignerdranch.android.task.Activities.pojos.SizesResponse;
import com.bignerdranch.android.task.R;
import com.bumptech.glide.Glide;
import com.squareup.picasso.Picasso;

import java.util.List;

import rx.Observable;
import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class RvAdapter extends RecyclerView.Adapter<RvAdapter.MyViewHolder> {

    List<PhotosResponse.Photos.Photo> mList;
    ApiInterface mApiInterface;

    private String url = "";

    public RvAdapter(List<PhotosResponse.Photos.Photo> mList) {
        this.mList = mList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item, parent, false);
        MyViewHolder mvh = new MyViewHolder(view);
        mApiInterface = ApiClient.getClient().create(ApiInterface.class);
        return mvh;
    }


    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {
//        Picasso.get().load(mList.get(position).getImageUrl()).into(holder.mImageView);
//        Glide.with(holder.mImageView.getContext())
//        .load(mList.get(position).getImageUrl()).into(holder.mImageView);
        holder.mTextView.setText(mList.get(position).getTitle());
        Observable<SizesResponse> observable = mApiInterface.getSizes("flickr.photos.getSizes", "f53b6e674bdf079eb999bd7d87138d49",
                (mList.get(position).getId()), "json", "1");

        observable.subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<SizesResponse>() {

                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                    }

                    @Override
                    public void onNext(SizesResponse sizesResponse) {
                        url = sizesResponse.getSizes().getSize().get(0).getSource();
                        Glide.with(holder.mImageView.getContext())
                                .load(url).into(holder.mImageView);
                    }
                });

    }


    @Override
    public int getItemCount() {
        return mList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView mImageView;
        TextView mTextView;

        public MyViewHolder(final View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imagePhoto);
            mTextView = itemView.findViewById(R.id.textHidden);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {


                    Intent intent = new Intent(v.getContext(), SinglePhotoActivity.class);
                    mImageView.buildDrawingCache();
                    Bitmap image = mImageView.getDrawingCache();


                    Bundle extras = new Bundle();
                    extras.putParcelable("imagebitmap", image);
//                    extras.putString("imageUrl",url);
                    extras.putString("imageId", (mList.get(getAdapterPosition()).getId()));
                    intent.putExtras(extras);
                    intent.putExtra("2", mTextView.getText().toString());
                    v.getContext().startActivity(intent);


                }
            });
        }
    }
}
