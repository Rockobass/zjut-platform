package org.whatever.aha.zjut.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * 学生信息实体类
 *
 * @author Baby_mo
 */
@Data
@Builder
public class StudentInfo {
    /**
     * 用户id
     */
    @TableId(type = IdType.NONE)
    int userId;
    /**
     * 学号
     */
    String studentNumber;
    /**
     * 电话号码
     */
    String phoneNumber;
    /**
     * 真实姓名
     */
    String realName;
    /**
     * 性别
     */
    Integer sex;
    /**
     * 学位
     */
    Integer degree;
    /**
     * 年级
     */
    String grade;
    /**
     * 学院id
     */
    int academyId;
    /**
     * 专业id
     */
    int majorId;
    /**
     * 生日
     */
    Date birthday;
    /**
     * 入学时间
     */
    Date admissionTime;
    /**
     * 学校
     */
    String schoolName;
    /**
     * 班级名
     */
    String className;
}
