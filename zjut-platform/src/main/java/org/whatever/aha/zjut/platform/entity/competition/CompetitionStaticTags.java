package org.whatever.aha.zjut.platform.entity.competition;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/15 18:20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CompetitionStaticTags {
    /**
     * 竞赛类型
     */
    @NotNull(message = "外键 竞赛type不能为空")
    int compType;
    /**
     * 竞赛组别
     */
    @NotNull(message = "竞赛组别不能为空")
    String compGroup;
    /**
     * 竞赛标签
     */
    String compTag;
}
