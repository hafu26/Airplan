package demo.isoft.com.airplan.myview;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.GridView;

/**
 * Created by hafu_16 on 2019/7/8.
 */

public class MygrideView  extends GridView{

    public MygrideView(Context context) {
        super(context);
    }

    public MygrideView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MygrideView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec=MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE>>2,MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec,expandSpec);
    }
}
