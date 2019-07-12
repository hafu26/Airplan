package demo.isoft.com.airplan.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import demo.isoft.com.airplan.AirportListActivity;
import demo.isoft.com.airplan.CalendarActivity;
import demo.isoft.com.airplan.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {
    private TextView textView_choose1, textView_choose2;
    private LinearLayout choose_line1, choose_line2;
    private TextView textView_date;
    private MyStatusReceiver receiver;
    private  TextView textView_airport_from,textView_airport_to;
    private ImageView imageView;
    private String textView_airport_from_change,textView_airport_to__change;


    public StatusFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        textView_choose1 = (TextView) view.findViewById(R.id.fragment_status_choose_textview1);
        textView_choose2 = (TextView) view.findViewById(R.id.fragment_status_choose_textview2);
        textView_airport_from= (TextView) view.findViewById(R.id.fragment_status_airport_from);
        textView_airport_to= (TextView) view.findViewById(R.id.fragment_status_airport_to);



        imageView= (ImageView) view.findViewById(R.id.fragment_status_airport_fromto_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_airport_from_change=textView_airport_from.getText().toString();
                textView_airport_to__change=textView_airport_to.getText().toString();
                textView_airport_from.setText(textView_airport_to__change);
                textView_airport_to.setText(textView_airport_from_change);
            }
        });

        textView_date = (TextView) view.findViewById(R.id.fragment_status_textview_date);
        textView_date.setText(new SimpleDateFormat("yyyy年MM月dd日  EEEE").format(new Date()));
        textView_date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), CalendarActivity.class);
                getContext().startActivity(intent);
            }
        });

        receiver = new MyStatusReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.iss.dateSelected");
        filter.addAction("com.iss.airportselected");
        getContext().registerReceiver(receiver, filter);

        choose_line1 = (LinearLayout) view.findViewById(R.id.fragment_status_choose_line1);
        choose_line2 = (LinearLayout) view.findViewById(R.id.fragment_status_choose_line2);
        textView_choose1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                choose_line1.setVisibility(View.VISIBLE);
                choose_line2.setVisibility(View.GONE);
            }
        });
        textView_choose2.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               choose_line2.setVisibility(View.VISIBLE);
               choose_line1.setVisibility(View.GONE);
           }
       });

        textView_airport_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AirportListActivity.class);
                intent.putExtra("type","from");
                startActivity(intent);
            }
        });
        textView_airport_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getContext(), AirportListActivity.class);
                intent.putExtra("type","to");
                startActivity(intent);
            }
        });
        return view;
    }

    private class MyStatusReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.iss.dateSelected".equals(intent.getAction())) {
                long time = intent.getLongExtra("time", 0);
                textView_date.setText(new SimpleDateFormat("yyyy年MM月dd日  EEEE").format(time));
            } else if ("com.iss.airportselected".equals(intent.getAction())){
                String airportName=intent.getStringExtra("airportName");
                String airportType=intent.getStringExtra("airpotrType");
                if ("from".equals(airportType)){
                    textView_airport_from.setText(airportName);
                }else if ("to".equals(airportType)){
                    textView_airport_to.setText(airportName);
                }
            }
        }
    }
}
