/*
 * Project: aha_zjut
 *
 * File Created at 2021/8/8
 *
 * 自定义时间格式代替DATE标签：2021-08-08 17:31:5:31 下午
 */

package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.apache.ibatis.annotations.Update;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.whatever.aha.zjut.base.constant.AuthConst;
import org.whatever.aha.zjut.base.dto.AjaxResult;
import org.whatever.aha.zjut.platform.service.RolePermissionService;

import javax.validation.constraints.Min;

/**
 * @author Vc
 * @version 1.0
 * @date 2021/8/8 5:31 下午
 */
@Api(tags = "角色对应权限操作")
@RequestMapping("/RolePermission")
@RequiredArgsConstructor
@RestController
@Validated
@SaCheckPermission(value = {AuthConst.R_supper, AuthConst.ROLE_PERMISSION_LIST}, mode = SaMode.OR)
public class RolePermissionController {
    final RolePermissionService rolePermissionService;

    /**
     * 拉取权限id列表  根据指定roleId
     */
    @ApiOperation("获取权限id列表")
    @GetMapping("/getPcodeByRid")
    @ApiImplicitParam(name = "roleId", value = "角色id", dataTypeClass = Integer.class)
    public Object getPcodeByRid(@Min (1)@RequestParam(defaultValue="0") int roleId){
        return AjaxResult.SUCCESS(rolePermissionService.getPcodeByRid2(roleId));
    }

    /**
     * 修改指定角色的拥有的权限
     * @param roleId 角色id
     * @param code 拥有的权限码集合
     */
    @ApiOperation("修改指定角色的拥有的权限")
    @PutMapping("/updatePcodeByRid")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "roleId", value = "角色id", dataTypeClass = Integer.class),
            @ApiImplicitParam(name = "code", value = "权限码集合", allowMultiple = true, dataTypeClass = String.class)
    })
    public Object updatePcodeByRid(int roleId, String[] code){
        return AjaxResult.SUCCESS(rolePermissionService.updateRoleMenu(roleId, code));
    }

}
