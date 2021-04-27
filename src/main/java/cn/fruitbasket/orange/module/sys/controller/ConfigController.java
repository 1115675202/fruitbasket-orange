package cn.fruitbasket.orange.module.sys.controller;

import cn.fruitbasket.orange.module.common.vo.PageVO;
import cn.fruitbasket.orange.module.sys.pojo.query.ConfigAddQuery;
import cn.fruitbasket.orange.module.sys.pojo.query.ConfigPageableQuery;
import cn.fruitbasket.orange.module.sys.pojo.query.ConfigUpdateQuery;
import cn.fruitbasket.orange.module.sys.pojo.vo.ConfigVO;
import cn.fruitbasket.orange.module.sys.service.ConfigService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.Set;

/**
 * 系统配置
 *
 * @author LiuBing
 * @date 2020/12/16
 */
@Validated
@RestController
@RequestMapping("config")
public class ConfigController {

    private final ConfigService configService;

    public ConfigController(ConfigService configService) {
        this.configService = configService;
    }

    /**
     * @return 一页信息
     */
    @GetMapping("multi")
    public PageVO<ConfigVO> listPageRoles(@Valid ConfigPageableQuery query) {
        return configService.listPageConfig(query);
    }

    /**
     * 添加一个配置
     *
     * @param query 配置信息
     * @return 生成的配置信息
     */
    @PostMapping
    public ConfigVO add(@RequestBody @Valid ConfigAddQuery query) {
        return configService.saveConfig(query);
    }

    /**
     * 根据配置 ID 删除多个配置
     *
     * @param configIds -
     * @return 删除数量
     */
    @DeleteMapping("multi")
    public long deleteConfigIdIn(@RequestBody @Valid @NotEmpty(message = "配置ID：不能为空") Set<Integer> configIds) {
        return configService.deleteConfigIdIn(configIds);
    }

    /**
     * 修改配置信息
     *
     * @param query 配置信息
     * @return 修改后的配置信息
     */
    @PutMapping
    public ConfigVO updateRole(@RequestBody @Valid ConfigUpdateQuery query) {
        return configService.updateConfig(query);
    }
}
