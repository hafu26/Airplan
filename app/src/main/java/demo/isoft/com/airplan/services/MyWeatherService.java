package demo.isoft.com.airplan.services;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;

import demo.isoft.com.airplan.pojo.Tipt;
import demo.isoft.com.airplan.pojo.WeatherData;


/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * TODO: Customize class - update intent actions and extra parameters.
 */
public class MyWeatherService extends IntentService {
    String message="success";
    ArrayList<WeatherData> weatherDatas=new ArrayList<>();

    public MyWeatherService() {
        super("MyWeatherService");
    }

    //当执行onHandleIntent方法时，会开启一个worker的线程
    //并且onHandleIntent的方法的所有代码执行完毕，线程会自动消亡，而且服务也自动销毁
    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            String city = null;
            try {
                city = URLEncoder.encode("天津", "UTF-8");
                String url = "http://api.map.baidu.com/telematics/v3/weather?location=" + city + "&mcode=6F:3E:27:E3:FB:CF:2F:70:EA:54:5D:A4:A7:C9:42:B7:81:67:AA:0A;com.example.administrator.myapp23_weatherforcast&output=json&ak=5Lsh7GHRpGvEbYafVjMqtG0RRmWUFOgq";
                RequestParams params = new RequestParams(url);
                x.http().get(params, new Callback.CommonCallback<String>() {

                    @Override
                    public void onSuccess(String result) {
                        parseJson(result);
                    }

                    @Override
                    public void onError(Throwable ex, boolean isOnCallback) {
                        Log.e("iss", "error");
                        message="网络问题，请重新设置网络";
                    }

                    @Override
                    public void onCancelled(CancelledException cex) {

                    }

                    @Override
                    public void onFinished() {

                    }
                });
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
        }
    }

    //解析json数据
    public void parseJson(String result) {
        try {
            JSONObject root = new JSONObject(result);
            int error=root.getInt("error");
            if(error!=0){
                //访问数据无效
                message="数据访问参数错误";
            }else {

                JSONArray results=root.getJSONArray("results");
                JSONObject info=results.getJSONObject(0);
                String pm25=info.getString("pm25");
                JSONArray index=info.getJSONArray("index");
                ArrayList<Tipt> tipts=new ArrayList<>();
                for(int i=0;i<index.length();i++){
                    Tipt tipt=new Tipt( );
                    JSONObject o=index.getJSONObject(i);
                    tipt.setDes(o.getString("des"));
                    tipt.setTipt(o.getString("tipt"));
                    tipt.setTitle(o.getString("title"));
                    tipt.setZs(o.getString("zs"));
                    tipts.add(tipt);
                }


                JSONArray weather_data=info.getJSONArray("weather_data");
                for(int i=0;i<weather_data.length();i++){
                    WeatherData data=new WeatherData();
                    JSONObject o=weather_data.getJSONObject(i);
                    data.setDate(o.getString("date"));
                    data.setDayPictureUrl(o.getString("dayPictureUrl"));
                    downLoadPicture(o.getString("dayPictureUrl"),i,"day");
                    data.setNightPictureUrl(o.getString("nightPictureUrl"));
                    downLoadPicture(o.getString("nightPictureUrl"),i,"night");
                    data.setWeather(o.getString("weather"));
                    data.setWind(o.getString("wind"));
                    data.setTemperature(o.getString("temperature"));
                    weatherDatas.add(data);
                }



                //发送广播
                Intent intent=new Intent("com.iss.tiptweather");
                intent.putExtra("pm25",pm25);
                intent.putExtra("message",message);
                intent.putExtra("error",error);
                intent.putParcelableArrayListExtra("tipts",tipts);
                intent.putParcelableArrayListExtra("weather_data",weatherDatas);
                sendBroadcast(intent);
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    private void downLoadPicture(String url, final  int index, final String type) {
        url=url+"?random="+Math.random();
        RequestParams params=new RequestParams(url);
        x.http().get(params, new Callback.CommonCallback<File>() {

            @Override
            public void onSuccess(File result) {
                if("day".equals(type)){
                    weatherDatas.get(index).setDayPicPath(result.getPath());
                }else if ("night".equals(type)){
                    weatherDatas.get(index).setNightPicath(result.getPath());
                }
                Intent intent =new Intent("com.iss.weather");
                intent.putParcelableArrayListExtra("weather_data",weatherDatas);
                sendBroadcast(intent);
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }
        });
    }
}
