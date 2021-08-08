package org.whatever.aha.zjut.platform.entity;


import lombok.Data;

/**
 * 专业实体类
 *
 * @author Baby_mo
 */
@Data
public class Major {
    /**
     * 专业id
     */
    Integer majorId;
    /**
     * 学院id
     */
    Integer academyId;
    /**
     * 专业名
     */
    String majorName;
}
