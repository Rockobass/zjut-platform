/*
 * Project: aha_zjut
 *
 * File Created at 2021/8/8
 *
 * 自定义时间格式代替DATE标签：2021-08-08 17:31:5:31 下午
 */

package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.stp.StpUtil;
import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.whatever.aha.zjut.base.constant.AuthConst;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.Util.UserRoleUtil;
import org.whatever.aha.zjut.platform.service.RolePermissionService;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/8/8 5:31 下午
 */
@RequestMapping("/RolePermission")
@RequiredArgsConstructor
@RestController
public class RolePermissionController {
    final RolePermissionService rolePermissionService;

    /**
     * 拉取权限id列表  根据指定roleId
     * @param roleId
     * @return
     */
    @RequestMapping("/getPcodeByRid")
    public AjaxResult getPcodeByRid(@RequestParam(defaultValue="0") int roleId){
        // 进行超级用户鉴权
        StpUtil.checkPermission(AuthConst.R_supper);
        StpUtil.checkPermission(AuthConst.ROLE_PERMISSION_LIST);
        // 防止拉出全部
        if(roleId == 0){
            return AjaxResult.FAIL("roleId不能为null或0",null);
        }
        return AjaxResult.SUCCESS(rolePermissionService.getPcodeByRid2(roleId));
    }


    /**
     * 根据当前用户roleId拉取菜单permissionId列表
     **/
    @RequestMapping("getPcodeByCurrRid")
    public AjaxResult getPcodeByCurrRid(){
        Set PcodeSet=new HashSet();
        List<Object> roleIds = UserRoleUtil.getCurrRole();
        roleIds.forEach(roleId ->
                {
                    List<Object> Pcodes = rolePermissionService.getPcodeByRid2((Integer) roleId);
                    PcodeSet.addAll(Pcodes);
                }
                );
        return AjaxResult.SUCCESS(PcodeSet);
    }


    /**
     * 修改指定角色的拥有的权限
     * @param roleId 角色id
     * @param code 拥有的权限码集合
     * @return
     */
    @RequestMapping("updatePcodeByRid")
    public AjaxResult updatePcodeByRid(int roleId, String[] code){
        StpUtil.checkPermission(AuthConst.R_supper);
        StpUtil.checkPermission(AuthConst.ROLE_PERMISSION_LIST);
        return AjaxResult.SUCCESS(rolePermissionService.updateRoleMenu(roleId, code));
    }

}
