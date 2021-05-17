package cn.fruitbasket.orange.module.sys.pojo.vo;

import cn.fruitbasket.orange.module.common.vo.BaseDataVO;
import cn.fruitbasket.orange.module.sys.pojo.entity.SysConfig;
import cn.hutool.core.bean.BeanUtil;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 普通配置
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Data
@Accessors(chain = true)
public class ConfigVO extends BaseDataVO<ConfigVO> {

    /**
     * 配置键
     */
    private String configKey;

    /**
     * 配置值
     */
    private String configValue;

    /**
     * 备注
     */
    private String description;

    public static ConfigVO of(SysConfig sysConfig) {
        return BeanUtil.copyProperties(sysConfig, ConfigVO.class);
    }
}
