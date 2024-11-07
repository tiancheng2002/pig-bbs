package com.liang.vo.question;

import com.liang.pojo.Question;
import com.liang.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class QuestionVo extends Question {

    private User user;

    private Integer answerTotal;

    private List<Answers> answers;

}
