package com.fruitbasket.orange;

import io.github.yedaxia.apidocs.Docs;
import io.github.yedaxia.apidocs.DocsConfig;

/**
 * JApiDocs 静态接口文档
 *
 * @author LiuBing
 * @date 2021/3/27
 */
public class JApiDocsGenerator {

    public static void main(String[] args) {
        String projectPath = System.getProperty("user.dir");
        DocsConfig config = new DocsConfig();
        // 项目根目录
        config.setProjectPath(projectPath);
        // 项目名称
        config.setProjectName("orange");
        // 声明该API的版本
        config.setApiVersion("");
        // 生成API 文档所在目录
        config.setDocsPath(projectPath + "/src/main/resources/static/api");
        // 配置自动生成
        config.setAutoGenerate(true);
        // 执行生成文档
        Docs.buildHtmlDocs(config);
    }
}
