<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring>

<@c.page>
    <#if isCurrentUser>
        <#include "parts/messageEdit.ftl"/>
    </#if>
    <#include "parts/messageList.ftl"/>
</@c.page>