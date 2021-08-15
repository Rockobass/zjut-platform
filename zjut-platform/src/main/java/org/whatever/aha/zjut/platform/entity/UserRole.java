/*
 * Project: aha_zjut
 *
 * File Created at 2021/8/8
 *
 * 自定义时间格式代替DATE标签：2021-08-08 22:42:10:42 下午
 */

package org.whatever.aha.zjut.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author Vc
 * @version 1.0
 * @date 2021/8/8 10:42 下午
 */
@Data
@AllArgsConstructor
public class UserRole {
    @TableId(type = IdType.NONE)
    int userId;
    int roleId;
}
