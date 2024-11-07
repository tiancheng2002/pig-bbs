package com.liang.vo.user;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AuthorVo {

    private Integer uid;

    private Integer answerTotal;

    private Integer essayTotal;

    private Integer fans;

}
