package com.bignerdranch.android.task.Activities;

import android.graphics.Bitmap;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bignerdranch.android.task.Activities.pojos.PhotoComment;
import com.bignerdranch.android.task.R;

import java.util.ArrayList;
import java.util.List;

public class SinglePhotoActivity extends AppCompatActivity {
    public static final String EXTRA_REPLY = "com.example.android.wordlistsql.REPLY";
    ImageView singlePhotoImage;
    EditText mEditText;
    private RecyclerView recyclerView;
    public static final int NEW_WORD_ACTIVITY_REQUEST_CODE = 1;

    private String text;
    private TextView mTextView, mTextView2;
    private String text2;
    List<WordListAdapter> mList;
    private WordDao mWordDao;
    private WordRoomDatabase db;

    private String photoId;
    private Bitmap photo;
    private AppExecutors appExecutors;
    private WordListAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_photo);

        db = WordRoomDatabase.getDatabase(getApplication());
        mWordDao = db.wordDao();

        mList = new ArrayList<>();
        Bundle extras = getIntent().getExtras();
        photo =(Bitmap)extras.getParcelable("imagebitmap");
        photoId = extras.getString("imageId");
        singlePhotoImage = findViewById(R.id.singlePhotoImage);
        singlePhotoImage.setImageBitmap(photo);




        recyclerView = findViewById(R.id.recyclerview);
        adapter = new WordListAdapter(this);
        recyclerView.setAdapter(adapter);
        final Button mButton = findViewById(R.id.sendButton);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        appExecutors = new AppExecutors();
        getComments();
        mEditText = findViewById(R.id.textComment);

        text = getIntent().getStringExtra("2");
        mTextView = findViewById(R.id.textViewTitle);
        mTextView.setText(text);



        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!mEditText.getText().toString().isEmpty()) {
                    appExecutors.diskIO().execute(new Runnable() {
                        @Override
                        public void run() {
                            mWordDao.insert(new PhotoComment(photoId, mEditText.getText().toString()));
                            getComments();
                        }
                    });
                }else {
                    mEditText.setError("Empty Comment");
                }
            }
        });


    }

    private void getComments() {
        appExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<PhotoComment> photoComments = mWordDao.getCommentsByPhotoId(photoId);
                appExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        mEditText.setText("");
                        adapter.setWords(photoComments);
                    }
                });
            }
        });
    }


}
