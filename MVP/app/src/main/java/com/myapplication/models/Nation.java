package com.myapplication.models;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

public class Nation implements Parcelable {

    @SerializedName("region")
    private String region;

    @SerializedName("numericCode")
    private String numericCode;

    @SerializedName("nativeName")
    private String nativeName;

    @SerializedName("alpha2Code")
    private String alpha2Code;

    @SerializedName("capital")
    private String capital;

    @SerializedName("subregion")
    private String subregion;

    @SerializedName("flag")
    private String flag;

    @SerializedName("area")
    private String area;

    @SerializedName("name")
    private String name;

    @SerializedName("latlng")
    private String[] latlng;

    @SerializedName("demonym")
    private String demonym;

    @SerializedName("borders")
    private String[] borders;

    @SerializedName("population")
    private String population;

    protected Nation(Parcel in) {
        region = in.readString();
        numericCode = in.readString();
        nativeName = in.readString();
        alpha2Code = in.readString();
        capital = in.readString();
        subregion = in.readString();
        flag = in.readString();
        area = in.readString();
        name = in.readString();
        latlng = in.createStringArray();
        demonym = in.readString();
        borders = in.createStringArray();
        population = in.readString();
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(region);
        dest.writeString(numericCode);
        dest.writeString(nativeName);
        dest.writeString(alpha2Code);
        dest.writeString(capital);
        dest.writeString(subregion);
        dest.writeString(flag);
        dest.writeString(area);
        dest.writeString(name);
        dest.writeStringArray(latlng);
        dest.writeString(demonym);
        dest.writeStringArray(borders);
        dest.writeString(population);
    }

    @SuppressWarnings("unused")
    public static final Parcelable.Creator<Nation> CREATOR = new Parcelable.Creator<Nation>() {
        @Override
        public Nation createFromParcel(Parcel in) {
            return new Nation(in);
        }

        @Override
        public Nation[] newArray(int size) {
            return new Nation[size];
        }
    };


    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getNumericCode() {
        return numericCode;
    }

    public void setNumericCode(String numericCode) {
        this.numericCode = numericCode;
    }

    public String getNativeName() {
        return nativeName;
    }

    public void setNativeName(String nativeName) {
        this.nativeName = nativeName;
    }

    public String getAlpha2Code() {
        return alpha2Code;
    }

    public void setAlpha2Code(String alpha2Code) {
        this.alpha2Code = alpha2Code;
    }

    public String getCapital() {
        return capital;
    }

    public void setCapital(String capital) {
        this.capital = capital;
    }

    public String getSubregion() {
        return subregion;
    }

    public void setSubregion(String subregion) {
        this.subregion = subregion;
    }

    public String getFlag() {
        return flag;
    }

    public void setFlag(String flag) {
        this.flag = flag;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String[] getLatlng() {
        return latlng;
    }

    public void setLatlng(String[] latlng) {
        this.latlng = latlng;
    }

    public String getDemonym() {
        return demonym;
    }

    public void setDemonym(String demonym) {
        this.demonym = demonym;
    }

    public String[] getBorders() {
        return borders;
    }

    public void setBorders(String[] borders) {
        this.borders = borders;
    }

    public String getPopulation() {
        return population;
    }

    public void setPopulation(String population) {
        this.population = population;
    }
}
