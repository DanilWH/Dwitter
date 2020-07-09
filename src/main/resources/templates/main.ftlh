<#import "/parts/common.ftlh" as c>
<#import "/parts/auth_common.ftlh" as ac>

<@c.page>

    	<@ac.logout />
        
    	<div>
    		<form method="post">
    			<input type="text" name="text" placeholder="...Enter your quote..." />
    			<input type="text" name="tag" placeholder="Tag" />
    			<input type="hidden" name="_csrf" value="${_csrf.token}" />
    			<button type="submit">Add</button>
    		</form>
    	</div>
    	
    	<div><h3>Quotes list</h3></div>
    	<form method="get" action="/main">
    		<input type="text" name="filter" value="${filter}"/>
    		<button type="submit">Search</button>
    	</form>
    	
    	<#list quotes as quote>
	    	<div>
	        	<b>${quote.id}</b>
	        	<span>${quote.text}</span>
	        	<i>${quote.tag}</i>
	        	<strong>${quote.authorName}</strong>
	        </div>
	    <#else>
	    	<p>There are no quotes.</p>
    	</#list>
    	
        
</@c.page>