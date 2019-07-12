package demo.isoft.com.airplan.pojo;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by hafu_16 on 2019/7/12.
 */

public class Airport implements Parcelable{
    private String fullNameCn;
    private String threeWordsCode;
    private String fourWordsCode;

    public Airport(){ }
    public Airport(Parcel in) {
        fullNameCn = in.readString();
        threeWordsCode = in.readString();
        fourWordsCode = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(fullNameCn);
        dest.writeString(threeWordsCode);
        dest.writeString(fourWordsCode);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Airport> CREATOR = new Creator<Airport>() {
        @Override
        public Airport createFromParcel(Parcel in) {
            return new Airport(in);
        }

        @Override
        public Airport[] newArray(int size) {
            return new Airport[size];
        }
    };

    public String getFullNameCn() {
        return fullNameCn;
    }

    public void setFullNameCn(String fullNameCn) {
        this.fullNameCn = fullNameCn;
    }

    public String getThreeWordsCode() {
        return threeWordsCode;
    }

    public void setThreeWordsCode(String threeWordsCode) {
        this.threeWordsCode = threeWordsCode;
    }

    public String getFourWordsCode() {
        return fourWordsCode;
    }

    public void setFourWordsCode(String fourWordsCode) {
        this.fourWordsCode = fourWordsCode;
    }
}
