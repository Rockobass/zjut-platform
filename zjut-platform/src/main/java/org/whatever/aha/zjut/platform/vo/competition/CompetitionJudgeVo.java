package org.whatever.aha.zjut.platform.vo.competition;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/27 0:12
 */
@Data
public class CompetitionJudgeVo {
    /**
     * 赛事 id
     */
    int compId;
    /**
     * 赛事类型
     */
    int compType;
    /**
     * 赛事届数
     */
    int compTh;
    /**
     * 赛事信息
     */
    String compInfo;
    /**
     * 赛事状态
     */
    int compStatus;

    /**
     * 竞赛关键的时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date compKeyTime;


}
