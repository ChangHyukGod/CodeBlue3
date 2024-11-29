package com.simplecoding.simpledms.vo.recommend;

import lombok.*;

/**
 * @author : KTE-149
 * @fileName : Recommend
 * @since : 24. 11. 29.
 * description :
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Recommend {

//    TD_ID	NUMBER
//    TD_NAME	VARCHAR2(255 BYTE)
//    LOC	VARCHAR2(255 BYTE)
//    DESCRIPTION	CLOB
//    IMAGE_URL	VARCHAR2(1000 BYTE)
//    IMAGE_DATA 	BLOB
//    POPULARITY	NUMBER

    private int tdId;
    private String tdName;
    private String loc;
    private String description;
    private byte[] imageData;
    private int popularity;
    private String imageUrl;


    public Recommend(int tdId, String tdName, String loc, String description, byte[] imageData, int popularity) {
        this.tdId = tdId;
        this.tdName = tdName;
        this.loc = loc;
        this.description = description;
        this.imageData = imageData;
        this.popularity = popularity;
    }


    public Recommend(String tdName, String loc, String description, byte[] imageData, int popularity) {
        this.tdName = tdName;
        this.loc = loc;
        this.description = description;
        this.imageData = imageData;
        this.popularity = popularity;
    }

    public Recommend(String tdName, String loc, String description, String popularity, byte[] bytes) {
    }

    public Recommend(int tdId, String tdName, String loc, String description, String popularity, byte[] bytes) {
    }
}

