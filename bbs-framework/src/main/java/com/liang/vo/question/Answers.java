package com.liang.vo.question;

import com.liang.pojo.Answer;
import com.liang.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Answers extends Answer {

    private User AnswerUser;

}
