<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring>

<@c.page>
    <@spring.message "userList.listOfUser"/>

    <table>
        <thead>
        <tr>
            <th><@spring.message "userList.Name"/></th>
            <th><@spring.message "userList.Role"/></th>
            <th></th>
        </tr>
        </thead>
        <tbody>
        <#list users as user>
            <tr>
                <td>${user.username}</td>
                <td><#list user.roles as role>${role}<#sep>, </#list></td>
                <td><a href="/user/${user.id}"><@spring.message "userList.edit"/></a></td>
            </tr>
        </#list>
        </tbody>
    </table>
</@c.page>