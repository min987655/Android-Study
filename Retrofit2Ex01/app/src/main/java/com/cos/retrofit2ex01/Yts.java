package com.cos.retrofit2ex01;

import java.util.List;

import lombok.Data;

@Data
public class Yts {

    public String status;
    public String statusMessage;
    public Data data;
    public Meta meta;

    @lombok.Data
    public class Data {

        public Integer movieCount;
        public Integer limit;
        public Integer pageNumber;
        private List<Movie> movies = null;

        @lombok.Data
        public class Movie {

            public Integer id;
            public String url;
            public String imdbCode;
            public String title;
            public String titleEnglish;
            public String titleLong;
            public String slug;
            public Integer year;
            public Double rating;
            public Integer runtime;
            public List<String> genres = null;
            public String summary;
            public String descriptionFull;
            public String synopsis;
            public String ytTrailerCode;
            public String language;
            public String mpaRating;
            public String backgroundImage;
            public String backgroundImageOriginal;
            public String smallCoverImage;
            public String mediumCoverImage;
            public String largeCoverImage;
            public String state;
            public String dateUploaded;
            public Integer dateUploadedUnix;

        }

    }

    @lombok.Data
    public class Meta {
        private Integer serverTime;
        private String serverTimezone;
        private Integer apiVersion;
        private String executionTime;
    }
}

