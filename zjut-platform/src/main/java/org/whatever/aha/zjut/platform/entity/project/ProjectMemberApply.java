package org.whatever.aha.zjut.platform.entity.project;

import lombok.Data;

import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/09/04 11:44
 */
@Data
public class ProjectMemberApply {
    /**
     * 申请id
     */
    int projectMemberApplyId;
    /**
     * 项目id
     */
    int projectId;
    /**
     * 申请的用户id
     */
    int applyUserId;
    /**
     * 申请状态
     */
    int applyStatus;
    /**
     * 审批意见
     */
    int applyComment;
    /**
     * 更新时间
     */
    Date updateTime;
}
