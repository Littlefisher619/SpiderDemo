package com.littlefisher.spider.xbiquge.model;

public class Chapter {
    public String getNovelName() {
        return novelName;
    }

    public void setNovelName(String novelName) {
        this.novelName = novelName;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public String getChapterURL() {
        return chapterURL;
    }

    public void setChapterURL(String chapterURL) {
        this.chapterURL = chapterURL;
    }

    private String novelName,chapterName,chapterURL;

    public Chapter(String novelName, String chapterName, String chapterURL) {
        this.novelName = novelName;
        this.chapterName = chapterName;
        this.chapterURL = chapterURL;
    }
}
