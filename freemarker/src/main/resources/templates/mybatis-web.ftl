package ${package}.web;
<#assign classLower = class?uncap_first>

import ${package}.domain.UserScore;
import ${package}.service.UserScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/mybatis/${classLower}")
public class ${class}Controller {
    @Autowired
    private ${class}Service ${classLower}Service;

    @RequestMapping("/get${class}List")
    public List<${class}> get${class}List(){
        return ${classLower}Service.get${class}List();
    }

    <#list properties as prop>
    <#if prop.flag=="1">
    @RequestMapping("/get${class}ByUserId")
    public ${class} get${class}By${prop.checkName}(@RequestParam("${prop.name}") ${prop.type} ${prop.name}){
        return ${classLower}Service.get${class}By${prop.checkName}(userId);
    }
    </#if>
    </#list>
}