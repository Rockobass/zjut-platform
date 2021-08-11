package org.whatever.aha.zjut.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.whatever.aha.zjut.platform.entity.Academy;
import org.whatever.aha.zjut.platform.entity.Major;
import org.whatever.aha.zjut.platform.mapper.AcademyMapper;
import org.whatever.aha.zjut.platform.mapper.MajorMapper;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommonService {
    final AcademyMapper academyMapper;
    final MajorMapper majorMapper;

    @Cacheable(value = "NoExpire", key = "'academy_list'")
    public List<Academy> getAcademyList() {
        return academyMapper.selectList(null);
    }

    @Cacheable(value = "NoExpire", key = "'major_list_'+#academyId")
    public List<Major> getMajorList(int academyId) {
        return majorMapper.selectList(new QueryWrapper<Major>().eq("academy_id", academyId));
    }
}
