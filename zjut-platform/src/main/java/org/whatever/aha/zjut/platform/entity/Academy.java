package org.whatever.aha.zjut.platform.entity;


import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;

/**
 * 学院实体类
 *
 * @author Baby_mo
 */
@Data
public class Academy {
    /**
     * 学院id
     */
    @TableId(type = IdType.AUTO)
    int academyId;
    /**
     * 学院名
     */
    String academyName;
}
