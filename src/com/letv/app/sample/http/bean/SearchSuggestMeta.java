
package com.letv.app.sample.http.bean;

public class SearchSuggestMeta{
    private String name;

    private String aid;

    private String vid;

    private String isEnd;// 是否完结 1:完结;0未完结

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAid() {
        return aid;
    }

    public void setAid(String aid) {
        this.aid = aid;
    }

    public String getVid() {
        return vid;
    }

    public void setVid(String vid) {
        this.vid = vid;
    }

    public boolean getIsEnd() {
        return "1".equals(isEnd);
    }

    public void setIsEnd(String isEnd) {
        this.isEnd = isEnd;
    }
}
