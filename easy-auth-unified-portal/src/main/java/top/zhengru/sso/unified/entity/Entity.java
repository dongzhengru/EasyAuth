package top.zhengru.sso.unified.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;

/**
 * MybatisPlus基础持久化基类
 */
public class Entity {

    @TableId(type = IdType.ASSIGN_ID)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}