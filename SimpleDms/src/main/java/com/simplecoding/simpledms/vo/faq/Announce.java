package com.simplecoding.simpledms.vo.faq;

import lombok.*;

/**
 * @author : KTE
 * @fileName : Announce
 * @since : 24. 12. 3.
 * description :
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Announce {
    private int ano;
    private String title;
    private String content;
    private String date;
}
