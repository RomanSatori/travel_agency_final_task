<#include "security.ftl">
<#import "/spring.ftl" as spring>
<div class="card-columns">
    <#list messages as message>
        <div class="card my-3">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${message.text}</span><br/>
                <i>#${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/user-messages/${message.author.id}">${message.authorName}</a>
                    <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">
                        <@spring.message "messageList.Edit"/>
                    </a>
            </div>
        </div>
    <#else>
        <@spring.message "messageList.noMessage"/>
    </#list>
</div>