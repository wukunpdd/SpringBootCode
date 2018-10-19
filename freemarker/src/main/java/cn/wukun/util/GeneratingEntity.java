package cn.wukun.util;

import java.io.File;
import java.io.FileWriter;
import java.io.Writer;
import java.sql.ResultSetMetaData;
import java.util.*;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.Version;

/**
 * Java各种代码的生成类
 */
public class GeneratingEntity {

    private static Configuration configuration;
    private static Template template;
    private static Writer writer;

    /**
     * Hibernate的实体类生成
     * @param applicationInfo
     * @param tableName
     * @throws Exception
     */
    public void generateHibernateDomain(ApplicationInfo applicationInfo, String tableName) throws Exception{
        generate(applicationInfo, tableName, "hibernate-domain");
    }

    /**
     * Hibernate的Dao层代码生成
     * @param applicationInfo
     * @param tableName
     * @throws Exception
     */
    public void generateHibernateDao(ApplicationInfo applicationInfo, String tableName) throws Exception{
        generate(applicationInfo, tableName, "hibernate-dao");
    }

    /**
     * Hibernate的Service层代码生成
     * @param applicationInfo
     * @param tableName
     * @throws Exception
     */
    public void generateHibernateService(ApplicationInfo applicationInfo, String tableName) throws Exception{
        generate(applicationInfo, tableName, "hibernate-service");
    }

    /**
     * Hibernate的Service实现层代码生成
     * @param applicationInfo
     * @param tableName
     * @throws Exception
     */
    public void generateHibernateServiceImpl(ApplicationInfo applicationInfo, String tableName) throws Exception{
        generate(applicationInfo, tableName, "hibernate-service-impl");
    }

    /**
     * Hibernate的web层代码生成
     * @param applicationInfo
     * @param tableName
     * @throws Exception
     */
    public void generateHibernateWeb(ApplicationInfo applicationInfo, String tableName) throws Exception{
        generate(applicationInfo, tableName, "hibernate-web");
    }

    /**
     * MyBatis的实体类生成
     * @param applicationInfo
     * @param tableName
     * @throws Exception
     */
    public void generateMyBatisDomain(ApplicationInfo applicationInfo, String tableName) throws Exception{
        generate(applicationInfo, tableName, "mybatis-domain");
    }

    /**
     * MyBatis的*Mapper.xml文件生成
     * @param applicationInfo
     * @param tableName
     * @throws Exception
     */
    public void generateMyBatisMapper(ApplicationInfo applicationInfo, String tableName) throws Exception{
        generate(applicationInfo, tableName, "mybatis-mapper");
    }

    /**
     * MyBatis的Dao层文件生成
     * @param applicationInfo
     * @param tableName
     * @throws Exception
     */
    public void generateMyBatisDao(ApplicationInfo applicationInfo, String tableName) throws Exception{
        generate(applicationInfo, tableName, "mybatis-dao");
    }

    /**
     * MyBatis的Service层接口
     * @param applicationInfo
     * @param tableName
     * @throws Exception
     */
    public void generateMyBatisService(ApplicationInfo applicationInfo, String tableName) throws Exception{
        generate(applicationInfo, tableName, "mybatis-service");
    }

    /**
     * MyBatis的Service层接口实现
     * @param applicationInfo
     * @param tableName
     * @throws Exception
     */
    public void generateMyBatisServiceImpl(ApplicationInfo applicationInfo, String tableName) throws Exception{
        generate(applicationInfo, tableName, "mybatis-service-impl");
    }

    /**
     * MyBatis的Web层
     * @param applicationInfo
     * @param tableName
     * @throws Exception
     */
    public void generateMyBatisWeb(ApplicationInfo applicationInfo, String tableName) throws Exception{
        generate(applicationInfo, tableName, "mybatis-web");
    }

    public static void generate(ApplicationInfo applicationInfo, String tableName, String type) throws Exception{
        //模板名称和包名的确立
        String tempalteName = type + ".ftl";
        String packageName = applicationInfo.getPackageName() + ".hibernate";
        if(type.indexOf("mybatis") != -1){
            packageName = applicationInfo.getPackageName() + ".mybatis";
        }

        //实体类存放地址确定
        String beanPath = applicationInfo.getGeneratingAddress()+"/"+applicationInfo.getPackageName();
        if(applicationInfo.getPackageName().indexOf(".") != -1){
            String[] names = applicationInfo.getPackageName().split("\\.");
            beanPath = applicationInfo.getGeneratingAddress();
            for(String name:names){
                beanPath += "/" + name;
            }
        }

        if(type.indexOf("mybatis") != -1){
            beanPath += "/mybatis";
        }else if(type.indexOf("hibernate") != -1){
            beanPath += "/hibernate";
        }

        if(type.indexOf("domain") != -1 || type.indexOf("mapper") != -1){
            beanPath += "/domain";
        }else if(type.indexOf("dao") != -1){
            beanPath += "/dao";
        }else if(type.indexOf("service") != -1 && type.indexOf("service-impl") == -1){
            beanPath += "/service";
        }else if(type.indexOf("service-impl") != -1){
            beanPath += "/service/impl";
        }else if(type.indexOf("web") != -1){
            beanPath += "/web";
        }

        //获取表格的相关信息
        GeneratingTable generatingTable = new GeneratingTable();

        Version incompatibleImprovements = new Version("2.3.21");
        configuration = new Configuration(incompatibleImprovements);
        configuration.setDirectoryForTemplateLoading(new File(applicationInfo.getTemplateAddress()));

        template = configuration.getTemplate(tempalteName);

        generatingTable.initConnection(applicationInfo.getDriverClass(), applicationInfo.getDbUrl(), applicationInfo.getUsername(), applicationInfo.getPassword());
        ResultSetMetaData resultSetMetaData=generatingTable.getMetaDataFromTable(tableName);
        List<Table> tableList=generatingTable.displayMetaData(resultSetMetaData);

        Map<String,Object> root=new HashMap<String,Object>();
        root.put("class",tableList.get(0).getName());
        root.put("tableName",tableList.get(0).getCname());
        root.put("package", packageName);
        Collection<Map<String, String>> properties = new HashSet<Map<String, String>>();
        root.put("properties",properties);

        for(int i=1;i<tableList.size();i++){
            Map<String, String> currency = new HashMap<String, String>();
            //主键 因为主键往往是第一个属性
            if(i==1){
                currency.put("flag","1");
            }else{
                currency.put("flag","0");
            }
            currency.put("name",tableList.get(i).getName());
            currency.put("cname",tableList.get(i).getCname());
            currency.put("length",tableList.get(i).getLength());
            currency.put("type",generatingTable.handleField(tableList.get(i).getType()));
            currency.put("jdbcType", generatingTable.getJdbcType(tableList.get(i).getJdbcType()));
            currency.put("checkName", tableList.get(i).getCheckName());
            properties.add(currency);
        }

        File filePath = new File(beanPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        //实体类名称确定
        String filePathOfBean = beanPath + "/"+tableList.get(0).getName();
        if(type.indexOf("domain") != -1){
            filePathOfBean += ".java";
        }else if(type.indexOf("dao") != -1){
            if(type.indexOf("hibernate") != -1){
                filePathOfBean += "Dao.java";
            }else if(type.indexOf("mybatis") != -1){
                filePathOfBean += "Mapper.java";
            }
        }else if(type.indexOf("service") != -1 && type.indexOf("service-impl") == -1){
            filePathOfBean += "Service.java";
        }else if(type.indexOf("service-impl") != -1){
            filePathOfBean += "ServiceImpl.java";
        }else if(type.indexOf("web") != -1){
            filePathOfBean += "Controller.java";
        }else if(type.indexOf("mapper") != -1){
            filePathOfBean += "Mapper.xml";
        }

        File file = new File(filePathOfBean);
        if (!file.exists()) {
            file.createNewFile();
        }

        writer = new FileWriter(file);
        template.process(root, writer);
        writer.flush();
        writer.close();
    }
}
