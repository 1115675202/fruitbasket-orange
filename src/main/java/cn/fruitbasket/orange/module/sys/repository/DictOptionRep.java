package cn.fruitbasket.orange.module.sys.repository;

import cn.fruitbasket.orange.module.sys.pojo.entity.SysDictOption;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * 字典
 *
 * @author LiuBing
 * @date 2020/12/15
 */
public interface DictOptionRep extends JpaRepository<SysDictOption, Integer> {
}
