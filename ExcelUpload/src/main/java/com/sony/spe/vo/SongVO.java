package com.sony.spe.vo;

public class SongVO {

    private String cueTitle;
    private String parentSong;
    private Integer originalProductId;

    public String getCueTitle() {
        return cueTitle;
    }

    public void setCueTitle(String cueTitle) {
        this.cueTitle = cueTitle;
    }

    public String getParentSong() {
        return parentSong;
    }

    public void setParentSong(String parentSong) {
        this.parentSong = parentSong;
    }

    public Integer getOriginalProductId() {
        return originalProductId;
    }

    public void setOriginalProductId(Integer originalProductId) {
        this.originalProductId = originalProductId;
    }
}
