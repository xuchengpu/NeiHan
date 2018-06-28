package com.xcp.neihan.bean;

import java.util.List;

/**
 * Created by 许成谱 on 2017/2/23 14:56.
 * qq:1550540124
 * for:
 */

public class HomeBean {
    @Override
    public String toString() {
        return "HomeBean{" +
                "code=" + code +
                ", msg='" + msg + '\'' +
                ", result=" + result +
                '}';
    }

    /**
     * code : 200
     * msg : 璇锋眰鎴愬姛
     * result : {"act_info":[{"icon_url":"/operation/img/1478169868/1478761370286.png","name":"灏氱璋风鍒╀笓鍖轰箣111.1涓撳尯","url":"/oper/1478169868app.html"},{"icon_url":"/operation/img/1478763176/1478762941492.png","name":"灏氱璋风鍒╀笓鍖� 榛勯噾鐙楃伯闄愰噺11.1鍏冩姠","url":"/oper/1478763176app.html"}],"banner_info":[{"image":"/1478770583834.png","option":3,"type":0,"value":{"url":"/act20161111?cyc_app=1"}},{"image":"/1478770583835.png","option":2,"type":0,"value":{"url":"/act20161111?cyc_app=1"}},{"image":"/1478770583836.png","option":1,"type":0,"value":{"url":"/act20161111?cyc_app=1"}}],"channel_info":[{"channel_name":"鏈嶉グ","image":"/app/img/menu-cyc.png","option":2,"type":1,"value":{"channel_id":"8"}},{"channel_name":"娓告垙","image":"/app/img/menu-game.png","option":2,"type":1,"value":{"channel_id":"4"}},{"channel_name":"鍔ㄦ极","image":"/app/img/menu-carttoon.png","option":2,"type":1,"value":{"channel_id":"3"}},{"channel_name":"瑁呮壆","image":"/app/img/menu-cosplay.png","option":2,"type":1,"value":{"channel_id":"5"}},{"channel_name":"鍙ら","image":"/app/img/menu-oldage.png","option":2,"type":1,"value":{"channel_id":"6"}},{"channel_name":"婕睍绁ㄥ姟","image":"/app/img/menu-collect.png","option":2,"type":1,"value":{"channel_id":"9"}},{"channel_name":"鏂囧叿","image":"/app/img/menu-stationery.png","option":2,"type":1,"value":{"channel_id":"11"}},{"channel_name":"闆堕","image":"/app/img/menu-snack.png","option":2,"type":1,"value":{"channel_id":"10"}},{"channel_name":"棣栭グ","image":"/app/img/menu-jewelry.png","option":2,"type":1,"value":{"channel_id":"12"}},{"channel_name":"鏇村","image":"/app/img/menu-more.png","option":6,"type":1,"value":{"channel_id":"13"}}],"hot_info":[{"cover_price":"159.00","figure":"/1477984921265.jpg","name":"鐜拌揣銆愪竴鏂瑰皹瀵般\u20ac戝墤渚犳儏缂樹笁鍓戜笁涓冪 骞插皢鑾偑 90姗欐浠跨儳钃濆鍙ゅ绨�","product_id":"9356"},{"cover_price":"159.00","figure":"/1477984931882.jpg","name":"鐜拌揣銆愪竴鏂瑰皹瀵般\u20ac戝墤渚犳儏缂樹笁鍓戜笁涓冪 骞插皢鑾偑 90姗欐浠跨儳钃濆鍙ゅ绨�-鐗瑰吀鐗�","product_id":"10391"},{"cover_price":"29.00","figure":"/1452161899947.jpg","name":"銆愬柕楣块叡銆戣秴钀� 鍋囬\u20ac忚倝 鎷兼帴 韪╄剼杩囪啙鎵撳簳琚� 瑁よ-鍔犵粧淇濇殩","product_id":"3831"},{"cover_price":"199.00","figure":"/1447232577216.jpg","name":"銆愭极韪\u20ac戝師鍒� 瀹磶楠� 榫欑尗 鍙埍闆湴闈村姩婕繚鏆栭瀷鍛ㄨ竟鍐姣涚粧闉嬪瓙","product_id":"2691"},{"cover_price":"70.00","figure":"/1474370572805.jpg","name":"銆愮幇璐с\u20ac戙\u20ac怗IRLISM灏戝コ涓讳箟銆� 绗�4鏈� 2016澶忕鍒� lolita","product_id":"9414"},{"cover_price":"4.80","figure":"/1465268743242.jpg","name":"銆愯壘婕\u20ac戝叏鑱岄珮鎵�-铚滈ク绯诲垪","product_id":"6869"},{"cover_price":"143.10","figure":"/1477360350123.png","name":"銆愰珮鍐风尗銆戞殫榛戠郴杞鐥呭▏鑽父灏戝コ绉嬭鍋囦咯浠跺姞鍘氬崼琛ｅ附琛�  棰勫敭","product_id":"10136"},{"cover_price":"329.00","figure":"/supplier/1467702094592.jpg","name":"銆恮acom銆戞暟浣嶆澘鐢绘澘ctl471鎵嬬粯鏉縝amboo鐢佃剳缁樼敾鐢靛瓙缁樺浘鏉縫s","product_id":"7752"}],"recommend_info":[{"cover_price":"138.00","figure":"/supplier/1478873740576.jpg","name":"銆愬皻纭呰胺銆戞棩甯� 钀岀郴灏忓ぉ浣垮崼琛�--鐧借壊娆�","product_id":"10659"},{"cover_price":"138.00","figure":"/supplier/1478873369497.jpg","name":"銆愬皻纭呰胺銆戞棩甯� 钀岀郴灏忔伓榄斿崼琛�--榛戣壊娆�","product_id":"10658"},{"cover_price":"32.00","figure":"/supplier/1478867468462.jpg","name":"棰勫敭銆愭极鍙嬫枃鍖栥\u20ac戝叏鑱岄珮鎵�6 澶╅椈瑙掑窛  娴佸湴寰界珷 鍏ㄦ柊鍏歌棌鐗� 铦磋澏钃� 鐚爲缁� 璧犵簿缇庡ぇ娴锋姤+棣栧埛闄愬畾璧�2017骞存椿椤靛彴鍘�","product_id":"10657"},{"cover_price":"18.00","figure":"/1478860081305.jpg","name":"銆愬垢杩愭槦銆戠儷閲戦洩绾篔SK鐨勯厤浠跺皬鐗╋細鎵� 濂椼\u20ac侀」閾�","product_id":"10656"},{"cover_price":"178.00","figure":"/1478850234799.jpg","name":"銆愬皻纭呰胺銆戝鐙愬浘鑵� 闃撮槼甯堝悓浜哄厓绱犲崼琛�","product_id":"10655"},{"cover_price":"138.00","figure":"/1478849792177.jpg","name":"銆愬皻纭呰胺銆戝闄㈤ 鏃ュ父鐧炬惌 瀹芥澗闀胯琛～","product_id":"10654"}],"seckill_info":{"end_time":"1479052800","list":[{"cover_price":"20.00","figure":"/1478489000522.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�600-120浼樻儬鍒�","origin_price":"20.00","product_id":"7100"},{"cover_price":"10.00","figure":"/1478489035167.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�300-80浼樻儬鍒�","origin_price":"10.00","product_id":"7101"},{"cover_price":"5.00","figure":"/1478489878735.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�160-40浼樻儬鍒�","origin_price":"5.00","product_id":"7102"},{"cover_price":"49.00","figure":"/1475045805488.jpg","name":"銆愬彜椋庡師鍒涖\u20ac� 鑷姩鐩存焺浼� 鏅撮洦浼� 銆愪簯楣ゆ父銆戝寘閭�  鏂板鎶樺彔浼�","origin_price":"69.00","product_id":"9593"},{"cover_price":"5.00","figure":"/1478678511949.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�60-20浼樻儬鍒�","origin_price":"5.00","product_id":"10536"},{"cover_price":"49.00","figure":"/1438680345318.jpg","name":"銆愬彜椋庡師鍒涖\u20ac� 鑷姩鐩存焺浼� 鏅撮洦浼� 銆愰潚绔硅瘝銆戝寘閭�  鏂板鎶樺彔浼�","origin_price":"59.00","product_id":"555"}],"start_time":"1478772000"}}
     */

    private int code;
    private String msg;
    private ResultBean result;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public static class ResultBean {
        @Override
        public String toString() {
            return "ResultBean{" +
                    "seckill_info=" + seckill_info +
                    ", act_info=" + act_info +
                    ", banner_info=" + banner_info +
                    ", channel_info=" + channel_info +
                    ", hot_info=" + hot_info +
                    ", recommend_info=" + recommend_info +
                    '}';
        }

        /**
         * act_info : [{"icon_url":"/operation/img/1478169868/1478761370286.png","name":"灏氱璋风鍒╀笓鍖轰箣111.1涓撳尯","url":"/oper/1478169868app.html"},{"icon_url":"/operation/img/1478763176/1478762941492.png","name":"灏氱璋风鍒╀笓鍖� 榛勯噾鐙楃伯闄愰噺11.1鍏冩姠","url":"/oper/1478763176app.html"}]
         * banner_info : [{"image":"/1478770583834.png","option":3,"type":0,"value":{"url":"/act20161111?cyc_app=1"}},{"image":"/1478770583835.png","option":2,"type":0,"value":{"url":"/act20161111?cyc_app=1"}},{"image":"/1478770583836.png","option":1,"type":0,"value":{"url":"/act20161111?cyc_app=1"}}]
         * channel_info : [{"channel_name":"鏈嶉グ","image":"/app/img/menu-cyc.png","option":2,"type":1,"value":{"channel_id":"8"}},{"channel_name":"娓告垙","image":"/app/img/menu-game.png","option":2,"type":1,"value":{"channel_id":"4"}},{"channel_name":"鍔ㄦ极","image":"/app/img/menu-carttoon.png","option":2,"type":1,"value":{"channel_id":"3"}},{"channel_name":"瑁呮壆","image":"/app/img/menu-cosplay.png","option":2,"type":1,"value":{"channel_id":"5"}},{"channel_name":"鍙ら","image":"/app/img/menu-oldage.png","option":2,"type":1,"value":{"channel_id":"6"}},{"channel_name":"婕睍绁ㄥ姟","image":"/app/img/menu-collect.png","option":2,"type":1,"value":{"channel_id":"9"}},{"channel_name":"鏂囧叿","image":"/app/img/menu-stationery.png","option":2,"type":1,"value":{"channel_id":"11"}},{"channel_name":"闆堕","image":"/app/img/menu-snack.png","option":2,"type":1,"value":{"channel_id":"10"}},{"channel_name":"棣栭グ","image":"/app/img/menu-jewelry.png","option":2,"type":1,"value":{"channel_id":"12"}},{"channel_name":"鏇村","image":"/app/img/menu-more.png","option":6,"type":1,"value":{"channel_id":"13"}}]
         * hot_info : [{"cover_price":"159.00","figure":"/1477984921265.jpg","name":"鐜拌揣銆愪竴鏂瑰皹瀵般\u20ac戝墤渚犳儏缂樹笁鍓戜笁涓冪 骞插皢鑾偑 90姗欐浠跨儳钃濆鍙ゅ绨�","product_id":"9356"},{"cover_price":"159.00","figure":"/1477984931882.jpg","name":"鐜拌揣銆愪竴鏂瑰皹瀵般\u20ac戝墤渚犳儏缂樹笁鍓戜笁涓冪 骞插皢鑾偑 90姗欐浠跨儳钃濆鍙ゅ绨�-鐗瑰吀鐗�","product_id":"10391"},{"cover_price":"29.00","figure":"/1452161899947.jpg","name":"銆愬柕楣块叡銆戣秴钀� 鍋囬\u20ac忚倝 鎷兼帴 韪╄剼杩囪啙鎵撳簳琚� 瑁よ-鍔犵粧淇濇殩","product_id":"3831"},{"cover_price":"199.00","figure":"/1447232577216.jpg","name":"銆愭极韪\u20ac戝師鍒� 瀹磶楠� 榫欑尗 鍙埍闆湴闈村姩婕繚鏆栭瀷鍛ㄨ竟鍐姣涚粧闉嬪瓙","product_id":"2691"},{"cover_price":"70.00","figure":"/1474370572805.jpg","name":"銆愮幇璐с\u20ac戙\u20ac怗IRLISM灏戝コ涓讳箟銆� 绗�4鏈� 2016澶忕鍒� lolita","product_id":"9414"},{"cover_price":"4.80","figure":"/1465268743242.jpg","name":"銆愯壘婕\u20ac戝叏鑱岄珮鎵�-铚滈ク绯诲垪","product_id":"6869"},{"cover_price":"143.10","figure":"/1477360350123.png","name":"銆愰珮鍐风尗銆戞殫榛戠郴杞鐥呭▏鑽父灏戝コ绉嬭鍋囦咯浠跺姞鍘氬崼琛ｅ附琛�  棰勫敭","product_id":"10136"},{"cover_price":"329.00","figure":"/supplier/1467702094592.jpg","name":"銆恮acom銆戞暟浣嶆澘鐢绘澘ctl471鎵嬬粯鏉縝amboo鐢佃剳缁樼敾鐢靛瓙缁樺浘鏉縫s","product_id":"7752"}]
         * recommend_info : [{"cover_price":"138.00","figure":"/supplier/1478873740576.jpg","name":"銆愬皻纭呰胺銆戞棩甯� 钀岀郴灏忓ぉ浣垮崼琛�--鐧借壊娆�","product_id":"10659"},{"cover_price":"138.00","figure":"/supplier/1478873369497.jpg","name":"銆愬皻纭呰胺銆戞棩甯� 钀岀郴灏忔伓榄斿崼琛�--榛戣壊娆�","product_id":"10658"},{"cover_price":"32.00","figure":"/supplier/1478867468462.jpg","name":"棰勫敭銆愭极鍙嬫枃鍖栥\u20ac戝叏鑱岄珮鎵�6 澶╅椈瑙掑窛  娴佸湴寰界珷 鍏ㄦ柊鍏歌棌鐗� 铦磋澏钃� 鐚爲缁� 璧犵簿缇庡ぇ娴锋姤+棣栧埛闄愬畾璧�2017骞存椿椤靛彴鍘�","product_id":"10657"},{"cover_price":"18.00","figure":"/1478860081305.jpg","name":"銆愬垢杩愭槦銆戠儷閲戦洩绾篔SK鐨勯厤浠跺皬鐗╋細鎵� 濂椼\u20ac侀」閾�","product_id":"10656"},{"cover_price":"178.00","figure":"/1478850234799.jpg","name":"銆愬皻纭呰胺銆戝鐙愬浘鑵� 闃撮槼甯堝悓浜哄厓绱犲崼琛�","product_id":"10655"},{"cover_price":"138.00","figure":"/1478849792177.jpg","name":"銆愬皻纭呰胺銆戝闄㈤ 鏃ュ父鐧炬惌 瀹芥澗闀胯琛～","product_id":"10654"}]
         * seckill_info : {"end_time":"1479052800","list":[{"cover_price":"20.00","figure":"/1478489000522.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�600-120浼樻儬鍒�","origin_price":"20.00","product_id":"7100"},{"cover_price":"10.00","figure":"/1478489035167.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�300-80浼樻儬鍒�","origin_price":"10.00","product_id":"7101"},{"cover_price":"5.00","figure":"/1478489878735.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�160-40浼樻儬鍒�","origin_price":"5.00","product_id":"7102"},{"cover_price":"49.00","figure":"/1475045805488.jpg","name":"銆愬彜椋庡師鍒涖\u20ac� 鑷姩鐩存焺浼� 鏅撮洦浼� 銆愪簯楣ゆ父銆戝寘閭�  鏂板鎶樺彔浼�","origin_price":"69.00","product_id":"9593"},{"cover_price":"5.00","figure":"/1478678511949.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�60-20浼樻儬鍒�","origin_price":"5.00","product_id":"10536"},{"cover_price":"49.00","figure":"/1438680345318.jpg","name":"銆愬彜椋庡師鍒涖\u20ac� 鑷姩鐩存焺浼� 鏅撮洦浼� 銆愰潚绔硅瘝銆戝寘閭�  鏂板鎶樺彔浼�","origin_price":"59.00","product_id":"555"}],"start_time":"1478772000"}
         */

        private SeckillInfoBean seckill_info;
        private List<ActInfoBean> act_info;
        private List<BannerInfoBean> banner_info;
        private List<ChannelInfoBean> channel_info;
        private List<HotInfoBean> hot_info;
        private List<RecommendInfoBean> recommend_info;

        public SeckillInfoBean getSeckill_info() {
            return seckill_info;
        }

        public void setSeckill_info(SeckillInfoBean seckill_info) {
            this.seckill_info = seckill_info;
        }

        public List<ActInfoBean> getAct_info() {
            return act_info;
        }

        public void setAct_info(List<ActInfoBean> act_info) {
            this.act_info = act_info;
        }

        public List<BannerInfoBean> getBanner_info() {
            return banner_info;
        }

        public void setBanner_info(List<BannerInfoBean> banner_info) {
            this.banner_info = banner_info;
        }

        public List<ChannelInfoBean> getChannel_info() {
            return channel_info;
        }

        public void setChannel_info(List<ChannelInfoBean> channel_info) {
            this.channel_info = channel_info;
        }

        public List<HotInfoBean> getHot_info() {
            return hot_info;
        }

        public void setHot_info(List<HotInfoBean> hot_info) {
            this.hot_info = hot_info;
        }

        public List<RecommendInfoBean> getRecommend_info() {
            return recommend_info;
        }

        public void setRecommend_info(List<RecommendInfoBean> recommend_info) {
            this.recommend_info = recommend_info;
        }

        public static class SeckillInfoBean {
            /**
             * end_time : 1479052800
             * list : [{"cover_price":"20.00","figure":"/1478489000522.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�600-120浼樻儬鍒�","origin_price":"20.00","product_id":"7100"},{"cover_price":"10.00","figure":"/1478489035167.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�300-80浼樻儬鍒�","origin_price":"10.00","product_id":"7101"},{"cover_price":"5.00","figure":"/1478489878735.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�160-40浼樻儬鍒�","origin_price":"5.00","product_id":"7102"},{"cover_price":"49.00","figure":"/1475045805488.jpg","name":"銆愬彜椋庡師鍒涖\u20ac� 鑷姩鐩存焺浼� 鏅撮洦浼� 銆愪簯楣ゆ父銆戝寘閭�  鏂板鎶樺彔浼�","origin_price":"69.00","product_id":"9593"},{"cover_price":"5.00","figure":"/1478678511949.png","name":"灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�60-20浼樻儬鍒�","origin_price":"5.00","product_id":"10536"},{"cover_price":"49.00","figure":"/1438680345318.jpg","name":"銆愬彜椋庡師鍒涖\u20ac� 鑷姩鐩存焺浼� 鏅撮洦浼� 銆愰潚绔硅瘝銆戝寘閭�  鏂板鎶樺彔浼�","origin_price":"59.00","product_id":"555"}]
             * start_time : 1478772000
             */

            private String end_time;
            private String start_time;
            private List<ListBean> list;

            public String getEnd_time() {
                return end_time;
            }

            public void setEnd_time(String end_time) {
                this.end_time = end_time;
            }

            public String getStart_time() {
                return start_time;
            }

            public void setStart_time(String start_time) {
                this.start_time = start_time;
            }

            public List<ListBean> getList() {
                return list;
            }

            public void setList(List<ListBean> list) {
                this.list = list;
            }

            public static class ListBean {
                /**
                 * cover_price : 20.00
                 * figure : /1478489000522.png
                 * name : 灏氱璋疯喘鐗╄妭鐗逛緵浼樻儬鍒�  婊�600-120浼樻儬鍒�
                 * origin_price : 20.00
                 * product_id : 7100
                 */

                private String cover_price;
                private String figure;
                private String name;
                private String origin_price;
                private String product_id;

                public String getCover_price() {
                    return cover_price;
                }

                public void setCover_price(String cover_price) {
                    this.cover_price = cover_price;
                }

                public String getFigure() {
                    return figure;
                }

                public void setFigure(String figure) {
                    this.figure = figure;
                }

                public String getName() {
                    return name;
                }

                public void setName(String name) {
                    this.name = name;
                }

                public String getOrigin_price() {
                    return origin_price;
                }

                public void setOrigin_price(String origin_price) {
                    this.origin_price = origin_price;
                }

                public String getProduct_id() {
                    return product_id;
                }

                public void setProduct_id(String product_id) {
                    this.product_id = product_id;
                }
            }
        }

        public static class ActInfoBean {
            /**
             * icon_url : /operation/img/1478169868/1478761370286.png
             * name : 灏氱璋风鍒╀笓鍖轰箣111.1涓撳尯
             * url : /oper/1478169868app.html
             */

            private String icon_url;
            private String name;
            private String url;

            public String getIcon_url() {
                return icon_url;
            }

            public void setIcon_url(String icon_url) {
                this.icon_url = icon_url;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }

        public static class BannerInfoBean {
            /**
             * image : /1478770583834.png
             * option : 3
             * type : 0
             * value : {"url":"/act20161111?cyc_app=1"}
             */

            private String image;
            private int option;
            private int type;
            private ValueBean value;

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getOption() {
                return option;
            }

            public void setOption(int option) {
                this.option = option;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public ValueBean getValue() {
                return value;
            }

            public void setValue(ValueBean value) {
                this.value = value;
            }

            public static class ValueBean {
                /**
                 * url : /act20161111?cyc_app=1
                 */

                private String url;

                public String getUrl() {
                    return url;
                }

                public void setUrl(String url) {
                    this.url = url;
                }
            }
        }

        public static class ChannelInfoBean {
            /**
             * channel_name : 鏈嶉グ
             * image : /app/img/menu-cyc.png
             * option : 2
             * type : 1
             * value : {"channel_id":"8"}
             */

            private String channel_name;
            private String image;
            private int option;
            private int type;
            private ValueBeanX value;

            public String getChannel_name() {
                return channel_name;
            }

            public void setChannel_name(String channel_name) {
                this.channel_name = channel_name;
            }

            public String getImage() {
                return image;
            }

            public void setImage(String image) {
                this.image = image;
            }

            public int getOption() {
                return option;
            }

            public void setOption(int option) {
                this.option = option;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }

            public ValueBeanX getValue() {
                return value;
            }

            public void setValue(ValueBeanX value) {
                this.value = value;
            }

            public static class ValueBeanX {
                /**
                 * channel_id : 8
                 */

                private String channel_id;

                public String getChannel_id() {
                    return channel_id;
                }

                public void setChannel_id(String channel_id) {
                    this.channel_id = channel_id;
                }
            }
        }

        public static class HotInfoBean {
            /**
             * cover_price : 159.00
             * figure : /1477984921265.jpg
             * name : 鐜拌揣銆愪竴鏂瑰皹瀵般€戝墤渚犳儏缂樹笁鍓戜笁涓冪 骞插皢鑾偑 90姗欐浠跨儳钃濆鍙ゅ绨�
             * product_id : 9356
             */

            private String cover_price;
            private String figure;
            private String name;
            private String product_id;

            public String getCover_price() {
                return cover_price;
            }

            public void setCover_price(String cover_price) {
                this.cover_price = cover_price;
            }

            public String getFigure() {
                return figure;
            }

            public void setFigure(String figure) {
                this.figure = figure;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }
        }

        public static class RecommendInfoBean {
            /**
             * cover_price : 138.00
             * figure : /supplier/1478873740576.jpg
             * name : 銆愬皻纭呰胺銆戞棩甯� 钀岀郴灏忓ぉ浣垮崼琛�--鐧借壊娆�
             * product_id : 10659
             */

            private String cover_price;
            private String figure;
            private String name;
            private String product_id;

            public String getCover_price() {
                return cover_price;
            }

            public void setCover_price(String cover_price) {
                this.cover_price = cover_price;
            }

            public String getFigure() {
                return figure;
            }

            public void setFigure(String figure) {
                this.figure = figure;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getProduct_id() {
                return product_id;
            }

            public void setProduct_id(String product_id) {
                this.product_id = product_id;
            }
        }
    }
}
