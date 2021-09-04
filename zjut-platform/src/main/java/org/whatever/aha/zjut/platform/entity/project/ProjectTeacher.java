package org.whatever.aha.zjut.platform.entity.project;

import lombok.Data;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/09/04 12:52
 */
@Data
public class ProjectTeacher {
    /**
     * 项目id
     */
    int projectId;
    /**
     * 学院id
     */
    int academyId;
    /**
     * 老师的名字
     */
    String teacherName;
    /**
     * 老师职称(1:讲师，2：副教授 3：教授)
     */
    int teacherRank;

}
