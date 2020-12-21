<#macro auth path isRegisterForm>

	<form action="${path}" method="post">
		<!-- Username -->
	    <div class="form-group row">
	    	<label class="col-sm-2 col-form-label">User Name:</label>
	    	<input type="text" name="username" placeholder="User Name" class="col-sm-6"/>
	    </div>

		<!-- Password -->
	    <div class="form-group row">
	    	<label class="col-sm-2 col-form-label">Password:</label>
	    	<input type="password" name="password" placeholder="Password" class="col-sm-6"/>
	    </div>

		<!-- Email -->
		<#if isRegisterForm>
		<div class="form-group row">
	    	<label class="col-sm-2 col-form-label">Email:</label>
	    	<input type="email" name="email" placeholder="some@some.com" class="col-sm-6"/>
	    </div>
		</#if>

	    <input type="hidden" name="_csrf" value="${_csrf.token}" />
	    
	    <#if !isRegisterForm>
	    	<a href="/registration">Registration</a>
	    </#if>
	    
	    <button name="submit" class="btn btn-primary"><#if isRegisterForm>Sign Up<#else>Sign In</#if></button>
	</form>

</#macro>

<#macro logout>

	<form action="/logout" method="post">
		<input type="hidden" name="_csrf" value="${_csrf.token}" />
        <button name="submit" class="btn btn-primary">Sign Out</button>
    </form>

</#macro>