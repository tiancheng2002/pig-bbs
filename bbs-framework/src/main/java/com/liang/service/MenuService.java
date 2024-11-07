package com.liang.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.liang.pojo.Menu;
import com.liang.query.MenuQuery;
import com.liang.vo.system.MenuVo;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author xiaozhu
 * @since 2022-09-02
 */
public interface MenuService extends IService<Menu> {

    List<MenuVo> getMenu(MenuQuery menuQuery);

    void removeTree(List<Long> idList);
}
