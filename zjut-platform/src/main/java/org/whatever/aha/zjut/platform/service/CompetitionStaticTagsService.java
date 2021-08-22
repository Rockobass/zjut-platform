package org.whatever.aha.zjut.platform.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;
import org.whatever.aha.zjut.platform.entity.CompetitionStaticTags;
import org.whatever.aha.zjut.platform.mapper.CompetitionMapper;


import org.whatever.aha.zjut.platform.mapper.CompetitionStaticTagsMapper;

import java.util.List;

/**org.springframework.cache.annotation.EnableCaching
 * @author Vc
 * @version 1.0
 * @Desc
 * @date 2021/08/15 18:24
 */
@Service
@RequiredArgsConstructor
public class CompetitionStaticTagsService {
    final CompetitionStaticTagsMapper competitionStaticTagsMapper;

    /**
     * 创建新的静态比赛映射信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int addCompInfo(int compType, String compGroup, String compTag){
        return competitionStaticTagsMapper.insert(new CompetitionStaticTags(compType,compGroup,compTag));
    }

    /**
     * 删除一条tag信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int delTag(int compType, String compGroup, String compTag){
        return competitionStaticTagsMapper.delete(new QueryWrapper<CompetitionStaticTags>().eq("comp_type", compType).eq("comp_group",compGroup).eq("comp_tag",compTag));
    }

    /**
     * 删除compGroup对应的所有Tag信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int delTagByGroup(int compType, String compGroup){
        return competitionStaticTagsMapper.delete(new QueryWrapper<CompetitionStaticTags>().eq("comp_type", compType).eq("comp_group",compGroup));
    }

    /**
     * 批量删除tag信息
     */
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public int delTagInBatch(int compType, String compGroup, String[] compTagList){
        return competitionStaticTagsMapper.deleteTagInBatch(compType,compGroup,compTagList);
    }

    /**
     * 根据比赛类型获取所有group信息
     */
    @Cacheable(value = "NoExpire", key = "'comp_group_list_'+#compType")
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public List<String> getGroupByType(int compType){
        return competitionStaticTagsMapper.getGroupsByType(compType);
    }

    /**
     * 根据比赛类型、group获取所有tag信息
     */
    @Cacheable(value = "NoExpire", key = "'comp_tag_list_'+#compType+'_'+#compGroup")
    @Transactional(rollbackFor = Exception.class, propagation= Propagation.REQUIRED)
    public List<Object> getTags(int compType, String compGroup){
        return competitionStaticTagsMapper.selectObjs(new QueryWrapper<CompetitionStaticTags>().select("comp_tag").eq("comp_type",compType).eq("comp_group",compGroup));
    }

}
