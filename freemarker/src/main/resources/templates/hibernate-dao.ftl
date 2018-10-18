package ${package}.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

import ${package}.domain.${class};

<#list properties as prop>
<#if prop.flag=="1">
public interface ${class}Dao extends JpaRepository<${class},${prop.type}>{
</#if>
</#list>

<#list properties as prop>
<#if prop.flag=="0">
    List<${class}> findBy${prop.checkName}(${prop.type} ${prop.name});

    ${class} findOneBy${prop.checkName}(${prop.type} ${prop.name});

</#if>
</#list>
}