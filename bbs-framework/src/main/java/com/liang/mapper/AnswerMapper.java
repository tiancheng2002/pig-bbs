package com.liang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.pojo.Answer;
import com.liang.vo.answer.AnswerVo;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-11
 */
@Mapper
@Repository
public interface AnswerMapper extends BaseMapper<Answer> {

    List<AnswerVo> getAnswer(Integer uid);

}
