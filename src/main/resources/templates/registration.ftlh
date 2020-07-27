<#import "/parts/common.ftlh" as c>
<#import "/parts/auth_common.ftlh" as ac>

<@c.page>
	<h4>Sign up page</h4>
	
   	<p>${message?ifExists}</p>
   		
    <@ac.auth path="/registration" />
    
</@c.page>
