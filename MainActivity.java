package bawei.com.day421_homework_vp_xutils_db_demo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.alibaba.fastjson.JSON;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.message.BasicNameValuePair;
import org.xutils.DbManager;

import org.xutils.x;

import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import bawei.com.mylibrary.XListView;

public class MainActivity extends Activity implements XListView.IXListViewListener{

    private XListView xlv;

    int page = 1;

    int code;

    List<News.ListBean> list = new ArrayList<News.ListBean>();

    MyHandler handler = new MyHandler(this);
    private MyAdapter adapter;
    private MyApplication application;
    private ViewPager vp;
    private LinearLayout ll;
    private DbManager manager;

    class MyHandler extends Handler {

        WeakReference<MainActivity> weakReference;


        public MyHandler(MainActivity activity) {

            weakReference = new WeakReference<MainActivity>(activity);

        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);

            MainActivity mainActivity = weakReference.get();

            if (mainActivity == null) {
                return;
            }

            switch (msg.what) {

                case 1:

                    String json = (String) msg.obj;//接收返回的json串

                    News news = JSON.parseObject(json, News.class);

                    List<String> pagerList = news.getListViewPager();//ViewPager中的图片地址

                    initData(pagerList);

                    list.addAll(news.getList());

                    try {
                        for (News.ListBean listBean : list) {

                            Bean bean = new Bean();
//                            bean.setId(listBean.getId());
                            bean.setDate(listBean.getDate());
                            bean.setPic(listBean.getPic());
                            bean.setTitle(listBean.getTitle());
                            bean.setType(listBean.getType());

                            manager.save(bean);

                        }
                    } catch (Exception e) {
                        e.printStackTrace();
                    }


                    adapter.notifyDataSetChanged();


                    break;

                case 2:

                    int currentItem = vp.getCurrentItem();

                    vp.setCurrentItem(currentItem + 1);

                    handler.sendEmptyMessageDelayed(2, 2000);

                    break;

            }

        }
    }




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        xlv = (XListView) findViewById(R.id.xlv);

        LayoutInflater lif = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        View view = lif.inflate(R.layout.viewpage, xlv, false);

        vp = (ViewPager) view.findViewById(R.id.vp);

        ll = (LinearLayout) view.findViewById(R.id.ll1);

        xlv.addHeaderView(view);

        application = (MyApplication) getApplication();

        manager = x.getDb(application.config);

        adapter = new MyAdapter();


        execute(true);

        xlv.setPullLoadEnable(true);
        xlv.setPullRefreshEnable(true);
        xlv.setXListViewListener(this);

        for (int i = 0; i <3; i++) {

            ImageView iv = new ImageView(this);

            if (i == 0) {
                iv.setImageResource(R.drawable.shape01);
            } else {
                iv.setImageResource(R.drawable.shape02);
            }

            LinearLayout.LayoutParams params = new
                    LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT);

            params.setMargins(5, 0, 5, 0);

            ll.addView(iv, params);

            imageViews.add(iv);

        }


    }

    List<ImageView> imageViews = new ArrayList<ImageView>();

    public void initData(final List<String> pagerList) {

//        code = pagerList.size();

        vp.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {

                for (int i = 0; i < imageViews.size(); i++) {
                    if (i == position % pagerList.size()) {
                        imageViews.get(i).setImageResource(R.drawable.shape01);
                    } else {
                        imageViews.get(i).setImageResource(R.drawable.shape02);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        PagerAdapter adapter1 = new PagerAdapter() {
            @Override
            public int getCount() {
                return Integer.MAX_VALUE;
            }

            @Override
            public boolean isViewFromObject(View view, Object object) {
                return view == object;
            }

            @Override
            public void destroyItem(ViewGroup container, int position, Object object) {
//

                container.removeView((View) object);
            }

            @Override
            public Object instantiateItem(ViewGroup container, int position) {

                String url = pagerList.get(position % pagerList.size());

                View view = View.inflate(MainActivity.this, R.layout.vp_item, null);

                ImageView iv = (ImageView) view.findViewById(R.id.iv_vp);

                iv.setOnTouchListener(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {

                        switch (event.getAction()) {

                            case MotionEvent.ACTION_DOWN://按下

                                handler.removeCallbacksAndMessages(null);

                                break;

                            case MotionEvent.ACTION_UP://抬起

                                handler.sendEmptyMessageDelayed(2, 2000);

                                break;

                            case MotionEvent.ACTION_CANCEL://取消

                                handler.sendEmptyMessageDelayed(2, 2000);

                                break;

                            case MotionEvent.ACTION_MOVE://移动

                                handler.removeCallbacksAndMessages(null);

                                break;


                        }

                        return true;
                    }
                });

                ImageLoader.getInstance().displayImage(url, iv);

                container.addView(view);

                return view;
            }
        };

        vp.setAdapter(adapter1);

        vp.setCurrentItem(pagerList.size() * 10000);

        handler.sendEmptyMessageDelayed(2, 2000);


    }


    public void execute(boolean flag) {

		/**
		httpurlcontion
		Map map = new HashMap();
        map.put("page",page);
        map.put("postkey","1503d");

        new IAsyncTask(handler).execute(map);
		*/

        MyAsyncTask task = new MyAsyncTask(handler);

        List<BasicNameValuePair> data = new ArrayList<BasicNameValuePair>();

        data.add(new BasicNameValuePair("type", "2"));
        data.add(new BasicNameValuePair("postkey", "1503d"));
        data.add(new BasicNameValuePair("page", page + ""));

        task.execute("http://qhb.2dyt.com/Bwei/news", data);

        setData(flag);


    }

    public void setData(boolean flag) {

        if (flag) {

            xlv.setAdapter(adapter);

        } else {

            adapter.notifyDataSetChanged();

        }

    }

    //下拉刷新
    @Override
    public void onRefresh() {


        list.clear();

        adapter.notifyDataSetChanged();

        execute(true);

        handler.removeCallbacksAndMessages(null);

        xlv.stopRefresh();

        xlv.setRefreshTime("刚刚");

    }

    //上拉加载更多
    @Override
    public void onLoadMore() {

        page++;

        execute(false);

        handler.removeCallbacksAndMessages(null);

        xlv.stopLoadMore();

    }

    class MyAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list == null ? 0 : list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public int getViewTypeCount() {
            return 2;
        }

        @Override
        public int getItemViewType(int position) {

            News.ListBean listBean = list.get(position);

            if (listBean.getType() == 1) {
                return 0;
            } else {
                return 1;
            }

        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {


            ViewHolderOne holderOne = null;
            ViewHolderTwo holderTwo = null;


            if (convertView == null) {

                if (getItemViewType(position) == 0) {

                    holderOne = new ViewHolderOne();

                    convertView = View.inflate(MainActivity.this, R.layout.lv_item_one, null);

                    holderOne.one_title = (TextView) convertView.findViewById(R.id.one_title);
                    holderOne.one_date = (TextView) convertView.findViewById(R.id.one_date);
                    holderOne.one_iv = (ImageView) convertView.findViewById(R.id.one_iv);

                    convertView.setTag(holderOne);

                } else {

                    holderTwo = new ViewHolderTwo();

                    convertView = View.inflate(MainActivity.this, R.layout.lv_item_two, null);

                    holderTwo.two_title = (TextView) convertView.findViewById(R.id.two_title);
                    holderTwo.two_date = (TextView) convertView.findViewById(R.id.two_date);
                    holderTwo.two_iv1 = (ImageView) convertView.findViewById(R.id.two_iv1);
                    holderTwo.two_iv2 = (ImageView) convertView.findViewById(R.id.two_iv2);
                    holderTwo.two_iv3 = (ImageView) convertView.findViewById(R.id.two_iv3);

                    convertView.setTag(holderTwo);
                }

            } else {
                if (getItemViewType(position) == 0) {
                    holderOne = (ViewHolderOne) convertView.getTag();
                } else {
                    holderTwo = (ViewHolderTwo) convertView.getTag();
                }
            }


            if (getItemViewType(position) == 0) {
                News.ListBean bean = list.get(position);

                holderOne.one_title.setText(bean.getTitle());
                holderOne.one_date.setText(bean.getDate());
                ImageLoader.getInstance().displayImage(bean.getPic(), holderOne.one_iv);
            } else {

                News.ListBean bean = list.get(position);

                String[] picArr = bean.getPic().split("\\|");

                for (String pic :
                        picArr) {
                    System.out.println(" --->  " + pic);
                }

                holderTwo.two_title.setText(bean.getTitle());
                holderTwo.two_date.setText(bean.getDate());

                ImageLoader.getInstance().displayImage(picArr[0], holderTwo.two_iv1);
                ImageLoader.getInstance().displayImage(picArr[1], holderTwo.two_iv2);
                ImageLoader.getInstance().displayImage(picArr[2], holderTwo.two_iv3);

            }


            return convertView;

        }
    }

    class ViewHolderOne {
        ImageView one_iv;
        TextView one_title, one_date;
    }

    class ViewHolderTwo {
        ImageView two_iv1, two_iv2, two_iv3;
        TextView two_title, two_date;
    }
}
