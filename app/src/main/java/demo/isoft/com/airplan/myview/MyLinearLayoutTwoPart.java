package demo.isoft.com.airplan.myview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.os.Build;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import demo.isoft.com.airplan.R;

/**
 * Created by hafu_16 on 2019/7/11.
 */

public class MyLinearLayoutTwoPart extends LinearLayout {
    private int colorLeft;
    private int colorRight;
    private float heightDifference;
    private int lineOrientation;
    private float lineAngle;
    private Paint paint;

    public MyLinearLayoutTwoPart(Context context) {
        super(context);
        init();
    }

    public MyLinearLayoutTwoPart(Context context, AttributeSet attrs) {
        super(context, attrs);
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.MyLinearLayoutTwoPart);
        colorLeft = array.getColor(R.styleable.MyLinearLayoutTwoPart_colorLeft, 0XFFFFFF);
        colorRight = array.getColor(R.styleable.MyLinearLayoutTwoPart_colorRight, 0XFFFFFF);
        heightDifference = array.getDimension(R.styleable.MyLinearLayoutTwoPart_heightDifference, 0);
        lineOrientation = array.getInteger(R.styleable.MyLinearLayoutTwoPart_lineOrientation, 0);
        lineAngle = array.getFloat(R.styleable.MyLinearLayoutTwoPart_lineAngle, 0);
        init();
    }

    private void init() {
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int width = getWidth();//控件宽度
        int height = getHeight();
        if (lineOrientation == 1) {
            Path path = new Path();
            paint.setColor(colorLeft);
//            绘制左上边圆角
            path.moveTo(0, 20);
            path.lineTo(5, 5);
            path.lineTo(20, 0);
//            path.lineTo(width / 2 - height / 2 * lineAngle, 0);//从上左上右下右下左
            path.lineTo(width / 2 - height / 2 * lineAngle - 20, 0);
            path.lineTo(width / 2 - height / 2 * lineAngle - 15, 5);
            path.lineTo(width / 2 - height / 2 * lineAngle - 5, 10);

            path.lineTo(width / 2 + height / 2 * lineAngle, height);
            path.lineTo(0, height);
            path.close();
            canvas.drawPath(path, paint);

            path = new Path();
            paint.setColor(colorRight);
            path.moveTo(width / 2 - (height / 2 - heightDifference) * lineAngle, heightDifference);
            //path.lineTo(width, 0);//从上左上右下右下左
            path.lineTo(width - 20, heightDifference);
            path.lineTo(width - 5, heightDifference + 5);
            path.lineTo(width, heightDifference+20);

            path.lineTo(width, height);
//            path.lineTo(width / 2 + height / 2 * lineAngle,height);
            path.lineTo(width / 2 + height / 2 * lineAngle+20,height);
            path.lineTo(width / 2 + height / 2 * lineAngle+15,height-5);
            path.lineTo(width / 2 + height / 2 * lineAngle+5,height-10);
            path.close();
            canvas.drawPath(path, paint);

        } else if (lineOrientation == -1) {
            Path path=new Path();
            paint.setColor(colorLeft);
//            左上边的圆角
            path.moveTo(0,heightDifference+20);
            path.lineTo(5,heightDifference+5);
            path.lineTo(20,heightDifference);
            path.lineTo(width/2+(height/2-heightDifference)*lineAngle,heightDifference);

//            path.lineTo(width/2-height/2*lineAngle,height);
            path.lineTo(width / 2 - height / 2 * lineAngle-5,height-10);
            path.lineTo(width / 2 - height / 2 * lineAngle-15,height-5);
            path.lineTo(width / 2 - height / 2 * lineAngle-25,height);

            path.lineTo(0,height);
            path.close();
            canvas.drawPath(path,paint);

            path=new Path();
            paint.setColor(colorRight);
            path.moveTo(width/2+(height/2-heightDifference)*lineAngle,heightDifference);

            path.lineTo(width/2+height/2*lineAngle+5,10);
            path.lineTo(width/2+height/2*lineAngle+15,5);
            path.lineTo(width/2+height/2*lineAngle+20,0);

//            path.lineTo(width/2+height/2*lineAngle,0);
//            path.lineTo(width,0);
            path.lineTo(width-20,0);
            path.lineTo(width-5,5);
            path.lineTo(width,20);

            path.lineTo(width,height);
            path.lineTo(width/2-height/2*lineAngle,height);
            path.close();
            canvas.drawPath(path,paint);
        }
    }

    public MyLinearLayoutTwoPart(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public MyLinearLayoutTwoPart(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }
}
