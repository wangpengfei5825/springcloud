package com.generate;

import com.baomidou.mybatisplus.core.exceptions.MybatisPlusException;
import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Generate {

    /**
     * <p>
     * 读取控制台内容
     * </p>
     */
    public static String scanner(String tip) {
        Scanner scanner = new Scanner(System.in);
        StringBuilder help = new StringBuilder();
        help.append("请输入" + tip + "：");
        System.out.println(help.toString());
        if (scanner.hasNext()) {
            String ipt = scanner.next();
            if (StringUtils.isNotEmpty(ipt)) {
                return ipt;
            }
        }
        throw new MybatisPlusException("请输入正确的" + tip + "！");
    }

    public static void main(String[] args) {
        // 代码生成器
        AutoGenerator mpg = new AutoGenerator();

        // 全局配置
        GlobalConfig gc = new GlobalConfig();
        String projectPath = System.getProperty("user.dir");//获取项目路径
        gc.setOutputDir(projectPath+"/springcloud-gen/src/main/java");//生成文件的输出目录
        gc.setAuthor("me");//开发人员
        gc.setOpen(false);//是否打开输出目录
        gc.setFileOverride(true);//第二次生成会把第一次生成的覆盖掉
        gc.setSwagger2(true); //开启swagger2模式;实体属性 Swagger2 注解
        mpg.setGlobalConfig(gc);

        // 数据源配置
        DataSourceConfig dsc = new DataSourceConfig();
        dsc.setUrl("jdbc:mysql://47.96.181.121:3306/zy_bus_db?useUnicode=true&useSSL=false&characterEncoding=utf8&serverTimezone=UTC");
        // dsc.setSchemaName("public");
        dsc.setDriverName("com.mysql.cj.jdbc.Driver");
        dsc.setUsername("root");
        dsc.setPassword("vchuang666");
        mpg.setDataSource(dsc);

        // 包配置
        PackageConfig pc = new PackageConfig();
        pc.setModuleName(scanner("模块名"));//父包模块名
        pc.setParent("com.generate");//父包名。如果为空，将下面子包名必须写全部， 否则就只需写子包名
        //自定义路径;不设置会默认在父包下创建默认包名
        String str = scanner("子模块名");
        pc.setEntity("entity."+str);//Entity包名
        pc.setService("service."+str);//Service包名
        pc.setServiceImpl("service.serviceImpl."+str);//ServiceImpl包名
        pc.setController("controller."+str);//Controller包名
        pc.setMapper("dao."+str);//dao层包名
        pc.setXml("mapper");//Mapper xml包名
        mpg.setPackageInfo(pc);

        // 自定义配置
        InjectionConfig cfg = new InjectionConfig() {
            //注入自定义 Map 对象(注意需要setMap放进去)
            @Override
            public void initMap() {
                // to do nothing
            }
        };

        // 如果模板引擎是 freemarker
        String templatePath = "/templates/mapper.xml.ftl";
        // 如果模板引擎是 velocity
        // String templatePath = "/templates/mapper.xml.vm";

        // 自定义输出配置
        List<FileOutConfig> focList = new ArrayList<>();
        // 自定义配置会被优先输出
        focList.add(new FileOutConfig(templatePath) {
            @Override
            public String outputFile(TableInfo tableInfo) {
                // 自定义输出文件名 ， 如果你 Entity 设置了前后缀、此处注意 xml 的名称会跟着发生变化！！
               /* return projectPath + "/springcloud-api/src/main/resources/mapper/" + pc.getModuleName()
                + "/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;*/
                return projectPath + "/springcloud-gen/src/main/resources/mapper/"+str+"/" + tableInfo.getEntityName() + "Mapper" + StringPool.DOT_XML;
            }
        });
        /*
        cfg.setFileCreate(new IFileCreate() {
            @Override
            public boolean isCreate(ConfigBuilder configBuilder, FileType fileType, String filePath) {
                // 判断自定义文件夹是否需要创建
                checkDir("调用默认方法创建的目录");
                return false;
            }
        });
        */
        cfg.setFileOutConfigList(focList);//自定义输出文件;配置 FileOutConfig 指定模板文件、输出文件达到自定义文件生成目的
        mpg.setCfg(cfg);

        // 配置模板
        TemplateConfig templateConfig = new TemplateConfig();//模板配置，可自定义代码生成的模板，实现个性化操作

        // 配置自定义输出模板
        //指定自定义模板路径，注意不要带上.ftl/.vm, 会根据使用的模板引擎自动识别
        // templateConfig.setEntity("templates/entity2.java");
        // templateConfig.setService();
        // templateConfig.setController();

        templateConfig.setXml(null);
        mpg.setTemplate(templateConfig);

        // 策略配置
        StrategyConfig strategy = new StrategyConfig();
        strategy.setNaming(NamingStrategy.underline_to_camel);//数据库表映射到实体的命名策略;(NamingStrategy.underline_to_camel驼峰命名;NamingStrategy.no_change不改变)
        strategy.setColumnNaming(NamingStrategy.underline_to_camel);//数据库表字段映射到实体的注解命名策略, 未指定按照 naming 执行(注解式映射;比如:@Table("xxx"))
        //strategy.setSuperEntityClass("com.baomidou.mybatisplus.extension.activerecord.Model");//自定义继承的Entity类全称，带包名
        strategy.setEntityLombokModel(true);//【实体】是否为lombok模型（默认 false）
        strategy.setRestControllerStyle(true);//生成 @RestController 控制器
        //strategy.setSuperControllerClass("com.zhaoyu.web.springcloud.api.base.AppBaseController");//自定义继承的Controller类全称，带包名
        strategy.setInclude(scanner("表名，多个英文逗号分割").split(","));//需要包含的表名，允许正则表达式（与exclude二选一配置）
        //strategy.setSuperEntityColumns("id");//自定义基础的Entity类，公共字段
        strategy.setControllerMappingHyphenStyle(true);//驼峰转连字符
        strategy.setTablePrefix(pc.getModuleName() + "_");//表前缀

        //装配上面配置并执行
        mpg.setStrategy(strategy);//数据库表配置，通过该配置，可指定需要生成哪些表或者排除哪些表
        mpg.setTemplateEngine(new FreemarkerTemplateEngine());
        mpg.execute();
    }

}
