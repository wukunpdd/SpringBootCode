<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package}.dao.${class}Mapper">
    <resultMap id="BaseResultMap" type="${package}.domain.${class}">

        <#list properties as prop>
        <#if prop.flag=="1">
        <id column="${prop.cname}" jdbcType="${prop.jdbcType}" property="${prop.name}" />
        </#if>
        </#list>

        <#list properties as prop>
        <#if prop.flag=="0">
        <result column="${prop.cname}" jdbcType="${prop.jdbcType}" property="${prop.name}" />
        </#if>
        </#list>
    </resultMap>

    <select id="get${class}List" resultType="${class}">
        select * from ${tableName}
    </select>
</mapper>