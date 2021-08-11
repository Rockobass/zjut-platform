package org.whatever.aha.zjut.platform.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
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
    @TableId(type = IdType.AUTO)
    int majorId;
    /**
     * 学院id
     */
    int academyId;
    /**
     * 专业名
     */
    String majorName;
}
