<#import "parts/common.ftlh" as c>

<@c.page>

   		<div class="form-row">
   			<div class="form-group col-md-6">
    			<form method="get" action="/main" class="form-inline">
		    		<input type="text" name="filter" class="form-control mr-3"
		    				value="${filter?ifExists}" placeholder="Search by tag"/>
		    		<button type="submit" class="btn btn-primary">Search</button>
		    	</form>
   			</div>
   		</div>
    	
        <#include "parts/quoteEdit.ftlh" />
    	
    	<#include "parts/quoteList.ftlh" />
        
</@c.page>
