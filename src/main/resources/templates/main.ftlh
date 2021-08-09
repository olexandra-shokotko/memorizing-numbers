<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="jumbotron">
        <form method="post">
            <div>
                <label class="col-sm-5 col-form-label navbar-brand mb-2">Enter phone number</label>
                <input type="text" name="number" class="form-control col-sm-7" placeholder="Phone number" />
            </div>
            <hr class="my-4">

            <div class="input-group-prepend">
                <div>
                    <label><input type="checkbox" aria-label="Checkbox for following text input">The number of digits in the number corresponds to the number of words in the verse</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <button class="btn btn-primary mt-3" type="submit">Find verse</button>
        </form>
    </div>
</@c.page>
