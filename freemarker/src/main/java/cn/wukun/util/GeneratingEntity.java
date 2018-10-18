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

    private List<Table> tableList;

    private static Configuration configuration;
    private static Template template;
    private static Writer writer;

    //生成实体类的方法
    public void generatingEntity(
            String driverClass,String dbUrl,String username,String password,String tableName,String generatingAddress,String packageName) throws Exception{
        //获取表格的相关信息
        GeneratingTable generatingTable = new GeneratingTable();

        Version incompatibleImprovements=new Version("2.3.21");
        configuration = new Configuration(incompatibleImprovements);
        configuration.setDirectoryForTemplateLoading(new File("D:/github/my-freemarker/src/main/resources/templates"));

        template = configuration.getTemplate("entity.ftl");

        generatingTable.initConnection(driverClass,dbUrl,username,password);
        ResultSetMetaData resultSetMetaData=generatingTable.getMetaDataFromTable(tableName);
        tableList=generatingTable.displayMetaData(resultSetMetaData);

        Map<String,Object> root=new HashMap<String,Object>();
        root.put("class",tableList.get(0).getName());
        root.put("tableName",tableList.get(0).getCname());
        root.put("package",packageName);
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
            properties.add(currency);
        }

        String beanPath = generatingAddress+"/"+packageName+"/entity";
        File filePath = new File(beanPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        String filePathOfBean = beanPath + "/"+tableList.get(0).getName()+".java";
        File file = new File(filePathOfBean);
        if (!file.exists()) {
            file.createNewFile();
        }

        // 显示生成的数据
        writer = new FileWriter(file);
        template.process(root, writer);
        writer.flush();
        writer.close();
    }

    //生成dao层格式化代码，都是继承的JpaRepository
    public void generatingDao(
            String driverClass,String dbUrl,String username,String password,String tableName,String generatingAddress,String packageName) throws Exception{
        //获取表格的相关信息
        GeneratingTable generatingTable = new GeneratingTable();

        Version incompatibleImprovements = new Version("2.3.21");
        configuration = new Configuration(incompatibleImprovements);
        configuration.setDirectoryForTemplateLoading(new File("D:/github/my-freemarker/src/main/resources/templates"));

        template = configuration.getTemplate("dao.ftl");

        generatingTable.initConnection(driverClass,dbUrl,username,password);
        ResultSetMetaData resultSetMetaData=generatingTable.getMetaDataFromTable(tableName);
        tableList=generatingTable.displayMetaData(resultSetMetaData);

        Map<String,Object> root=new HashMap<String,Object>();
        root.put("class",tableList.get(0).getName());
        root.put("package",packageName);
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
            currency.put("type",generatingTable.handleField(tableList.get(i).getType()));
            properties.add(currency);
        }

        String beanPath = generatingAddress+"/"+packageName+"/dao";
        File filePath = new File(beanPath);
        if (!filePath.exists()) {
            filePath.mkdirs();
        }

        String filePathOfBean = beanPath + "/"+tableList.get(0).getName()+"Dao.java";
        File file = new File(filePathOfBean);
        if (!file.exists()) {
            file.createNewFile();
        }

        // 显示生成的数据
        writer = new FileWriter(file);
        template.process(root, writer);
        writer.flush();
        writer.close();
    }
}
