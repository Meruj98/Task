package com.bignerdranch.android.task.Activities.pojos;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SizesResponse {

@SerializedName("sizes")
@Expose
private Sizes sizes;
@SerializedName("stat")
@Expose
private String stat;

public Sizes getSizes() {
return sizes;
}

public void setSizes(Sizes sizes) {
this.sizes = sizes;
}

public String getStat() {
return stat;
}

public void setStat(String stat) {
this.stat = stat;
}

    public class Sizes {

        @SerializedName("canblog")
        @Expose
        private Integer canblog;
        @SerializedName("canprint")
        @Expose
        private Integer canprint;
        @SerializedName("candownload")
        @Expose
        private Integer candownload;
        @SerializedName("size")
        @Expose
        private List<Size> size = null;

        public Integer getCanblog() {
            return canblog;
        }

        public void setCanblog(Integer canblog) {
            this.canblog = canblog;
        }

        public Integer getCanprint() {
            return canprint;
        }

        public void setCanprint(Integer canprint) {
            this.canprint = canprint;
        }

        public Integer getCandownload() {
            return candownload;
        }

        public void setCandownload(Integer candownload) {
            this.candownload = candownload;
        }

        public List<Size> getSize() {
            return size;
        }

        public void setSize(List<Size> size) {
            this.size = size;
        }
        public class Size {

            @SerializedName("label")
            @Expose
            private String label;
            @SerializedName("width")
            @Expose
            private String width;
            @SerializedName("height")
            @Expose
            private Integer height;
            @SerializedName("source")
            @Expose
            private String source;
            @SerializedName("url")
            @Expose
            private String url;
            @SerializedName("media")
            @Expose
            private String media;

            public String getLabel() {
                return label;
            }

            public void setLabel(String label) {
                this.label = label;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public Integer getHeight() {
                return height;
            }

            public void setHeight(Integer height) {
                this.height = height;
            }

            public String getSource() {
                return source;
            }

            public void setSource(String source) {
                this.source = source;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getMedia() {
                return media;
            }

            public void setMedia(String media) {
                this.media = media;
            }

        }
    }
}