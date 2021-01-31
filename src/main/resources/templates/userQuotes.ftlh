<#import "parts/common.ftlh" as c>

<@c.page>

    <#if isCurrentUser>
        <#include "parts/quoteEdit.ftlh" />
    </#if>
    
    <#include "parts/quoteList.ftlh" />

</@c.page>
