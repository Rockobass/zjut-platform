package org.whatever.aha.zjut.platform.dto;

import lombok.Builder;
import lombok.Data;
import org.whatever.aha.zjut.platform.entity.StudentInfo;

import java.util.Date;

/**
 * 学生信息dto
 *
 * @author Baby_mo
 * @date 2021/08/15
 */
@Data
public class StudentInfoDto {
    /**
     * 学号
     */
    String studentNumber;
    /**
     * 电话号码
     */
    String phoneNumber;
    /**
     * 实名
     */
    String realName;
    /**
     * 性别
     */
    String sex;
    /**
     * 年级
     */
    String grade;
    /**
     * 学院
     */
    String academy;
    /**
     * 专业
     */
    String major;
    /**
     * 生日
     */
    Date birthday;
    /**
     * 入学时间
     */
    Date admissionTime;
    /**
     * 学校名
     */
    String schoolName;
    /**
     * 班级名
     */
    String className;
}
