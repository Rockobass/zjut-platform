/*
 * Project: aha_zjut
 *
 * File Created at 2021/8/9
 *
 * 自定义时间格式代替DATE标签：2021-08-09 01:43:1:43 上午
 */

package org.whatever.aha.zjut.platform.controller;

import cn.dev33.satoken.stp.StpUtil;
import lombok.RequiredArgsConstructor;
import org.whatever.aha.zjut.platform.entity.User;
import org.whatever.aha.zjut.platform.service.UserService;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/8/9 1:43 上午
 */

//TODO 补充用户登录等等信息
@RequiredArgsConstructor
public class UserController {
    final UserService userService;

}
