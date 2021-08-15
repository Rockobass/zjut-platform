package org.whatever.aha.zjut.platform.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.NotNull;
import java.sql.Timestamp;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/14 10:53
 */
@Data
@AllArgsConstructor
public class Competition {
    /**
     * 赛事id
     */
    @TableId(type = IdType.AUTO)
    Integer compId;
    /**
     *   赛事类型(1:运河杯立项 2:结题 3:竞赛 4:新苗立项 5:挑战杯专项赛)
     */
    @NotNull(message = "赛事类型不能为空")
    int compType;
    /**
     *  举办年份
     */
    @NotNull(message = "举办年份不能为空")
    int compYear;
    /**
     *  校团委推荐数量
     */
    @NotNull(message = "校团委推荐数量不能为空")
    int compSchoolRecAmt;
    /**
     *   赛事简介
     */
    String compInfo;
    /**
     *  评委评分方式（0:打分/1:排序）
     */
    @NotNull(message = "评委评分方式不能为空")
    Boolean compJudgeWay;
    /**
     * 校级管理员指定评判标准
     */
    String compJudgeStandard;

}
