package demo.isoft.com.airplan;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import demo.isoft.com.airplan.pojo.Airport;
import demo.isoft.com.airplan.services.AirportListService;

public class AirportSearchActivity extends AppCompatActivity {

    private EditText editText;
    private ListView listView;
    private MyAirPortSearchReceiver receiver;
    private ArrayList<Airport> list;
    private MyAirPortListAdapter adapter;
    private String airPortType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_airport_search);
        editText = (EditText) findViewById(R.id.airport_search_edittext);
        listView = (ListView) findViewById(R.id.airport_search_listview);
        list=new ArrayList<>();

        adapter=new MyAirPortListAdapter();
        listView.setAdapter(adapter);

        airPortType=getIntent().getStringExtra("airPotrType");

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String airportName=list.get(position).getFullNameCn();
                Intent intent=new Intent("com.iss.airportselected");
                intent.putExtra("airportName", airportName);
                intent.putExtra("airpotrType", airPortType);;
                sendBroadcast(intent);

                Intent intent1=new Intent(AirportSearchActivity.this,MainActivity.class);
                startActivity(intent1);
                finish();
            }
        });
        receiver=new MyAirPortSearchReceiver();
        IntentFilter filter=new IntentFilter();
        filter.addAction("com.iss.airportlist");
        registerReceiver(receiver,filter);

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                //文字变化前
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //文字变化时
            }

            @Override
            public void afterTextChanged(Editable s) {
                //文字变化后
                Intent intent = new Intent(AirportSearchActivity.this, AirportListService.class);
                intent.putExtra("airPortName", s.toString());
                startService(intent);
            }
        });
    }

    public class MyAirPortSearchReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if("com.iss.airportlist".equals(intent.getAction())){
                list=intent.getParcelableArrayListExtra("list");
                adapter.notifyDataSetChanged();
            }
        }
    }
    public class MyAirPortListAdapter extends BaseAdapter{

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
            View view= LayoutInflater.from(AirportSearchActivity.this).inflate(R.layout.airportlist_listview_item,null);
            TextView textview= (TextView) view.findViewById(R.id.airportlist_listview_item_textview);
            textview.setText(list.get(position).getFullNameCn()+"("+list.get(position).getThreeWordsCode()+"("+list.get(position).getFourWordsCode()+")");
            return view;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
