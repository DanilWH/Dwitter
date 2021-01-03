<#import "/parts/common.ftlh" as c>

<@c.page>

    <h1>${username}</h1>

	<p>${message?ifExists}</p>
	
	<form method="post">
		<!-- Password -->
	    <div class="form-group row">
	    	<label class="col-sm-2 col-form-label">Password:</label>
	    	<input type="password" name="password" placeholder="Password" class="col-sm-6"/>
	    </div>

		<!-- Email -->
		<div class="form-group row">
	    	<label class="col-sm-2 col-form-label">Email:</label>
	    	<input type="email" name="email" class="col-sm-6" placeholder="some@some.com" value="${email!''}" />
	    </div>

	    <input type="hidden" name="_csrf" value="${_csrf.token}" />
	    
	    <button name="submit" class="btn btn-primary">Save</button>
	</form>

</@c.page>

