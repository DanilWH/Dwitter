<#macro pager url page>

    <ul class="pagination">
        <li class="page-item disabled"><a class="page-link" href="#" tabindex="-1">Number of the elements on the page</a></li>

        <#list [5, 10, 15, 20] as s> <!-- "s" stands for "size". -->
            <li class="page-item <#if s == page.getSize()>active</#if>">
                <a class="page-link" href="${url}?page=${page.getNumber()}&size=${s}" tabindex="-1">${s}</a>
            </li>
        </#list>
    </ul>

    <ul class="pagination">
        <li class="page-item">
            <a class="page-link" href="${url}?page=${page.getNumber() - 1}&size=${page.getSize()}" tabindex="-1">Previous</a>
        </li>

        <#list pagerSequence as p> <!-- "p" stands for "page". -->
            <#if p != -1>
                <li class="page-item <#if p == page.getNumber()>active</#if>">
                    <a class="page-link" href="${url}?page=${p}&size=${page.getSize()}" tabindex="-1">${p + 1}</a>
                </li>
            <#else>
                <li class="page-item disabled">
                    <a class="page-link" href="#" tabindex="-1">...</a>
                </li>
            </#if>
        </#list>

        <li class="page-item">
            <a class="page-link" href="${url}?page=${page.getNumber() + 1}&size=${page.getSize()}" tabindex="-1">Next</a>
        </li>
    </ul>

</#macro>