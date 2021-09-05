package org.whatever.aha.zjut.platform.entity.project;

import lombok.Data;

import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/09/05 9:37
 */
@Data
public class ProjectScore {
    /**
     * 项目id
     */
    int projectId;
    /**
     * 阶段id
     */
    int stageId;
    /**
     * 分数
     */
    double score;
    /**
     * 排名
     */
    int rank;
    /**
     * 评委id
     */
    int judgeId;
    /**
     * 评委的名字
     */
    String judgeName;
    /**
     * 评委评论
     */
    String judgeComment;
    /**
     * 更新时间
     */
    Date updateTime;
}
