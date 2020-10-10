<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring>
<#include "parts/security.ftl">
<@c.page>
    <#if isAdmin>
        <#include "parts/tourEdit.ftl"/>
    </#if>
    <#include "parts/tourList.ftl"/>
</@c.page>