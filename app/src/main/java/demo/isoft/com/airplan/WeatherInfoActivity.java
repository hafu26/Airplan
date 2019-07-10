package demo.isoft.com.airplan;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.isoft.com.airplan.pojo.Tipt;
import demo.isoft.com.airplan.pojo.WeatherData;

public class WeatherInfoActivity extends AppCompatActivity {

    private ArrayList<WeatherData> weatherDatas;
    private ArrayList<Tipt> tipts;
    private ListView listView1, listView2;
    private MyAdapter2 myAdapter2;
    private MyAdapter1 myAdapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather_info);
        listView1 = (ListView) findViewById(R.id.weather_info_listview1);
        listView2 = (ListView) findViewById(R.id.weather_info_listview2);
        Intent intent = getIntent();
        weatherDatas = intent.getParcelableArrayListExtra("weather_data");
        String date = weatherDatas.get(0).getDate();
        date = date.substring(0, 2);
        weatherDatas.get(0).setDate(date);
        tipts = intent.getParcelableArrayListExtra("tipts");
        myAdapter2 = new MyAdapter2();
        myAdapter1=new MyAdapter1();
        listView2.setAdapter(myAdapter2);
        listView1.setAdapter(myAdapter1);

    }

    //当Activity启动，Activity可见
    @Override
    protected void onStart() {
        super.onStart();
    }

    //当Activity可编辑
    @Override
    protected void onResume() {
        super.onResume();
    }

    //当Activity重新启动
    @Override
    protected void onRestart() {
        super.onRestart();
    }

    //当Activity不可编辑
    @Override
    protected void onPause() {
        super.onPause();
    }

    //当Activity停止，不可见
    @Override
    protected void onStop() {
        super.onStop();
    }

    //销毁Activity时调用
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


    //    穿衣指数
    public class MyAdapter1 extends BaseAdapter {

        @Override
        public int getCount() {
            return tipts.size();
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
            View view = LayoutInflater.from(WeatherInfoActivity.this).inflate(R.layout.weather_info_listview1_weather_item, null);
            TextView textView_des= (TextView) view.findViewById(R.id.weather_info_listview1_item_line1_textview_des);
            TextView textView_tipt= (TextView) view.findViewById(R.id.weather_info_listview1_item_line1_textview_tipt);
            TextView textView_title= (TextView) view.findViewById(R.id.weather_info_listview1_item_line1_textview_title);
            TextView textView_zs= (TextView) view.findViewById(R.id.weather_info_listview1_item_line1_textview_zs);
            textView_des.setText(tipts.get(position).getDes());
            textView_tipt.setText(tipts.get(position).getTipt());
            textView_title.setText(tipts.get(position).getTitle());
            textView_zs.setText(tipts.get(position).getZs());
            return view;
        }
    }


    public class MyAdapter2 extends BaseAdapter {

        @Override
        public int getCount() {
            return weatherDatas.size();
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
            View view = LayoutInflater.from(WeatherInfoActivity.this).inflate(R.layout.weather_info_listview2_weather_item, null);
            TextView textView_date = (TextView) view.findViewById(R.id.weather_info_listview2_item_line1_date);
            TextView textView_weather = (TextView) view.findViewById(R.id.weather_info_listview2_item_line1_weather);
            ImageView imageView_day = (ImageView) view.findViewById(R.id.weather_info_listview2_item_line2_imagae1);
            ImageView imageView_night = (ImageView) view.findViewById(R.id.weather_info_listview2_item_line2_imagae2);
            TextView textView_wind = (TextView) view.findViewById(R.id.weather_info_listview2_item_line2_textview1);
            TextView textView_temp = (TextView) view.findViewById(R.id.weather_info_listview2_item_line2_textview2);
            textView_date.setText(weatherDatas.get(position).getDate());
            textView_weather.setText(weatherDatas.get(position).getWeather());
            imageView_day.setImageURI(Uri.parse(weatherDatas.get(position).getDayPicPath()));
            imageView_night.setImageURI(Uri.parse(weatherDatas.get(position).getNightPicath()));
            textView_wind.setText(weatherDatas.get(position).getWind());
            textView_temp.setText(weatherDatas.get(position).getTemperature());
            return view;
        }
    }
}
