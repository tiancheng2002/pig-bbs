package com.liang.vo.answer;

import com.liang.pojo.Answer;
import com.liang.pojo.Question;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class AnswerVo extends Answer {

    private Question question;

}
