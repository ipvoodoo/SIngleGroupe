package ru.echodc.singlegroupe.rest.model.response;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.util.List;
import ru.echodc.singlegroupe.model.attachment.video.Video;

public class VideosResponse {
    public int count;
    @SerializedName("items")
    @Expose
    public List<Video> items;
}