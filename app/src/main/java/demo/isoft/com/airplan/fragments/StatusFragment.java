package demo.isoft.com.airplan.fragments;


import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.io.File;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

import demo.isoft.com.airplan.AirportListActivity;
import demo.isoft.com.airplan.CalendarActivity;
import demo.isoft.com.airplan.R;
import demo.isoft.com.airplan.StatusListActivity;
import demo.isoft.com.airplan.utils.IOUtil;

/**
 * A simple {@link Fragment} subclass.
 */
public class StatusFragment extends Fragment {
    private TextView textView_choose1, textView_choose2;
    private LinearLayout choose_line1, choose_line2;
    private TextView textView_date;
    private MyStatusReceiver receiver;
    private TextView textView_airport_from, textView_airport_to;
    private ImageView imageView;
    private String textView_airport_from_change, textView_airport_to__change;
    private Button button;
    private LinearLayout history_line;


    public StatusFragment() {
        // Required empty public constructor
    }


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        textView_choose1 = (TextView) view.findViewById(R.id.fragment_status_choose_textview1);
        textView_choose2 = (TextView) view.findViewById(R.id.fragment_status_choose_textview2);
        textView_airport_from = (TextView) view.findViewById(R.id.fragment_status_airport_from);
        textView_airport_to = (TextView) view.findViewById(R.id.fragment_status_airport_to);
        history_line = (LinearLayout) view.findViewById(R.id.fragment_status_history_line);

        button = (Button) view.findViewById(R.id.fragment_status_info_find_button);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDateToFile();
                Intent intent = new Intent(getContext(), StatusListActivity.class);
                intent.putExtra("from", textView_airport_from.getText());
                intent.putExtra("to", textView_airport_to.getText());
                startActivity(intent);
            }
        });


        imageView = (ImageView) view.findViewById(R.id.fragment_status_airport_fromto_image);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                textView_airport_from_change = textView_airport_from.getText().toString();
                textView_airport_to__change = textView_airport_to.getText().toString();
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
                getHistoryData();
            }
        });

        textView_airport_from.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AirportListActivity.class);
                intent.putExtra("type", "from");
                startActivity(intent);
            }
        });
        textView_airport_to.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getContext(), AirportListActivity.class);
                intent.putExtra("type", "to");
                startActivity(intent);
            }
        });
        return view;
    }
 /*********************************************************************
  * 航班查询记录
  * *******************************************************************/
    /*把选中的记录存放到文件中*/
    public void saveDateToFile() {
        File root = getContext().getFilesDir();
        File file = new File(root, "city");
        ArrayList<String> list = IOUtil.readData(file);
        IOUtil.clearFile(file);
        String content = textView_airport_from.getText() + "-" + textView_airport_to.getText();
        int i = list.size();
        IOUtil.writeData(file, content+ "\n");
        int k=1;
        for (String string : list) {
            if (string.equals(content)==false){
                if(k++<6){
                    IOUtil.writeData(file, string + "\n");
                }
            }
        }
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    public void getHistoryData() {
        history_line.removeAllViews();
        File root = getContext().getFilesDir();
        File file = new File(root, "city");
        ArrayList<String> list = IOUtil.readData(file);
        for (String str : list) {
            final TextView textView = new TextView(getContext());
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
            params.setMargins(10, 10, 10, 10);
            textView.setText(str);
            textView.setBackground(getResources().getDrawable(R.drawable.bg_gray_rounding));
            textView.setPadding(10, 10, 10, 10);
            history_line.addView(textView);
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String text = textView.getText().toString();
                    int index = text.indexOf("-");
                    String from = text.substring(0, index);
                    String to = text.substring(index + 1, text.length());
                    textView_airport_from.setText(from);
                    textView_airport_to.setText(to);
                }
            });

        }
    }
//   ******************************航班记录查询及显示**********************************************



    private class MyStatusReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            if ("com.iss.dateSelected".equals(intent.getAction())) {
                long time = intent.getLongExtra("time", 0);
                textView_date.setText(new SimpleDateFormat("yyyy年MM月dd日  EEEE").format(time));
            } else if ("com.iss.airportselected".equals(intent.getAction())) {
                String airportName = intent.getStringExtra("airportName");
                String airportType = intent.getStringExtra("airpotrType");
                if ("from".equals(airportType)) {
                    textView_airport_from.setText(airportName);
                } else if ("to".equals(airportType)) {
                    textView_airport_to.setText(airportName);
                }
            }
        }
    }
}
