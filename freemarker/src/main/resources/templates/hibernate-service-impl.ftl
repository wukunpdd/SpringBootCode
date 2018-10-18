package ${package}.service.impl;
<#assign classLower = class?uncap_first>

import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import ${package}.domain.${class};
import ${package}.service.${class}Service;
import ${package}.dao.${class}Dao;

@Service
public class ${class}ServiceImpl implements ${class}Service{

    @Autowired
    private ${class}Dao ${classLower}Dao;

    public List<${class}> get${class}List(){
        return ${classLower}Dao.findAll();
    }

    public Page<${class}> getPage${class}(int pageNo, int pageSize){
        Pageable pageable = new PageRequest(pageNo, pageSize);
        return ${classLower}Dao.findAll(pageable);
    }

    public ${class} insert${class}(${class} ${classLower}){
        return ${classLower}Dao.saveAndFlush(${classLower});
    }

    public ${class} update${class}(${class} ${classLower}){
        return ${classLower}Dao.saveAndFlush(${classLower});
    }
}