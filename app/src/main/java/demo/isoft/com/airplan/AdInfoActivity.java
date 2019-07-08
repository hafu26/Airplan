package demo.isoft.com.airplan;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AdInfoActivity extends AppCompatActivity {
    TextView textView;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ad_info);
        textView= (TextView) findViewById(R.id.adinfo_textview);
        Intent intent=getIntent();
        String title=intent.getStringExtra("title");
        textView.setText(title);
    }
}
