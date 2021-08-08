/*
 * Project: aha_zjut
 *
 * File Created at 2021/8/8
 *
 * 自定义时间格式代替DATE标签：2021-08-08 15:32:3:32 下午
 */

package org.whatever.aha.zjut.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/8/8 3:32 下午
 */
@Data
@NoArgsConstructor
@TableName(value = "role_permission")
public class RolePermission {
    @TableId(type = IdType.AUTO)
    Integer id;
    Integer roleId;
    String permissionCode;
    Timestamp createTime;

    public RolePermission(Integer roleId, String permissionCode) {
        Date date = new Date();
        this.roleId = roleId;
        this.permissionCode = permissionCode;
        this.createTime = new Timestamp(date.getTime());
    }
}
