package com.liang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.pojo.Question;
import com.liang.vo.question.Answers;
import com.liang.vo.question.QuestionVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-06
 */
@Mapper
@Repository
public interface QuestionMapper extends BaseMapper<Question> {

    QuestionVo detail(Integer qid);

    List<QuestionVo> getQuestion(Integer uid);

    List<QuestionVo> questionAbroad();

    List<QuestionVo> getQuestionWithAdmin(String key);

    List<Answers> getAnswers(Integer qid);

}
