package com.example.rendongliu.brightomdb.domain;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by rendong.liu on 17/08/15.
 */
public class MovieData implements Parcelable {
    protected String Title;
    protected String Year;
    protected String Director;
    protected String Actors;
    protected String Language;
    protected String Poster;
    protected String Type;
    protected String Response;
    protected String Plot;
    protected String Error;




    public static final Parcelable.Creator<MovieData> CREATOR = new Parcelable.Creator<MovieData>() {
        public MovieData createFromParcel(Parcel in) {
            return new MovieData(in);
        }

        public MovieData[] newArray(int size) {
            return new MovieData[size];
        }
    };



    public MovieData(Parcel in) {
        Title = in.readString();
        Response = in.readString();
        Type = in.readString();
        Poster =in.readString();
        Language = in.readString();
        Plot = in.readString();
        Director = in.readString();
        Year = in.readString();
        Actors = in.readString();
        Error = in.readString();
    }

    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public String getActors() {
        return Actors;
    }

    public void setActors(String actors) {
        Actors = actors;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getType() {
        return Type;
    }

    public void setType(String type) {
        Type = type;
    }

    public String getPoster() {
        return Poster;
    }

    public void setPoster(String poster) {
        Poster = poster;
    }

    public String getLanguage() {
        return Language;
    }

    public void setLanguage(String language) {
        Language = language;
    }

    public String getDirector() {
        return Director;
    }

    public void setDirector(String director) {
        Director = director;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Title);
        dest.writeString(Year);
        dest.writeString( Director);
        dest.writeString(Actors);
        dest.writeString( Language );
        dest.writeString( Poster );
        dest.writeString(Type );
        dest.writeString( Response );
        dest.writeString(Plot );
        dest.writeString( Error );

    }
}
