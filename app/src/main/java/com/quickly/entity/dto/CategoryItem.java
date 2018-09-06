package com.quickly.entity.dto;

import com.quickly.entity.BaseModel;

import java.io.Serializable;
import java.util.List;

public class CategoryItem extends BaseModel{


    /**
     * msg : 处理成功！
     * code : 0
     * data : {"hasNext":true,"list":[{"imgUrl":"http://www.luosen365.com/image/20180331/1522487902283.png","num":"0","id":"13dff46d1eb04ebda9261378f26d1b4d","title":"太阳能"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522463981447.jpg","num":"0","id":"1aa43313925144daa2cc6dd4915d7a19","title":"空调"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522488133345.jpg","num":"0","id":"2475118e03f94c5f8a404a61deaa86a1","title":"油烟机"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522490332058.jpg","num":"0","id":"26a661a2828f41cfb3291db41efbbb78","title":"小家电"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522490657585.jpg","num":"0","id":"376524aa862c4be7b29f2a9a745d4752","title":"电视"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522463817993.jpg","num":"0","id":"3876493493dc4329929cf81285bf3a2e","title":"冰箱"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522490876187.jpg","num":"0","id":"4189a2872a544ad8b3022d627db41d77","title":"厨房电器"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522463931190.gif","num":"0","id":"488dfe19fe92448aa24b279dc86d9572","title":"热水器"}]}
     */

    private String msg;
    private int code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * hasNext : true
         * list : [{"imgUrl":"http://www.luosen365.com/image/20180331/1522487902283.png","num":"0","id":"13dff46d1eb04ebda9261378f26d1b4d","title":"太阳能"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522463981447.jpg","num":"0","id":"1aa43313925144daa2cc6dd4915d7a19","title":"空调"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522488133345.jpg","num":"0","id":"2475118e03f94c5f8a404a61deaa86a1","title":"油烟机"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522490332058.jpg","num":"0","id":"26a661a2828f41cfb3291db41efbbb78","title":"小家电"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522490657585.jpg","num":"0","id":"376524aa862c4be7b29f2a9a745d4752","title":"电视"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522463817993.jpg","num":"0","id":"3876493493dc4329929cf81285bf3a2e","title":"冰箱"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522490876187.jpg","num":"0","id":"4189a2872a544ad8b3022d627db41d77","title":"厨房电器"},{"imgUrl":"http://www.luosen365.com/image/20180331/1522463931190.gif","num":"0","id":"488dfe19fe92448aa24b279dc86d9572","title":"热水器"}]
         */

        private boolean hasNext;
        private List<ListBean> list;

        public boolean isHasNext() {
            return hasNext;
        }

        public void setHasNext(boolean hasNext) {
            this.hasNext = hasNext;
        }

        public List<ListBean> getList() {
            return list;
        }

        public void setList(List<ListBean> list) {
            this.list = list;
        }

        public static class ListBean {
            /**
             * imgUrl : http://www.luosen365.com/image/20180331/1522487902283.png
             * num : 0
             * id : 13dff46d1eb04ebda9261378f26d1b4d
             * title : 太阳能
             */

            private String imgUrl;
            private String num;
            private String id;
            private String title;

            public String getImgUrl() {
                return imgUrl;
            }

            public void setImgUrl(String imgUrl) {
                this.imgUrl = imgUrl;
            }

            public String getNum() {
                return num;
            }

            public void setNum(String num) {
                this.num = num;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }
        }
    }
}
