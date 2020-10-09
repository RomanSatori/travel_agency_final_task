<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>
<#import "/spring.ftl" as spring>

<@c.page>
    <div class="mb-1"><@spring.message "registration.add-new-user"/></div>
    ${message?ifExists}
    <@l.login "/registration" true />
</@c.page>