package com.littlefisher.spider.bilibili.beans;

import java.util.List;

public class ResponseBean {


    /**
     * code : 0
     * message : 0
     * ttl : 1
     * data : {"aid":10594057,"videos":5,"tid":17,"tname":"单机游戏","copyright":1,"pic":"http://i1.hdslb.com/bfs/archive/dc23949cf056a5721b8219c6e387d7e0a95050ce.png","title":"【Dead Cells】《死亡细胞》从入门到通关全方位攻略！【全5P完结】","pubdate":1494882058,"ctime":1497424535,"desc":"非常简单易于上手的《死亡细胞》全方位解说攻略！\r\n前往 Steam 购买游戏：http://store.steampowered.com/app/588650/Dead_Cells/\r\n\r\n这次非常长，但是别担心！看不完你可以先收藏ww\r\n喜欢的话砸硬币支持一发呀~\r\n对了，听说「标签」里的Moy也是可以关注的哟 √\r\n\r\n微博：@-墨裔-\r\n扣扣群：333519172","state":0,"attribute":49152,"duration":8442,"rights":{"bp":0,"elec":0,"download":1,"movie":0,"pay":0,"hd5":0,"no_reprint":0,"autoplay":1,"ugc_pay":0,"is_cooperation":0},"owner":{"mid":585578,"name":"-墨裔-","face":"http://i2.hdslb.com/bfs/face/975af0f4797978eeba46ee621498b94dc14ad0bb.jpg"},"stat":{"aid":10594057,"view":373635,"danmaku":3622,"reply":475,"favorite":4659,"coin":2129,"share":744,"now_rank":0,"his_rank":0,"like":1385,"dislike":0},"dynamic":"","cid":17488737,"dimension":{"width":0,"height":0,"rotate":0},"no_cache":false,"pages":[{"cid":17488737,"page":1,"from":"vupload","part":"菜鸟级教程指南！前三大关~","duration":2151,"vid":"","weblink":"","dimension":{"width":0,"height":0,"rotate":0}},{"cid":17488733,"page":2,"from":"vupload","part":"四五六关！继续进军！","duration":2339,"vid":"","weblink":"","dimension":{"width":0,"height":0,"rotate":0}},{"cid":17488734,"page":3,"from":"vupload","part":"啊原来这个就是最终魔王吗\u2026\u2026","duration":1658,"vid":"","weblink":"","dimension":{"width":0,"height":0,"rotate":0}},{"cid":17488735,"page":4,"from":"vupload","part":"卷土重来！这次一定能成功！","duration":1750,"vid":"","weblink":"","dimension":{"width":0,"height":0,"rotate":0}},{"cid":17488736,"page":5,"from":"vupload","part":"最后愉快地开始了刷刷刷~♪","duration":544,"vid":"","weblink":"","dimension":{"width":0,"height":0,"rotate":0}}],"subtitle":{"allow_submit":false,"list":[]}}
     */

    private int code;
    private String message;
    private int ttl;
    private DataBean data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getTtl() {
        return ttl;
    }

    public void setTtl(int ttl) {
        this.ttl = ttl;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * aid : 10594057
         * videos : 5
         * tid : 17
         * tname : 单机游戏
         * copyright : 1
         * pic : http://i1.hdslb.com/bfs/archive/dc23949cf056a5721b8219c6e387d7e0a95050ce.png
         * title : 【Dead Cells】《死亡细胞》从入门到通关全方位攻略！【全5P完结】
         * pubdate : 1494882058
         * ctime : 1497424535
         * desc : 非常简单易于上手的《死亡细胞》全方位解说攻略！
         前往 Steam 购买游戏：http://store.steampowered.com/app/588650/Dead_Cells/

         这次非常长，但是别担心！看不完你可以先收藏ww
         喜欢的话砸硬币支持一发呀~
         对了，听说「标签」里的Moy也是可以关注的哟 √

         微博：@-墨裔-
         扣扣群：333519172
         * state : 0
         * attribute : 49152
         * duration : 8442
         * rights : {"bp":0,"elec":0,"download":1,"movie":0,"pay":0,"hd5":0,"no_reprint":0,"autoplay":1,"ugc_pay":0,"is_cooperation":0}
         * owner : {"mid":585578,"name":"-墨裔-","face":"http://i2.hdslb.com/bfs/face/975af0f4797978eeba46ee621498b94dc14ad0bb.jpg"}
         * stat : {"aid":10594057,"view":373635,"danmaku":3622,"reply":475,"favorite":4659,"coin":2129,"share":744,"now_rank":0,"his_rank":0,"like":1385,"dislike":0}
         * dynamic :
         * cid : 17488737
         * dimension : {"width":0,"height":0,"rotate":0}
         * no_cache : false
         * pages : [{"cid":17488737,"page":1,"from":"vupload","part":"菜鸟级教程指南！前三大关~","duration":2151,"vid":"","weblink":"","dimension":{"width":0,"height":0,"rotate":0}},{"cid":17488733,"page":2,"from":"vupload","part":"四五六关！继续进军！","duration":2339,"vid":"","weblink":"","dimension":{"width":0,"height":0,"rotate":0}},{"cid":17488734,"page":3,"from":"vupload","part":"啊原来这个就是最终魔王吗\u2026\u2026","duration":1658,"vid":"","weblink":"","dimension":{"width":0,"height":0,"rotate":0}},{"cid":17488735,"page":4,"from":"vupload","part":"卷土重来！这次一定能成功！","duration":1750,"vid":"","weblink":"","dimension":{"width":0,"height":0,"rotate":0}},{"cid":17488736,"page":5,"from":"vupload","part":"最后愉快地开始了刷刷刷~♪","duration":544,"vid":"","weblink":"","dimension":{"width":0,"height":0,"rotate":0}}]
         * subtitle : {"allow_submit":false,"list":[]}
         */

        private int aid;
        private int videos;
        private int tid;
        private String tname;
        private int copyright;
        private String pic;
        private String title;
        private int pubdate;
        private int ctime;
        private String desc;
        private int state;
        private int attribute;
        private int duration;
        private RightsBean rights;
        private OwnerBean owner;
        private StatBean stat;
        private String dynamic;
        private int cid;
        private DimensionBean dimension;
        private boolean no_cache;
        private SubtitleBean subtitle;
        private List<PagesBean> pages;

        public int getAid() {
            return aid;
        }

        public void setAid(int aid) {
            this.aid = aid;
        }

        public int getVideos() {
            return videos;
        }

        public void setVideos(int videos) {
            this.videos = videos;
        }

        public int getTid() {
            return tid;
        }

        public void setTid(int tid) {
            this.tid = tid;
        }

        public String getTname() {
            return tname;
        }

        public void setTname(String tname) {
            this.tname = tname;
        }

        public int getCopyright() {
            return copyright;
        }

        public void setCopyright(int copyright) {
            this.copyright = copyright;
        }

        public String getPic() {
            return pic;
        }

        public void setPic(String pic) {
            this.pic = pic;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public int getPubdate() {
            return pubdate;
        }

        public void setPubdate(int pubdate) {
            this.pubdate = pubdate;
        }

        public int getCtime() {
            return ctime;
        }

        public void setCtime(int ctime) {
            this.ctime = ctime;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getAttribute() {
            return attribute;
        }

        public void setAttribute(int attribute) {
            this.attribute = attribute;
        }

        public int getDuration() {
            return duration;
        }

        public void setDuration(int duration) {
            this.duration = duration;
        }

        public RightsBean getRights() {
            return rights;
        }

        public void setRights(RightsBean rights) {
            this.rights = rights;
        }

        public OwnerBean getOwner() {
            return owner;
        }

        public void setOwner(OwnerBean owner) {
            this.owner = owner;
        }

        public StatBean getStat() {
            return stat;
        }

        public void setStat(StatBean stat) {
            this.stat = stat;
        }

        public String getDynamic() {
            return dynamic;
        }

        public void setDynamic(String dynamic) {
            this.dynamic = dynamic;
        }

        public int getCid() {
            return cid;
        }

        public void setCid(int cid) {
            this.cid = cid;
        }

        public DimensionBean getDimension() {
            return dimension;
        }

        public void setDimension(DimensionBean dimension) {
            this.dimension = dimension;
        }

        public boolean isNo_cache() {
            return no_cache;
        }

        public void setNo_cache(boolean no_cache) {
            this.no_cache = no_cache;
        }

        public SubtitleBean getSubtitle() {
            return subtitle;
        }

        public void setSubtitle(SubtitleBean subtitle) {
            this.subtitle = subtitle;
        }

        public List<PagesBean> getPages() {
            return pages;
        }

        public void setPages(List<PagesBean> pages) {
            this.pages = pages;
        }

        public static class RightsBean {
            /**
             * bp : 0
             * elec : 0
             * download : 1
             * movie : 0
             * pay : 0
             * hd5 : 0
             * no_reprint : 0
             * autoplay : 1
             * ugc_pay : 0
             * is_cooperation : 0
             */

            private int bp;
            private int elec;
            private int download;
            private int movie;
            private int pay;
            private int hd5;
            private int no_reprint;
            private int autoplay;
            private int ugc_pay;
            private int is_cooperation;

            public int getBp() {
                return bp;
            }

            public void setBp(int bp) {
                this.bp = bp;
            }

            public int getElec() {
                return elec;
            }

            public void setElec(int elec) {
                this.elec = elec;
            }

            public int getDownload() {
                return download;
            }

            public void setDownload(int download) {
                this.download = download;
            }

            public int getMovie() {
                return movie;
            }

            public void setMovie(int movie) {
                this.movie = movie;
            }

            public int getPay() {
                return pay;
            }

            public void setPay(int pay) {
                this.pay = pay;
            }

            public int getHd5() {
                return hd5;
            }

            public void setHd5(int hd5) {
                this.hd5 = hd5;
            }

            public int getNo_reprint() {
                return no_reprint;
            }

            public void setNo_reprint(int no_reprint) {
                this.no_reprint = no_reprint;
            }

            public int getAutoplay() {
                return autoplay;
            }

            public void setAutoplay(int autoplay) {
                this.autoplay = autoplay;
            }

            public int getUgc_pay() {
                return ugc_pay;
            }

            public void setUgc_pay(int ugc_pay) {
                this.ugc_pay = ugc_pay;
            }

            public int getIs_cooperation() {
                return is_cooperation;
            }

            public void setIs_cooperation(int is_cooperation) {
                this.is_cooperation = is_cooperation;
            }
        }

        public static class OwnerBean {
            /**
             * mid : 585578
             * name : -墨裔-
             * face : http://i2.hdslb.com/bfs/face/975af0f4797978eeba46ee621498b94dc14ad0bb.jpg
             */

            private int mid;
            private String name;
            private String face;

            public int getMid() {
                return mid;
            }

            public void setMid(int mid) {
                this.mid = mid;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getFace() {
                return face;
            }

            public void setFace(String face) {
                this.face = face;
            }
        }

        public static class StatBean {
            /**
             * aid : 10594057
             * view : 373635
             * danmaku : 3622
             * reply : 475
             * favorite : 4659
             * coin : 2129
             * share : 744
             * now_rank : 0
             * his_rank : 0
             * like : 1385
             * dislike : 0
             */

            private int aid;
            private int view;
            private int danmaku;
            private int reply;
            private int favorite;
            private int coin;
            private int share;
            private int now_rank;
            private int his_rank;
            private int like;
            private int dislike;

            public int getAid() {
                return aid;
            }

            public void setAid(int aid) {
                this.aid = aid;
            }

            public int getView() {
                return view;
            }

            public void setView(int view) {
                this.view = view;
            }

            public int getDanmaku() {
                return danmaku;
            }

            public void setDanmaku(int danmaku) {
                this.danmaku = danmaku;
            }

            public int getReply() {
                return reply;
            }

            public void setReply(int reply) {
                this.reply = reply;
            }

            public int getFavorite() {
                return favorite;
            }

            public void setFavorite(int favorite) {
                this.favorite = favorite;
            }

            public int getCoin() {
                return coin;
            }

            public void setCoin(int coin) {
                this.coin = coin;
            }

            public int getShare() {
                return share;
            }

            public void setShare(int share) {
                this.share = share;
            }

            public int getNow_rank() {
                return now_rank;
            }

            public void setNow_rank(int now_rank) {
                this.now_rank = now_rank;
            }

            public int getHis_rank() {
                return his_rank;
            }

            public void setHis_rank(int his_rank) {
                this.his_rank = his_rank;
            }

            public int getLike() {
                return like;
            }

            public void setLike(int like) {
                this.like = like;
            }

            public int getDislike() {
                return dislike;
            }

            public void setDislike(int dislike) {
                this.dislike = dislike;
            }
        }

        public static class DimensionBean {
            /**
             * width : 0
             * height : 0
             * rotate : 0
             */

            private int width;
            private int height;
            private int rotate;

            public int getWidth() {
                return width;
            }

            public void setWidth(int width) {
                this.width = width;
            }

            public int getHeight() {
                return height;
            }

            public void setHeight(int height) {
                this.height = height;
            }

            public int getRotate() {
                return rotate;
            }

            public void setRotate(int rotate) {
                this.rotate = rotate;
            }
        }

        public static class SubtitleBean {
            /**
             * allow_submit : false
             * list : []
             */

            private boolean allow_submit;
            private List<?> list;

            public boolean isAllow_submit() {
                return allow_submit;
            }

            public void setAllow_submit(boolean allow_submit) {
                this.allow_submit = allow_submit;
            }

            public List<?> getList() {
                return list;
            }

            public void setList(List<?> list) {
                this.list = list;
            }
        }

        public static class PagesBean {
            /**
             * cid : 17488737
             * page : 1
             * from : vupload
             * part : 菜鸟级教程指南！前三大关~
             * duration : 2151
             * vid :
             * weblink :
             * dimension : {"width":0,"height":0,"rotate":0}
             */

            private int cid;
            private int page;
            private String from;
            private String part;
            private int duration;
            private String vid;
            private String weblink;
            private DimensionBeanX dimension;

            public int getCid() {
                return cid;
            }

            public void setCid(int cid) {
                this.cid = cid;
            }

            public int getPage() {
                return page;
            }

            public void setPage(int page) {
                this.page = page;
            }

            public String getFrom() {
                return from;
            }

            public void setFrom(String from) {
                this.from = from;
            }

            public String getPart() {
                return part;
            }

            public void setPart(String part) {
                this.part = part;
            }

            public int getDuration() {
                return duration;
            }

            public void setDuration(int duration) {
                this.duration = duration;
            }

            public String getVid() {
                return vid;
            }

            public void setVid(String vid) {
                this.vid = vid;
            }

            public String getWeblink() {
                return weblink;
            }

            public void setWeblink(String weblink) {
                this.weblink = weblink;
            }

            public DimensionBeanX getDimension() {
                return dimension;
            }

            public void setDimension(DimensionBeanX dimension) {
                this.dimension = dimension;
            }

            public static class DimensionBeanX {
                /**
                 * width : 0
                 * height : 0
                 * rotate : 0
                 */

                private int width;
                private int height;
                private int rotate;

                public int getWidth() {
                    return width;
                }

                public void setWidth(int width) {
                    this.width = width;
                }

                public int getHeight() {
                    return height;
                }

                public void setHeight(int height) {
                    this.height = height;
                }

                public int getRotate() {
                    return rotate;
                }

                public void setRotate(int rotate) {
                    this.rotate = rotate;
                }
            }
        }
    }
}
