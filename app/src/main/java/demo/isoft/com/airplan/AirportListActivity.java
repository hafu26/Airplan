package demo.isoft.com.airplan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.isoft.com.airplan.pojo.Airport;
import demo.isoft.com.airplan.services.AirportListService;

public class AirportListActivity extends AppCompatActivity {
    private String airPotrType;
    private ImageView imageView_search;
    private TextView textView1, textView2;
    private LinearLayout line1, line2;
    private ListView listView;
    private MyAirportListReceiver receiver;
    private MyairportListAdapter adapter;
    private ArrayList<Airport> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_list);
        airPotrType = getIntent().getStringExtra("type");
        imageView_search = (ImageView) findViewById(R.id.airportlist_imageview_search);
        textView1 = (TextView) findViewById(R.id.airportlist_textview1);
        textView2 = (TextView) findViewById(R.id.airportlist_textview2);
        line1 = (LinearLayout) findViewById(R.id.airportlist_line1);
        line2 = (LinearLayout) findViewById(R.id.airportlist_line2);
        listView = (ListView) findViewById(R.id.airportlist_listview);


        list = new ArrayList<>();
        adapter = new MyairportListAdapter();
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String airportName = list.get(position).getFullNameCn();
                Intent intent = new Intent("com.iss.airportselected");
                intent.putExtra("airportName", airportName);
                intent.putExtra("airpotrType", airPotrType);
                sendBroadcast(intent);
                finish();
            }
        });

        //注册广播接收器
        receiver = new MyAirportListReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.iss.airportlist");

        registerReceiver(receiver, filter);

        startAirPortListService("domestic");

        textView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView1.setTextColor(getResources().getColor(R.color.footer_text_checked));
                textView2.setTextColor(getResources().getColor(R.color.darkgray));
                line1.setVisibility(View.VISIBLE);
                line2.setVisibility(View.GONE);
                startAirPortListService("domestic");
            }
        });
        textView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView2.setTextColor(getResources().getColor(R.color.footer_text_checked));
                textView1.setTextColor(getResources().getColor(R.color.darkgray));
                line1.setVisibility(View.GONE);
                line2.setVisibility(View.VISIBLE);
                startAirPortListService("international");
            }
        });
        imageView_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AirportListActivity.this, AirportSearchActivity.class);
                intent.putExtra("airPotrType", airPotrType);
                startActivity(intent);
            }
        });
    }

    private void startAirPortListService(String property) {
        Intent intent = new Intent(AirportListActivity.this, AirportListService.class);
        intent.putExtra("property", property);
        intent.putExtra("airPortType", airPotrType);
        startService(intent);
    }

    public class MyAirportListReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.iss.airportlist".equals(intent.getAction())) {
                list = intent.getParcelableArrayListExtra("list");
                //通过适配器刷新ListView
                adapter.notifyDataSetChanged();
            } else if ("com.iss.airportselected".equals(intent.getAction())) {

            }
        }
    }

    public class MyairportListAdapter extends BaseAdapter {

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
            View view = LayoutInflater.from(AirportListActivity.this).inflate(R.layout.airportlist_listview_item, null);
            TextView textview = (TextView) view.findViewById(R.id.airportlist_listview_item_textview);
            textview.setText(list.get(position).getFullNameCn() + "(" + list.get(position).getThreeWordsCode() + "(" + list.get(position).getFourWordsCode() + ")");
            return view;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}
