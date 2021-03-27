package com.fruitbasket.orange.module.sys.repository;

import com.fruitbasket.orange.module.sys.pojo.entity.SysConfig;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 配置
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface SysConfigRep extends JpaRepository<SysConfig, Integer> {
}
