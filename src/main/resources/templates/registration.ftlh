<#import "/parts/common.ftlh" as c>
<#import "/parts/auth_common.ftlh" as ac>

<@c.page>
	<h4>Sign up page</h4>
	
	<#if message??>
   		<p>${message}</p>
   	</#if>
   		
   		
    
    <@ac.auth path="/registration" />
    
</@c.page>
