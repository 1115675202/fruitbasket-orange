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
        DocsConfig config = new DocsConfig();
        // 项目根目录
        config.setProjectPath("/Users/liubing/devData/ideaProject/fruitbasket-orange");
        // 项目名称
        config.setProjectName("orange");
        // 声明该API的版本
        config.setApiVersion("V1.0");
        // 生成API 文档所在目录
        config.setDocsPath("/Users/liubing/Downloads/orange");
        // 配置自动生成
        config.setAutoGenerate(Boolean.TRUE);
        // 执行生成文档
        Docs.buildHtmlDocs(config);
    }
}
