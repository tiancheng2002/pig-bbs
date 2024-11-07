package com.liang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.pojo.Question;
import com.liang.vo.question.Answers;
import com.liang.vo.question.QuestionVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-06
 */
public interface QuestionService extends IService<Question> {

    QuestionVo questionDetail(Integer qid,Integer uid);

    List<QuestionVo> getQuestion(Integer uid);

    List<QuestionVo> questionAbroad();

    List<QuestionVo> getQuestionWithAdmin(String key);

    List<Answers> getAnswersWithAdmin(Integer qid);

}
