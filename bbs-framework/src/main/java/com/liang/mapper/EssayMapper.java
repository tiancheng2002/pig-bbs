package com.liang.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.liang.pojo.Essay;
import com.liang.vo.essay.EssayRejectVo;
import com.liang.vo.essay.EssayVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author xiaozhu
 * @since 2023-05-04
 */
@Mapper
@Repository
public interface EssayMapper extends BaseMapper<Essay> {

    List<EssayVo> getEssay(String key);

    EssayVo getEssayById(Integer eid);

    List<EssayVo> getByUser(Integer uid);

    List<EssayVo> getAbroad();

    void insertRejectInfo(@Param("eid") Integer eid,@Param("info") String info);

    List<EssayVo> getEssayWithAdmin(@Param("key") String key,@Param("status") Integer status);

    EssayRejectVo getReject(Integer eid);

    void delRejectInfo(Integer eid);

}
