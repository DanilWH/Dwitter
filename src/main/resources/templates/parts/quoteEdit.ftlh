<a class="btn btn-primary mb-3" data-toggle="collapse" href="#newQuoteCollapse" role="button" aria-expanded="false" aria-controls="newQuoteCollapse">
    Quote editor
</a>
<div id="newQuoteCollapse" class="collapse <#if quote??>show</#if>">
    <div class="form-group">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="text" placeholder="...Enter your quote..."
                    class="form-control ${(textError??)?string('is-invalid', '')}"
                    value="${(quote.text)?ifExists}"/>
                <#if textError??>
                    <div class="invalid-feedback">
                        ${textError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <input type="text" name="tag" placeholder="Tag"
                    class="form-control ${(tagError??)?string('is-invalid', '')}"
                    value="${(quote.tag)?ifExists}"/>
                <#if tagError??>
                    <div class="invalid-feedback">
                        ${tagError}
                    </div>
                </#if>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input id="customFile" type="file" name="file" class="custom-file-input" />
                    <label for="customFile" class="custom-file-label">Choose file</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />
            <input type="hidden" name="id" value="${(quote.id)?ifExists}" />
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Save</button>
            </div>
        </form>
    </div>
</div>
