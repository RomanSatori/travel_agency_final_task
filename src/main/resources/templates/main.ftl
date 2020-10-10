<#import "parts/common.ftl" as c>
<#import "/spring.ftl" as spring>

<@c.page>
    <div class="form-row">
        <div class="form-group col-md-6">
            <form method="get" action="/main" class="form-inline">
                <input type="text" name="filter" class="form-control" value="${filter?ifExists}" placeholder="Search by tag">
                <button type="submit" class="btn btn-primary ml-2"><@spring.message "main.Search"/></button>
            </form>
        </div>
    </div>

    <#include "parts/tourEdit.ftl" />

    <#include "parts/tourList.ftl" />

</@c.page>