package ${package}.service;
<#assign classLower = class?uncap_first>

import java.util.List;
import org.springframework.data.domain.Page;

import ${package}.domain.${class};

public interface ${class}Service{
    List<${class}> get${class}List();

    Page<${class}> getPage${class}(int pageNo, int pageSize);

    ${class} insert${class}(${class} ${classLower});

    ${class} update${class}(${class} ${classLower});
}