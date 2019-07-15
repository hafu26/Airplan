package demo.isoft.com.airplan;

import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import demo.isoft.com.airplan.pojo.PlaneStatus;
import demo.isoft.com.airplan.utils.TimeFormatUtil;

public class StatusListActivity extends AppCompatActivity {
    private String from, to;
    private TextView textView_title;
    private LinearLayout line_point;
    private TextView textView_time;
    private List<PlaneStatus> list;
    private MyStatusListAdapter adapter;
    private ViewPager viewPager;
    private MyViewPagerAdapter pagerAdapter;

    private int rowOnePager = 10;
    private List<View> listViews;
    private List<ImageView> listImageViews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_list);
        from = getIntent().getStringExtra("from");
        to = getIntent().getStringExtra("to");
        textView_title = (TextView) findViewById(R.id.statuslist_textview_title);
        textView_title.setText(from + "---->"+ to);
        line_point = (LinearLayout) findViewById(R.id.statuslist_line_roundpoint);
        textView_time = (TextView) findViewById(R.id.statuslist_textview_time);
        textView_time.setText(TimeFormatUtil.timeformat(new Date()));
        viewPager = (ViewPager) findViewById(R.id.statuslist_viewpager);
        getData();


        listImageViews.get(0).setImageResource(R.mipmap.point_green);
        pagerAdapter = new MyViewPagerAdapter();
        viewPager.setAdapter(pagerAdapter);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < listImageViews.size(); i++) {
                    if (i == position) {
                        listImageViews.get(i).setImageResource(R.mipmap.point_green);
                    } else {
                        listImageViews.get(i).setImageResource(R.mipmap.point_gray);
                    }
                }

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }


    public void getData() {
        listViews = new ArrayList<>();
        listImageViews = new ArrayList<>();
        list = new ArrayList<>();
        PlaneStatus status;
        for (int i = 0; i < 50; i++) {
            status = new PlaneStatus();
            status.setCode("A" + (i + 1));
            status.setStartTime("10:20");
            status.setEndTime("12:30");
            status.setStatus("起飞");
            list.add(status);
            if (i % rowOnePager == 0) {
                listViews.add(LayoutInflater.from(StatusListActivity.this).inflate(R.layout.status_list_viewpager_item, null));
                ImageView imageView = new ImageView(StatusListActivity.this);
                imageView.setLayoutParams(new LinearLayout.LayoutParams(30, 30));
                imageView.setPadding(5, 5, 5, 5);
                imageView.setScaleType(ImageView.ScaleType.FIT_XY);
                imageView.setImageResource(R.mipmap.point_gray);
                listImageViews.add(imageView);
                line_point.addView(imageView);
            }
        }
    }

    public class MyStatusListAdapter extends BaseAdapter {
        private int offset;

        public MyStatusListAdapter(int offset) {
            this.offset = offset;
        }

        @Override
        public int getCount() {
            return rowOnePager;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            View view=LayoutInflater.from(StatusListActivity.this).inflate(R.layout.statuslist_listview_item,null);
            TextView textView1= (TextView) view.findViewById(R.id.status_list_listview_item_textview1);
            TextView textView2= (TextView) view.findViewById(R.id.status_list_listview_item_textview2);
            TextView textView3= (TextView) view.findViewById(R.id.status_list_listview_item_textview3);
            TextView textView4= (TextView) view.findViewById(R.id.status_list_listview_item_textview4);
            textView1.setText(list.get(offset+position).getCode());
            textView2.setText(list.get(offset+position).getStartTime());
            textView3.setText(list.get(offset+position).getEndTime());
            textView4.setText(list.get(offset+position).getStatus());
            return view;
        }
    }

    public class MyViewPagerAdapter extends PagerAdapter {

        @Override
        public int getCount() {
            return listViews.size();
        }

        @Override
        public boolean isViewFromObject(View view, Object object) {
            return view==object;
        }



        //以下方法重写

        //返回当前页的布局

        @Override
        public Object instantiateItem(ViewGroup container, final int position) {
            ListView listView= (ListView) listViews.get(position).findViewById(R.id.status_list_viewpager_item_listview);
            adapter=new MyStatusListAdapter(position*rowOnePager);
            listView.setAdapter(adapter);
            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int p, long id) {
                    Toast.makeText(StatusListActivity.this,"code="+list.get(position*rowOnePager+p).getCode(),Toast.LENGTH_SHORT).show();
                }
            });
            container.addView(listViews.get(position));
            return listViews.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
           container.removeView(listViews.get(position));
        }
    }
}
