package ${package}.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
  
@Entity
@Table(name="${tableName}")
<#list properties as prop>
<#if prop.flag=="1">
public class ${class} implements IEntity<${prop.type}>{
</#if>
</#list> 
	private static final long serialVersionUID = 1L;
	 
  <#list properties as prop>
  	<#if prop.flag=="1">
  	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="${prop.cname}",length=${prop.length})  
    private ${prop.type} ${prop.name};
    <#else>
    @Column(name="${prop.cname}",length=${prop.length})  
    private ${prop.type} ${prop.name};
    </#if>
      
  </#list>  
  <#list properties as prop>  
    public ${prop.type} get${prop.name?cap_first}(){  
      return ${prop.name};  
    }
      
    public void set${prop.name?cap_first}(${prop.type} ${prop.name}){  
      this.${prop.name} = ${prop.name};  
    }
      
  </#list>   
}