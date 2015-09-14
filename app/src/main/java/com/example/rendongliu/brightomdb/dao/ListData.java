package com.example.rendongliu.brightomdb.dao;

import java.util.List;

/**
 * Created by rendong.liu on 09/09/15.
 */
public class ListData {
    protected String Response;
    protected String Error;
    protected List<movie> Search;

    public ListData(String response, String error, List<movie> search) {
        Response = response;
        Error = error;
        Search = search;
    }

    public List<movie> getSearch() {

        return Search;
    }

    public void setSearch(List<movie> search) {
        Search = search;
    }

    public class movie{
        protected String Title;
        protected String Year;
        protected String imdbID;
        protected String Type;

        public movie(String title, String year, String imdbID, String type) {
            Title = title;
            Year = year;
            this.imdbID = imdbID;
            Type = type;
        }

        public String getTitle() {
            return Title;
        }

        public void setTitle(String title) {
            Title = title;
        }

        public String getYear() {
            return Year;
        }

        public void setYear(String year) {
            Year = year;
        }

        public String getImdbID() {
            return imdbID;
        }

        public void setImdbID(String imdbID) {
            this.imdbID = imdbID;
        }

        public String getType() {
            return Type;
        }

        public void setType(String type) {
            Type = type;
        }
    }



    public String getResponse() {
        return Response;
    }

    public void setResponse(String response) {
        Response = response;
    }

    public String getError() {
        return Error;
    }

    public void setError(String error) {
        Error = error;
    }
}
