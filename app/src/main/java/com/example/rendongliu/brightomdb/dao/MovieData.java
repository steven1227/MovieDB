package com.example.rendongliu.brightomdb.dao;

import java.io.Serializable;

/**
 * Created by rendong.liu on 17/08/15.
 */
public class MovieData implements Serializable {
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


    public String getPlot() {
        return Plot;
    }

    public void setPlot(String plot) {
        Plot = plot;
    }

    public MovieData(String title, String response, String type, String poster, String language, String director, String year, String error, String actors, String plot) {
        Title = title;
        Response = response;
        Type = type;
        Poster = poster;
        Language = language;
        Plot = plot;
        Director = director;
        Year = year;
        Actors = actors;
        Error = error;
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
}
