package com.simplecoding.simpledms.vo.admin;

import lombok.*;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@ToString
@RequiredArgsConstructor
public class Admin {
        private int fno;            // FAQ 고유 ID (FNO)
        private String title;       // 제목 (TITLE)
        private String question;    // 질문 내용 (QUESTION)
        private String answer;      // 답변 내용 (ANSWER)
        private Date insertTime;    // 등록 시간 (INSERT_TIME)
        private Date updateTime;    // 수정 시간 (UPDATE_TIME)
        private byte[] faqData;     // 이미지 (FILE_DATA)
        private String faqUrl;      // 이미지 파일 URL (FILE_URL)

    }

