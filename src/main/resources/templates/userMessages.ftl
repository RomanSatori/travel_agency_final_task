<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring>
<#include "parts/security.ftl">
<@c.page>
    <#if isAdmin>
        <#include "parts/messageEdit.ftl"/>
    </#if>
    <#include "parts/messageList.ftl"/>
</@c.page>