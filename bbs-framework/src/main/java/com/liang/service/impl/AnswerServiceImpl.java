package com.liang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.mapper.AnswerMapper;
import com.liang.pojo.Answer;
import com.liang.service.AnswerService;
import com.liang.vo.answer.AnswerVo;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-11
 */
@Service
public class AnswerServiceImpl extends ServiceImpl<AnswerMapper, Answer> implements AnswerService {

    @Override
    public List<AnswerVo> getAnswer(Integer uid) {
        return baseMapper.getAnswer(uid);
    }

}
