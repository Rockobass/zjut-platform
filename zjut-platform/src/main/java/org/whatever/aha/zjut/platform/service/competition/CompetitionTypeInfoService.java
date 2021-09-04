package org.whatever.aha.zjut.platform.service.competition;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.platform.entity.competition.CompetitionTypeInfo;
import org.whatever.aha.zjut.platform.mapper.competition.CompetitionTypeInfoMapper;

import java.util.List;

/**
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/09/04 15:04
 */
@Service
@RequiredArgsConstructor
public class CompetitionTypeInfoService {
    final CompetitionTypeInfoMapper competitionTypeInfoMapper;

    public List<CompetitionTypeInfo> getAll(){
        return competitionTypeInfoMapper.selectList(new QueryWrapper<CompetitionTypeInfo>());
    }
}
