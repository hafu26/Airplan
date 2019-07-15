package demo.isoft.com.airplan.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hafu_16 on 2019/7/13.
 */

public class PlaneStatus  implements Parcelable{
    private  String code;
    private  String startTime;
    private  String endTime;
    private  String status;

    public PlaneStatus(){

    }

    protected PlaneStatus(Parcel in) {
        code = in.readString();
        startTime = in.readString();
        endTime = in.readString();
        status = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(code);
        dest.writeString(startTime);
        dest.writeString(endTime);
        dest.writeString(status);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<PlaneStatus> CREATOR = new Creator<PlaneStatus>() {
        @Override
        public PlaneStatus createFromParcel(Parcel in) {
            return new PlaneStatus(in);
        }

        @Override
        public PlaneStatus[] newArray(int size) {
            return new PlaneStatus[size];
        }
    };

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
