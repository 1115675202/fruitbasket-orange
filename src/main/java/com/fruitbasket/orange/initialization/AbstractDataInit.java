package com.fruitbasket.orange.initialization;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.io.ClassPathResource;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

/**
 * 系统配置、字典、菜单等基础数据初始化
 *
 * @author LiuBing
 * @date 2021/4/19
 */
public abstract class AbstractDataInit implements ApplicationRunner {

    @Resource
    protected ObjectMapper objectMapper;

    /**
     * 保存了 json 文件中的数据
     */
    protected JsonNode dataNode;

    /**
     * @return true-执行 finalRun()，false-不执行
     */
    protected abstract boolean support();

    /**
     * @return /resources 下的 json 文件路径，如：/initialization/data.base.json
     */
    protected abstract String jsonFilePath();

    /**
     * 最终执行的逻辑
     *
     * @param args -
     */
    protected abstract void finalRun(ApplicationArguments args);

    /**
     * 从加载的数据dataNode中获取数据
     *
     * @param fieldName -
     * @param clazz     -
     * @param <T>       -
     * @return -
     */
    protected <T> Set<T> getObjects(String fieldName, Class<T> clazz) {
        Set<T> set = new HashSet<>();
        try {
            for (JsonNode node : dataNode.get(fieldName))
                set.add(objectMapper.treeToValue(node, clazz));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
        return set;
    }

    /**
     * 子类公用的模板方法，也是spring启动执行的方法
     *
     * @param args -
     * @throws Exception -
     */
    @Override
    public final void run(ApplicationArguments args) throws Exception {
        if (!support()) return;
        initDataNode(jsonFilePath());
        finalRun(args);
    }

    /**
     * 读取 /resources 下的 json 文件内容到 dataNode
     *
     * @param jsonFilePath json 文件路径，如：/initialization/data.base.json
     * @throws IOException -
     */
    private void initDataNode(String jsonFilePath) throws IOException {
        ClassPathResource classPathResource = new ClassPathResource(jsonFilePath);
        this.dataNode = objectMapper.readTree(classPathResource.getInputStream());
    }

    protected AbstractDataInit() {
    }
}
