package org.whatever.aha.zjut.platform.entity.project;

import lombok.Data;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/09/04 13:00
 */
@Data
public class ProjectResource {
    /**
     * 项目资源id
     */
    int projectResourceId;
    /**
     * 项目id
     */
    int projectId;
    /**
     * 竞赛阶段
     */
    int compStage;
    /**
     * 项目资源名称
     */
    String projectResourceName;
    /**
     * cospath
     */
    String cosPath;
}
