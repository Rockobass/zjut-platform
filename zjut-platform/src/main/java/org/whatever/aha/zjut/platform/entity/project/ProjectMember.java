package org.whatever.aha.zjut.platform.entity.project;

import lombok.Data;

import javax.validation.constraints.Min;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/09/04 11:34
 */
@Data
public class ProjectMember {
    /**
     * 项目id
     */
    int projectId;
    /**
     * 成员id
     */
    int memberId;
    /**
     * 项目成员排位
     */
    @Min(1)
    int projectMemberRank;
    /**
     * 项目成员职责
     */
    String projectMemberJob;
}
