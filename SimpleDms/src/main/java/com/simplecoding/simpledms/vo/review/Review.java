package com.simplecoding.simpledms.vo.review;

import lombok.*;
import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Review {
    private int reviewId;        // 기본키
    private String title;        // 후기 제목
    private String content;      // 후기 내용
    private double rating;       // 평점
    private String imageUrl;     // 첨부 이미지 URL
    private String authorEmail;  // 작성자 이메일
    private String targetId;     // 대상 ID
    private Timestamp createdAt; // 생성일
    private Timestamp updatedAt; // 수정일
    private String loc;          // 지역

    // 모든 필드를 포함하는 생성자
    public Review(int reviewId, String title, String content, double rating, String imageUrl,
                  String authorEmail, String targetId, Timestamp createdAt, Timestamp updatedAt, String loc) {
        this.reviewId = reviewId;
        this.title = title;
        this.content = content;
        this.rating = rating;
        this.imageUrl = imageUrl;
        this.authorEmail = authorEmail;
        this.targetId = targetId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.loc = loc;
    }
}
