<#import "/parts/common.ftlh" as c>

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
    	
    	<a class="btn btn-primary mb-3" data-toggle="collapse" href="#newQuoteCollapse" role="button" aria-expanded="false" aria-controls="newQuoteCollapse">
    		Add a new quote
  		</a>
    	<div id="newQuoteCollapse" class="collapse">
   			<div class="form-group">
	    		<form method="post" enctype="multipart/form-data">
	    			<div class="form-group">
		    			<input type="text" name="text" placeholder="...Enter your quote..." class="form-control"/>
	    			</div>
	    			<div class="form-group">
		    			<input type="text" name="tag" placeholder="Tag" class="form-control" />
	    			</div>
	    			<div class="form-group">
		    			<div class="custom-file">
			    			<input id="customFile" type="file" name="file" class="custom-file-input" />
			    			<label for="customFile" class="custom-file-label">Choose file</label>
		    			</div>
	    			</div>
	    			<input type="hidden" name="_csrf" value="${_csrf.token}" />
	    			<div class="form-group">
		    			<button type="submit" class="btn btn-primary">Add</button>
	    			</div>
	    		</form>
   			</div>
    	</div>
    	
    	<div class="card-columns">
	    	<#list quotes as quote>
		    	<div class="card text-center m-3">
	        		<#if quote.filename??>
	        			<img src="/img/${quote.filename}" class="card-img-top p-2"/>
	        		</#if>
	        		<blockquote class="blockquote p-3">
	        			<p>${quote.text}</p>
	        			<footer class="blockquote-footer">
	        				<small>${quote.authorName}</small>
	        			</footer>
	        		</blockquote>
	        		<div class="card-footer text-left p-0">
				      	<small class="text-muted ml-3">${quote.tag}</small>
				    </div>
		        </div>		        
		    <#else>
		    	<p>There are no quotes.</p>
	    	</#list>
    	</div>
    	
        
</@c.page>