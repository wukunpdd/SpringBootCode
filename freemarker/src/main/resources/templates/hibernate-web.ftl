package ${package}.web;
<#assign classLower = class?uncap_first>

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import ${package}.domain.${class};
import ${package}.service.${class}Service;

@RestController
@RequestMapping("/hibernate/${classLower}")
public class ${class}Controller{

    @Autowired
    private ${class}Service ${classLower}Service;

    @RequestMapping("/get${class}List")
    public List<${class}> get${class}List(){
        return ${classLower}Service.get${class}List();
    }

    @RequestMapping("/getPage${class}")
    public Page<${class}> getPage${class}(int pageNo, int pageSize){
        return ${classLower}Service.getPage${class}(pageNo, pageSize);
    }

    @RequestMapping("/insert${class}")
    public ${class} insert${class}(${class} ${classLower}){
        return ${classLower}Service.insert${class}(${classLower});
    }

    @RequestMapping("/update${class}")
    public ${class} update${class}(${class} ${classLower}){
        return ${classLower}Service.update${class}(${classLower});
    }
}