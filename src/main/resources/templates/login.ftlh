<#import "/parts/common.ftlh" as c>
<#import "/parts/auth_common.ftlh" as ac>

<@c.page>

	<h4>Login page</h4>
	
	<@ac.auth path="/login" />
	
	<a href="/registration" >Registration</a>
	
</@c.page>