<#include "security.ftlh" />

<div class="card-columns" id="quote-list">
    <#list quotes as quote>
        <div class="card text-center m-3" data-id="${quote.id}">
            <#if quote.filename??>
                <img src="/img/${quote.filename}" class="card-img-top p-2"/>
            </#if>
            <blockquote class="blockquote p-3">
                <p>${quote.text}</p>
                <footer class="blockquote-footer">
                    <small>by <a href="/user-quotes/${quote.author.id}">${quote.authorName}</a></small>
                    <#if quote.author.id == currentUserId>
                        <a class="btn btn-primary" href="/user-quotes/${quote.author.id}?quote=${quote.id}">Edit</a>
                    </#if>
                </footer>
            </blockquote>
            <div class="card-footer text-left p-0">
                <small class="text-muted ml-3">#${quote.tag}</small>
            </div>
        </div>		        
    <#else>
        <p>There are no quotes.</p>
    </#list>
</div>
