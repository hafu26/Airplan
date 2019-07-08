package demo.isoft.com.airplan;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTabHost;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import demo.isoft.com.airplan.fragments.IndexFragment;
import demo.isoft.com.airplan.fragments.MeFragment;
import demo.isoft.com.airplan.fragments.StatusFragment;
import demo.isoft.com.airplan.fragments.SubscribeFragment;

import static demo.isoft.com.airplan.R.id.tabcontent_textview;

public class MainActivity extends AppCompatActivity {
   private FragmentTabHost tabHost;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tabHost= (FragmentTabHost) findViewById(android.R.id.tabhost);
        setTabHost();
    }
    private void setTabHost() {
        FragmentManager manger=getSupportFragmentManager();
        tabHost.setup(this,manger,R.id.topcontent);
        tabHost.addTab(tabHost.newTabSpec("index").setIndicator(getTabView("首页",R.drawable.footer_index_selector)), IndexFragment.class,null);
        tabHost.addTab(tabHost.newTabSpec("status").setIndicator(getTabView("飞机状态",R.drawable.footer_status_selector)), StatusFragment.class,null);
        tabHost.addTab(tabHost.newTabSpec("subscribe").setIndicator(getTabView("订阅",R.drawable.footer_subscribe_selector)), SubscribeFragment.class,null);
        tabHost.addTab(tabHost.newTabSpec("me").setIndicator(getTabView("我的",R.drawable.footer_me_selector)), MeFragment.class,null);
    }
    public View getTabView(String lable,int imageId){
        View view= LayoutInflater.from(this).inflate(R.layout.tabcontent,null);
        ImageView imageView= (ImageView) view.findViewById(R.id.tabcontent_iamgeview);
        TextView textView= (TextView) view.findViewById(tabcontent_textview);
        imageView.setImageResource(imageId);
        textView.setText(lable);
        return view;
    }

}
