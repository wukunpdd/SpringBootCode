package ${package}.dao;
<#assign foo="#" />

import ${package}.domain.${class};
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

public interface ${class}Mapper {
    List<${class}> get${class}List();

    <#list properties as prop>
    <#if prop.flag=="1">
    @Select("select * from ${tableName} where ${prop.cname} = ${foo}{${prop.name}}")
    ${class} get${class}By${prop.checkName}(@Param("${prop.name}") ${prop.type} ${prop.cname});
    </#if>
    </#list>
}