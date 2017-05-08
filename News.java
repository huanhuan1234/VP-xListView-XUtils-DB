package bawei.com.day421_homework_vp_xutils_db_demo;

import java.util.List;

/**
 * Created by huanhuan on 2017/4/23.
 */

public class News {



    /**
     * ret_code : 200
     * ret_msg : ok
     * listViewPager : ["https://img10.360buyimg.com/da/jfs/t4747/277/1368712300/170619/35098d7f/58f038e0N9b3a0ca5.jpg","https://img14.360buyimg.com/da/jfs/t4915/21/1427207714/81116/b005bb06/58f08963Ndb295b3c.jpg","https://img13.360buyimg.com/da/jfs/t4651/104/2867456043/68336/99da4c16/58f41eaeN5b614a63.jpg"]
     * list : [{"date":"5小时前","id":1,"pic":"http://img1.imgtn.bdimg.com/it/u=1794894692,1423685501&fm=23&gp=0.jpg","title":" \n军改后军级阵容首次曝光，六位将军特别亮相","type":1},{"date":"1小时前","id":3,"pic":"http://img02.tooopen.com/images/20140504/sy_60294738471.jpg","title":"《人民的名义》观后感：郑胜利居心不良，高育良地下情曝光","type":1},{"date":"5分钟前","id":13,"pic":"http://img5.imgtn.bdimg.com/it/u=2451261816,2335595583&fm=23&gp=0.jpg|http://img2.imgtn.bdimg.com/it/u=294748347,2057171981&fm=23&gp=0.jpg|http://img5.imgtn.bdimg.com/it/u=4077182999,3637750066&fm=23&gp=0.jpg","title":"【莱克】魔洁大吸力无线吸尘器，多功能一键切换","type":2},{"date":"10分钟前","id":16,"pic":"http://p3.pstatp.com/list/190x124/1cbc0003cf55a3003467|http://p3.pstatp.com/list/190x124/1cbc0003cf53e713d90e|http://p3.pstatp.com/list/190x124/1bc30008ed9744c2c34b","title":"90后女孩辞职做代孕赚20万 供两个妹妹上大学","type":2},{"date":"1分钟前","id":18,"pic":"http://p3.pstatp.com/list/190x124/e5900054f8a11fc5960","title":"深圳男子被吃脑虫入侵去世，哪些食物最危险含寄生虫?","type":1},{"date":"2小时前","id":19,"pic":"http://p9.pstatp.com/list/190x124/ef7000c2947b313fe05","title":" \n为什么越来越多的人会选择关闭朋友圈？","type":1}]
     */

    private int ret_code;
    private String ret_msg;
    private List<String> listViewPager;
    private List<ListBean> list;

    public int getRet_code() {
        return ret_code;
    }

    public void setRet_code(int ret_code) {
        this.ret_code = ret_code;
    }

    public String getRet_msg() {
        return ret_msg;
    }

    public void setRet_msg(String ret_msg) {
        this.ret_msg = ret_msg;
    }

    public List<String> getListViewPager() {
        return listViewPager;
    }

    public void setListViewPager(List<String> listViewPager) {
        this.listViewPager = listViewPager;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * date : 5小时前
         * id : 1
         * pic : http://img1.imgtn.bdimg.com/it/u=1794894692,1423685501&fm=23&gp=0.jpg
         * title :
         军改后军级阵容首次曝光，六位将军特别亮相
         * type : 1
         */

        private String date;
        private int id;
        private String pic;
        private String title;
        private int type;

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
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

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }
    }
}
