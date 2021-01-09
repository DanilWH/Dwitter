<#import "/parts/common.ftlh" as c>
<#import "/parts/auth_common.ftlh" as ac>

<@c.page>

	<h4>Login page</h4>

    <#if Session?? && Session.SPRING_SECURITY_LAST_EXCEPTION??>
        <div class="alert alert-danger" role="alert">
            ${Session.SPRING_SECURITY_LAST_EXCEPTION.message}
        </div>
    </#if>

    <#if message??>
        <div class="alert alert-${messageType}" role="alert">
            ${message}
        </div>
    </#if>

	<@ac.auth "/login" false/>
	
</@c.page>
