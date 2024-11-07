package com.liang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.param.ApproveParam;
import com.liang.param.EssayParam;
import com.liang.pojo.Essay;
import com.liang.pojo.User;
import com.liang.result.RespBean;
import com.liang.vo.essay.EssayRejectVo;
import com.liang.vo.essay.EssayVo;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
public interface EssayService extends IService<Essay> {

    RespBean essayStar(Integer eid, Integer uid);

    RespBean getStar(Integer uid);

    List<EssayVo> getEssay(String key);

    EssayVo getEssayById(Integer eid, Integer uid);

    List<EssayVo> getByUser(Integer uid);

    void setUserData(User user);

    List<EssayVo> getAbroad();

    void action(Essay essay,List<Integer> types);

    void approve(ApproveParam approveParam);

    List<EssayVo> getEssayWithAdmin(String key,Integer status);

    EssayRejectVo getRejectInfo(Integer eid);

}
