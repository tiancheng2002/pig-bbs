package com.liang.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liang.mapper.QuestionMapper;
import com.liang.pojo.Question;
import com.liang.service.EssayService;
import com.liang.service.QuestionService;
import com.liang.utils.RedisUtils;
import com.liang.vo.question.Answers;
import com.liang.vo.question.QuestionVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-06
 */
@Service
public class QuestionServiceImpl extends ServiceImpl<QuestionMapper, Question> implements QuestionService {

    @Autowired
    private EssayService essayService;

    @Autowired
    private RedisUtils redisUtils;

    @Override
    public QuestionVo questionDetail(Integer qid,Integer uid) {
        QuestionVo questionVo = baseMapper.detail(qid);
        if(uid!=null){
            Set<Integer> user_attention = (Set<Integer>) redisUtils.hget("user_attention", String.valueOf(uid));
            if(user_attention==null){
                user_attention = new HashSet<>();
                redisUtils.hset("user_attention", String.valueOf(uid),user_attention);
            }
            questionVo.getUser().setAttention(user_attention.contains(questionVo.getUid()));
        }
        essayService.setUserData(questionVo.getUser());
        return questionVo;
    }

    @Override
    public List<QuestionVo> getQuestion(Integer uid) {
        return baseMapper.getQuestion(uid);
    }

    @Override
    public List<QuestionVo> questionAbroad() {
        return baseMapper.questionAbroad();
    }

    @Override
    public List<QuestionVo> getQuestionWithAdmin(String key) {
        return baseMapper.getQuestionWithAdmin(key);
    }

    @Override
    public List<Answers> getAnswersWithAdmin(Integer qid) {
        return baseMapper.getAnswers(qid);
    }

}
