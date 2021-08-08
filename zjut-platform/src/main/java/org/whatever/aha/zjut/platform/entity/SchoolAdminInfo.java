/*
 * Project: aha_zjut
 *
 * File Created at 2021/8/8
 *
 * 自定义时间格式代替DATE标签：2021-08-08 10:50:10:50 上午
 */

package org.whatever.aha.zjut.platform.entity;
import lombok.Builder;
import lombok.Data;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/8/8 10:50 上午
 */
@Data
@Builder
public class SchoolAdminInfo {
    Integer userId;
    String userName;
    String phoneNumber;
    String realName;

}
