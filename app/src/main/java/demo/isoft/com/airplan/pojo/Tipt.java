package demo.isoft.com.airplan.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hafu_16 on 2019/7/9.
 */

public class Tipt implements Parcelable {
    //如果该类的对象，通过Internet传递，必须是该类首先arcelable接口

    private String des;
    private String tipt;
    private String title;
    private String zs;

    public Tipt(Parcel in) {
        des = in.readString();
        tipt = in.readString();
        title = in.readString();
        zs = in.readString();

    }
    public static  final Creator<Tipt> CREATOR=new Creator<Tipt>() {
        @Override
        public Tipt createFromParcel(Parcel in) {
            return new Tipt(in);
        }

        @Override
        public Tipt[] newArray(int size) {
            return new Tipt[size];
        }
    } ;

    public Tipt() {

    }

    public String getDes() {
        return des;
    }

    public void setDes(String des) {
        this.des = des;
    }

    public String getTipt() {
        return tipt;
    }

    public void setTipt(String tipt) {
        this.tipt = tipt;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getZs() {
        return zs;
    }

    public void setZs(String zs) {
        this.zs = zs;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(des);
        dest.writeString(tipt);
        dest.writeString(title);
        dest.writeString(zs);
    }
}
