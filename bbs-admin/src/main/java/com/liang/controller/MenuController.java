package com.liang.controller;

import com.liang.pojo.Menu;
import com.liang.query.MenuQuery;
import com.liang.service.MenuService;
import com.liang.vo.system.MenuVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author xiaozhu
 * @since 2022-09-02
 */
@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;

    /*
        返回所有的菜单信息
     */
//    @RequestMapping("/all")
//    public List<Menu> getMenu(@RequestParam(value = "menuId",required = false,defaultValue = "0") Integer menuId){
//        return menuService.getMenu(menuId);
//    }

    @RequestMapping("/all")
    public List<MenuVo> getMenu(MenuQuery menuQuery){
        List<MenuVo> menus = menuService.getMenu(menuQuery);
        return menus;
    }

    /*
        根据id查找对应菜单信息
     */
    @RequestMapping("/detail")
    public Menu getMenuDetail(@RequestParam("menuId") Integer menuId){
        return menuService.getById(menuId);
    }

    /*
        添加或修改对应菜单信息
     */
    @PostMapping("/action")
    public String actionMenu(@RequestBody Menu menu){
        if(menu.getMenuId()!=null){
            menuService.updateById(menu);
        }else{
            menuService.save(menu);
        }
        return "ok";
    }

    /*
        删除对应菜单信息
     */
    @PostMapping("/del")
    public String delMenu(@RequestBody List<Long> idList){
        menuService.removeTree(idList);
        return "ok";
    }

}
