package demo.isoft.com.airplan.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by hafu_16 on 2019/7/13.
 */

public class TimeFormatUtil {
    public static String timeformat(Date date) {
        return new SimpleDateFormat("yyyy年MM月dd日  EEEE").format(date);
    }
    public static String timeformat(long date) {
        return new SimpleDateFormat("yyyy年MM月dd日  EEEE").format(date);
    }
}
