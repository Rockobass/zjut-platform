package org.whatever.aha.zjut.platform.entity.project;

import lombok.Data;

import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc 项目查询评委申请意见表
 * @date 2021/09/03 22:23
 */
@Data
public class ProjectAppJudge {
    /**
     * 申请id
     */
    int projectApplyId;
    /**
     * 项目id
     */
    int projectId;
    /**
     * 赛事阶段
     */
    int compStage;
    /**
     * 申请状态（1：待审核 2：未通过 3：已通过）
     */
    int applyStatus;
    /**
     * 审批意见
     */
   String applyComment;
    /**
     * 更新时间
     */
    Date updateTime;
}
