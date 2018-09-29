package com.bignerdranch.android.task.Activities;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.bignerdranch.android.task.Activities.pojos.PhotoComment;

import java.util.List;
@Dao
public interface WordDao {
    @Insert
    void insert(PhotoComment photoComment);

    @Query("DELETE FROM comment_table")
    void deleteAll();

    @Query("SELECT * FROM comment_table WHERE photoId  = :userIds")
    List<PhotoComment> getCommentsByPhotoId(String userIds);


}
