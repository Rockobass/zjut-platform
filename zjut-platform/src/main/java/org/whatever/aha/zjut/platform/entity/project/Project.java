package org.whatever.aha.zjut.platform.entity.project;

import lombok.Data;

import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc 项目表
 * @date 2021/09/03 22:01
 */
@Data
public class Project {
    /**
     * 项目id
     */
    int projectId;
    /**
     * 竞赛id
     */
    int compId;
    /**
     * 竞赛通道
     */
    int compTrack;
    /**
     * 竞赛阶段
     */
    int compStage;
    /**
     * 项目名称
     */
    String projectName;
    /**
     * 项目负责人
     */
    int projectLeaderId;
    /**
     * 项目创建时间
     */
    Date projectCreateTime;
    /**
     * 项目得分
     */
    Double projectScore;
    /**
     * 项目排名
     */
    int projectRank;
    /**
     * 项目状态(1:未审核、2:退回修改、3:已审核) 院级管理员视角
     */
    int projectAcademyStatus;
    /**
     * 项目状态(1:待提交、2:已提交、3:已退回、4:已完结) 学生视角
     */
    int projectStudentStatus;
    /**
     * 项目组别
     */
    String projectGroup;
    /**
     * 项目标签
     */
    String projectTag;
    /**
     * 项目学院id
     */
    int projectAcademyId;
    /**
     * 赛事奖励id(0:特等  1:一等  2：二等  3：三等  4：鼓励 )
     */
    int projectAwardId;
    /**
     * 是否省级推荐 0：否、1：是
     */
    Boolean projectProvinceRec;
    /**
     * 删除状态(0:未删除 1:已删除)
     */
    Boolean projectDeleteStatus;
    /**
     * 学院备注
     */
    String projectAcademyNote;
}
