package ${package}.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import ${package}.entity.${class};

<#list properties as prop>
<#if prop.flag=="1">
public interface ${class}Dao extends JpaRepository<${class},${prop.type}>{
</#if>
</#list>

}