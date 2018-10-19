package ${package}.service;

import ${package}.domain.${class};

import java.util.List;

public interface ${class}Service {
    List<${class}> get${class}List();

    <#list properties as prop>
    <#if prop.flag=="1">
    ${class} get${class}By${prop.checkName}(${prop.type} ${prop.name});
    </#if>
    </#list>
}