package com.bignerdranch.android.task.Activities.pojos;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "comment_table")
public class PhotoComment {
    @PrimaryKey
    @ColumnInfo(name = "id")
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public PhotoComment(String photoId, String content) {
        this.photoId = photoId;
        this.content = content;
    }

    @ColumnInfo(name = "photoId")
    private String photoId;

    @ColumnInfo(name = "content")
    private String content;

    public String getPhotoId() {
        return photoId;
    }

    public void setPhotoId(String photoId) {
        this.photoId = photoId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
