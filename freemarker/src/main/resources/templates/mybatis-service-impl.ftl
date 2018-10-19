package ${package}.service.impl;
<#assign classLower = class?uncap_first>

import ${package}.dao.UserScoreMapper;
import ${package}.domain.UserScore;
import ${package}.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ${class}ServiceImpl implements ${class}Service {

    @Autowired
    private ${class}Mapper ${classLower}Mapper;

    public List<${class}> get${class}List() {
        return ${classLower}Mapper.get${class}List();
    }

    <#list properties as prop>
    <#if prop.flag=="1">
    public ${class} get${class}By${prop.checkName}(${prop.type} ${prop.name}) {
        return ${classLower}Mapper.get${class}By${prop.checkName}(${prop.name});
    }
    </#if>
    </#list>
}