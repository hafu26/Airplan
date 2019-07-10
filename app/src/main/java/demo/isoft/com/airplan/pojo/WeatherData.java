package demo.isoft.com.airplan.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hafu_16 on 2019/7/9.
 */

public class WeatherData implements Parcelable {
    //如果该类的对象，通过Internet传递，必须是该类首先arcelable接口
    private String date;
    private String dayPictureUrl;
    private String nightPictureUrl;
    private String weather;
    private String wind;
    private String temperature;
    private String dayPicPath;
    private String nightPicath;

    public String getDayPicPath() {
        return dayPicPath;
    }

    public void setDayPicPath(String dayPicath) {
        this.dayPicPath = dayPicath;
    }

    public String getNightPicath() {
        return nightPicath;
    }

    public void setNightPicath(String nightPicath) {
        this.nightPicath = nightPicath;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDayPictureUrl() {
        return dayPictureUrl;
    }

    public void setDayPictureUrl(String dayPictureUrl) {
        this.dayPictureUrl = dayPictureUrl;
    }

    public String getNightPictureUrl() {
        return nightPictureUrl;
    }

    public void setNightPictureUrl(String nightPictureUrl) {
        this.nightPictureUrl = nightPictureUrl;
    }

    public String getWeather() {
        return weather;
    }

    public void setWeather(String weather) {
        this.weather = weather;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }

    public static Creator<WeatherData> getCREATOR() {
        return CREATOR;
    }

    public WeatherData() {

    }

    protected WeatherData(Parcel in) {
        date = in.readString();
        dayPictureUrl = in.readString();
        nightPictureUrl = in.readString();
        weather = in.readString();
        wind = in.readString();
        temperature = in.readString();
        dayPicPath=in.readString();
        nightPicath=in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(date);
        dest.writeString(dayPictureUrl);
        dest.writeString(nightPictureUrl);
        dest.writeString(weather);
        dest.writeString(wind);
        dest.writeString(temperature);
        dest.writeString(dayPicPath);
        dest.writeString(nightPicath);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<WeatherData> CREATOR = new Creator<WeatherData>() {
        @Override
        public WeatherData createFromParcel(Parcel in) {
            return new WeatherData(in);
        }

        @Override
        public WeatherData[] newArray(int size) {
            return new WeatherData[size];
        }
    };
}
