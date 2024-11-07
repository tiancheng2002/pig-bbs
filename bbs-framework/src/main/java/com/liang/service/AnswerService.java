package com.liang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.pojo.Answer;
import com.liang.vo.answer.AnswerVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-11
 */
public interface AnswerService extends IService<Answer> {

    List<AnswerVo> getAnswer(Integer uid);



}
