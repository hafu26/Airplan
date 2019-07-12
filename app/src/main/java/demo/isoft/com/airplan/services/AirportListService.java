package demo.isoft.com.airplan.services;

import android.app.IntentService;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.ArrayList;

import demo.isoft.com.airplan.pojo.Airport;
import demo.isoft.com.airplan.urls.Urls;



public class AirportListService extends IntentService{
    private ArrayList<Airport> list =new ArrayList<>();

    public AirportListService() {
        super("AirportListService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        if (intent != null) {
            String airPortType = intent.getStringExtra("airPortType");
            String property = intent.getStringExtra("property");
            String airPortName=intent.getStringExtra("airPortName");
            getAirportData(property, airPortType,airPortName);
        }
    }

    private void getAirportData(String property, String airPortType,String airPortName) {
        String url= Urls.AIRPORT_LIST_URL;
        RequestParams params=new RequestParams(url);
        if (airPortName==null  || airPortName.trim().equals("")){
            params.addBodyParameter("property",property);
        }else {
            params.addBodyParameter("airPortName",airPortName);
        }
        x.http().get(params, new Callback.CommonCallback<String>() {
            @Override
            public void onSuccess(String result) {
                try {
                    JSONArray array=new JSONArray(result);
                    Airport airport=null;
                    for (int i=0;i<array.length();i++){
                        airport=new Airport();
                        airport.setFullNameCn(array.getJSONObject(i).getString("fullNameCn"));
                        airport.setFourWordsCode(array.getJSONObject(i).getString("fourWordsCode"));
                        airport.setThreeWordsCode(array.getJSONObject(i).getString("threeWordsCode"));
                        list.add(airport);
                    }
                    Intent intent=new Intent("com.iss.airportlist");
                    intent.putParcelableArrayListExtra("list",list);
                    sendBroadcast(intent);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
