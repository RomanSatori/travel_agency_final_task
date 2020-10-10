<#include "security.ftl">
<#import "/spring.ftl" as spring>
<#import "pager.ftl" as p>

<@p.pager url page />

<div class="card-columns" id="message-list">
    <#list page.content as message>
        <div class="card my-3" data-id="${message.id}">
            <#if message.filename??>
                <img src="/img/${message.filename}" class="card-img-top">
            </#if>
            <div class="m-2">
                <span>${message.text}</span><br/>
                <i>#${message.tag}</i>
            </div>
            <div class="card-footer text-muted">
                <a href="/user-messages/${message.author.id}">${message.authorName}</a>
                <#if isAdmin>
                    <a class="btn btn-primary" href="/user-messages/${message.author.id}?message=${message.id}">
                        <@spring.message "messageList.Edit"/>
                    </a>
                </#if>
                <a class="btn btn-primary" href="buyTour.ftl">
                    <@spring.message "messageList.buyTour"/>
                </a>
            </div>
        </div>
    <#else>
        <@spring.message "messageList.noMessage"/>
    </#list>
</div>