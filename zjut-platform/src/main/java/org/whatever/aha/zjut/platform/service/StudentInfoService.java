package org.whatever.aha.zjut.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.base.constant.ErrorCode;
import org.whatever.aha.zjut.base.exception.AppException;
import org.whatever.aha.zjut.platform.dto.StudentInfoDto;
import org.whatever.aha.zjut.platform.entity.Academy;
import org.whatever.aha.zjut.platform.entity.Major;
import org.whatever.aha.zjut.platform.entity.StudentInfo;
import org.whatever.aha.zjut.platform.entity.User;
import org.whatever.aha.zjut.platform.mapper.*;

/**
 * @author Baby_mo
 */
@Service
@RequiredArgsConstructor
public class StudentInfoService {
    final UserMapper userMapper;
    final StudentInfoMapper studentInfoMapper;
    final PasswordEncoder passwordEncoder;
    final AcademyMapper academyMapper;
    final MajorMapper majorMapper;
    final UserRoleMapper userRoleMapper;

    /**
     * 学生注册
     */
    @Transactional(rollbackFor = Exception.class)
    public Integer insertStudent(String password, String realName, Integer sex, Integer degree, String grade,
                              int academyId, int majorId, String phoneNumber, String studentNumber) {
        if (existStuNumber(studentNumber)) {
            throw new AppException(ErrorCode.STUDENT_NUMBER_REGISTERED);
        }
        User user = User.builder().phoneNumber(phoneNumber)
                .username(studentNumber)
                .password(passwordEncoder.encode(password))
                .loginType(0).build();
        userMapper.insert(user);

        int userId = user.getUserId();
        if (academyMapper.selectCount(new QueryWrapper<Academy>().eq("academy_id", academyId)) != 1
                || majorMapper.selectCount(new QueryWrapper<Major>().eq("academy_id", academyId).eq("major_id", majorId)) != 1) {
            throw new AppException(ErrorCode.ILLEGAL_REQUEST);
        }
        userRoleMapper.addStudentRole(userId);

        StudentInfo studentInfo = StudentInfo.builder().userId(userId).phoneNumber(phoneNumber).sex(sex)
                .realName(realName).degree(degree).grade(grade).academyId(academyId)
                .majorId(majorId).studentNumber(studentNumber).schoolName("浙江工业大学").build();
        studentInfoMapper.insert(studentInfo);
        return userId;
    }

    @Cacheable(value = "NoExpire", key = "'stu_info_'+#userId")
    public StudentInfoDto getStudentInfo(int userId) {
        StudentInfo rawInfo = studentInfoMapper.selectOne(new QueryWrapper<StudentInfo>().eq("user_id", userId));
        return convertFor(rawInfo);
    }

    @Cacheable(value = "ExpireOneMin", key = "'stu_num_existed'+#studentNumber")
    public boolean existStuNumber(String studentNumber) {
        int count = studentInfoMapper.selectCount(new QueryWrapper<StudentInfo>().eq("student_number", studentNumber));
        return count != 0;
    }


    public StudentInfoDto convertFor(StudentInfo studentInfo) {
        StudentInfoDto dto = new StudentInfoDto();
        dto.setStudentNumber(studentInfo.getStudentNumber());
        dto.setPhoneNumber(studentInfo.getPhoneNumber());
        dto.setRealName(studentInfo.getRealName());
        dto.setSex(studentInfo.getSex()==0 ? "男" : "女");
        dto.setGrade(studentInfo.getGrade());
        dto.setAcademy(academyMapper.getAcademyName(studentInfo.getAcademyId()));
        dto.setMajor(majorMapper.getMajorName(studentInfo.getMajorId()));
        dto.setBirthday(studentInfo.getBirthday());
        dto.setSchoolName(studentInfo.getSchoolName());
        dto.setAdmissionTime(studentInfo.getAdmissionTime());
        dto.setClassName(studentInfo.getClassName());
        return dto;
    }
}
