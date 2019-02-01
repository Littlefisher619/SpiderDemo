package com.littlefisher.spider.xbiquge.model;

public class Novel {
    public String getNovelURL() {
        return novelURL;
    }

    public void setNovelURL(String novelURL) {
        this.novelURL = novelURL;
    }

    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getProgress() {
        return progress;
    }

    public void setProgress(String progress) {
        this.progress = progress;
    }

    public Novel(String novelURL, String novelName, String progress) {
        this.novelURL = novelURL;
        this.novelName = novelName;
        this.progress = progress;
    }

    private String novelURL,novelName,progress;

}
