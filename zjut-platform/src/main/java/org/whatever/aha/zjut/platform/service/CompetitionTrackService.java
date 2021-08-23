package org.whatever.aha.zjut.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.entity.CompetitionStaticTags;
import org.whatever.aha.zjut.platform.entity.CompetitionTrack;
import org.whatever.aha.zjut.platform.mapper.CompetitionTrackMapper;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/24 1:18
 */
@Service
@RequiredArgsConstructor
public class CompetitionTrackService {
    final CompetitionTrackMapper competitionTrackMapper;

    /**
     * 创建新的静态比赛映射信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addTrack(int compId, String trackName, String trackDesc){
        return competitionTrackMapper.insert(new CompetitionTrack(compId,null,trackName,trackDesc));
    }

    /**
     * 删除一条tag信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int delTrack(int compId,String trackName){
        return competitionTrackMapper.delete(new QueryWrapper<CompetitionTrack>().eq("comp_id", compId).eq("track_name",trackName));
    }
}
