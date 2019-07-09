package demo.isoft.com.airplan.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import demo.isoft.com.airplan.AdInfoActivity;
import demo.isoft.com.airplan.R;
import demo.isoft.com.airplan.myview.MygrideView;
import demo.isoft.com.airplan.pojo.FragmentIndexGridviewItem;
import demo.isoft.com.airplan.pojo.Tipt;
import demo.isoft.com.airplan.services.MyWeatherService;

/**
 * A simple {@link Fragment} subclass.
 */
public class IndexFragment extends Fragment {
    private Button buttonlogin;
    private List<FragmentIndexGridviewItem> list;
    private MygrideView gridView;
    private MyAdapter adapter;
    private Intent intent;
    private MyWeatherReceiver receiver;
    private TextView textView;
    //
    private List<FragmentIndexGridviewItem> listfunction;
    private MygrideView gridViewfunction;
    //private FunctionAdapter adapterfunction;

    public IndexFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view = inflater.inflate(R.layout.fragment_index, container, false);

//        首页的了功能导航栏
        gridViewfunction = (MygrideView) view.findViewById(R.id.fragment_index_gridview_function);

       /* setFunctionData();
        adapterfunction = new FunctionAdapter();
        gridViewfunction.setAdapter(adapterfunction);*/

//**********************************************************
        gridView = (MygrideView) view.findViewById(R.id.fragment_index_gridview);
        textView= (TextView) view.findViewById(R.id.fragment_index_textview_weather);
        intent=new Intent(getContext(), MyWeatherService.class);
        //注册广播接收器
        receiver=new MyWeatherReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.iss.tiptweather");
        getContext().registerReceiver(receiver,filter);

        getContext().startService(intent);

        buttonlogin = (Button) view.findViewById(R.id.fragment_index_login);
        buttonlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentlogin = new Intent(getContext(), AdInfoActivity.class);
                startActivity(intentlogin);
            }
        });
        setData();
        adapter = new MyAdapter();
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getContext(),list.get(position).getTitle(), Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getContext(), AdInfoActivity.class);
                intent.putExtra("title", list.get(position).getTitle());
                startActivity(intent);
            }
        });
        return view;
    }
//function部分
   /* private void setFunctionData() {
        listfunction = new ArrayList<>();
        listfunction.add(new FragmentIndexGridviewItem(R.mipmap.ad_image1, "免费乘机泊车", "深航旅客专享"));
    }

    private class FunctionAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return 0;
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
            View view = LayoutInflater.from(getContext()).inflate(R.layout.fragment_index_gridview_function, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.fregment_index_gridview_function_imageview);
            TextView textView1 = (TextView) view.findViewById(R.id.fregment_index_gridview_function_textview1);
            imageView.setImageResource(listfunction.get(position).getImageId());
            textView1.setText(listfunction.get(position).getTitle());
            return view;
        }
    }*/
    //function部分**************************************************

    public void setData() {
        list = new ArrayList<>();
        list.add(new FragmentIndexGridviewItem(R.mipmap.ad_image1, "免费乘机泊车", "深航旅客专享"));
        list.add(new FragmentIndexGridviewItem(R.mipmap.ad_image2, "免费升舱及积分礼包", "青岛航空新会员"));
        list.add(new FragmentIndexGridviewItem(R.mipmap.ad_image1, "免费乘机泊车2", "深航旅客专享2"));
        list.add(new FragmentIndexGridviewItem(R.mipmap.ad_image1, "免费乘机泊车3", "深航旅客专享3"));
    }


    private class MyAdapter extends BaseAdapter {
        @Override
        public int getCount() {
            return list.size();
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
            View view = LayoutInflater.from(getContext()).inflate(R.layout.fregment_index_gridview_item, null);
            ImageView imageView = (ImageView) view.findViewById(R.id.fregment_index_gridview_item_imageview);
            TextView textView1 = (TextView) view.findViewById(R.id.fregment_index_gridview_item_textview1);
            TextView textView2 = (TextView) view.findViewById(R.id.fregment_index_gridview_item_textview2);
            imageView.setImageResource(list.get(position).getImageId());
            textView1.setText(list.get(position).getTitle());
            textView2.setText(list.get(position).getSubtitle());
            return view;
        }
    }
    //广播接收器
    public class MyWeatherReceiver extends BroadcastReceiver{

        @Override
        public void onReceive(Context context, Intent intent) {
            String action=intent.getAction();
            if("com.iss.tiptweather".equals(action)){
                String message=intent.getStringExtra("message");
                String pm25=intent.getStringExtra("pm25");
                int error=intent.getIntExtra("error",-1);
                ArrayList<Tipt> tipts=intent.getParcelableArrayListExtra("tipts");
                if(error==0){
                    String str="pm2.5="+pm25+"\n";
                    for (int i=0;i<tipts.size();i++)
                    {
                        str+=tipts.get(i).getTitle()+":"+tipts.get(i).getZs()+"\n";
                    }
                    textView.setText(str);
                }else {
                    Toast.makeText(getContext(),message,Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

}
