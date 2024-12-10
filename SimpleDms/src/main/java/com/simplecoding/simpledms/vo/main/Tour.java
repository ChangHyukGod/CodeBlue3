package com.simplecoding.simpledms.vo.main;

import lombok.*;

/**
 * @author : KTE
 * @fileName : Tour
 * @since : 24. 11. 27.
 * description :
 */
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Tour {
//    TOUR_ID	NUMBER(38,0)
//    NAME	VARCHAR2(100 BYTE)
//    LOCATION	VARCHAR2(255 BYTE)
//    DESCRIPTION	CLOB
//    PRICE	VARCHAR2(100 BYTE)
//    TOUR_DATA	BLOB
//    TOUR_FILE_URL	VARCHAR2(1000 BYTE)
//    VIEW	VARCHAR2(100 BYTE)
//    CATEGORY	VARCHAR2(100 BYTE)
    private int tourId;  //기본키
    private String name;
    private String location;
    private String description;
    private String price;
    private byte[] tourData;
    private String tourFileUrl;
    private String view;
    private String category;

//  생성자
    public Tour(int tourId, String name, String location, String description, String price, byte[] tourData, String view, String category) {
        this.tourId = tourId;
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = price;
        this.tourData = tourData;
        this.view = view;
        this.category = category;
    }

    public Tour(String name, String location, String description, String price, byte[] tourData, String view, String category) {
        this.name = name;
        this.location = location;
        this.description = description;
        this.price = price;
        this.tourData = tourData;
        this.view = view;
        this.category = category;
    }
}