/*
 * Project: aha_zjut
 *
 * File Created at 2021/8/9
 *
 * 自定义时间格式代替DATE标签：2021-08-09 01:06:1:06 上午
 */

package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.constant.AuthConst;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.service.UserRoleService;

import javax.validation.constraints.Min;

/**
 * @author Vc
 * @version 1.0
 * @date 2021/8/9 1:06 上午
 */
@RequestMapping("/UserRole")
@RequiredArgsConstructor
@RestController
@Validated
@SaCheckPermission(value = {AuthConst.R_supper, AuthConst.USER_ROLE_LIST}, mode = SaMode.OR)
public class UserRoleController {
    final UserRoleService userRoleService;

    /**
     * 拉取指定用户的权限码列表  后台筛选列表
     */
    @RequestMapping("/getRoleByUid")
    public Object getRoleByUid(@Min (1)@RequestParam() int roleId){
        return AjaxResult.SUCCESS(userRoleService.getRoleByUid(roleId));
    }

    /**
     * 增加user-role映射  新增用户 或 后台管理用户对应权限
     */
    @RequestMapping("/addUserRole")
    public Object addUserRole(int userId, int roleId){
        return AjaxResult.SUCCESS(userRoleService.addUserRole(userId, roleId));
    }


    /**
     * 删除一条userId对应的Role信息
     **/
    @RequestMapping("/deleteOneUR")
    public Object deleteOneUR(Integer userId, Integer roleId){
        return AjaxResult.SUCCESS(userRoleService.deleteOneUR(userId, roleId));
    }


    /**
     * 删除userId对应的所有Role信息
     */
    @RequestMapping("/deleteRoleByUid")
    public Object deleteRoleByUid(Integer userId){
        return AjaxResult.SUCCESS(userRoleService.deleteRoleByUid(userId));
    }
}
