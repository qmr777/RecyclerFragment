package com.example.administrator.androidtest.Bean;

import java.util.List;

/**
 * Created by qmr on 2016/9/1.
 */
public class AndroidDataBean {

    /**
     * _id : 57c67ca8421aa9125d96f543
     * createdAt : 2016-08-31T14:43:52.969Z
     * desc : 【Android干货】Matrix详解，这应该是目前最详细的一篇讲解Matrix的中文文章了。
     * publishedAt : 2016-09-01T11:31:19.288Z
     * source : web
     * type : Android
     * url : http://www.gcssloop.com/2015/02/Matrix_Method/
     * used : true
     * who : sloop
     */

    private String _id;
    private String createdAt;
    private String desc;
    private String publishedAt;
    private String source;
    private String type;
    private String url;
    private boolean used;
    private String who;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public String getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(String publishedAt) {
        this.publishedAt = publishedAt;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isUsed() {
        return used;
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public String getWho() {
        return who;
    }

    public void setWho(String who) {
        this.who = who;
    }
}