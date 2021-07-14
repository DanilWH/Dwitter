<#include "security.ftlh" />
<#import "pager.ftlh" as pager />

<@pager.pager url page />

<div class="card-columns" id="quote-list">
    <#list page.content as quote>
        <div class="card text-center m-3" data-id="${quote.id}">
            <#if quote.filename??>
                <img src="/img/${quote.filename}" class="card-img-top p-2"/>
            </#if>
            <blockquote class="blockquote p-3">
                <p>${quote.text}</p>
                <footer class="blockquote-footer container">
                    <div class="row">
                        <small class="col align-self-center">by <a href="/user-quotes/${quote.author.id}">${quote.authorName}</a></small>
                        <a class="col align-self-center" href="/quotes/${quote.id}/like">
                            <#if quote.meLiked>
                                <i class="fas fa-thumbs-up"></i>
                            <#else>
                                <i class="far fa-thumbs-up"></i>
                            </#if>
                            ${quote.likes}
                        </a>
                        <#if quote.author.id == currentUserId>
                            <a class="col btn btn-primary" href="/user-quotes/${quote.author.id}?quote=${quote.id}">Edit</a>
                        </#if>
                    </div>
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

<@pager.pager url page />
