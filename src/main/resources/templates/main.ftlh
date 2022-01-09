<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="jumbotron">
        <h2 class="mb-1 display-5">Find a verse</h2>

        <form action="/find-verse" method="post">
            <div>
                <label class="col-form-label navbar-brand mb-2">Enter number</label>
                <input type="text" name="number" class="form-control col-sm-7" placeholder="Number" required pattern="^[0-9]+" maxlength="50"/>
            </div>
            <div>
                <label class="col-form-label navbar-brand mt-3">Verse language</label>
                <div>
                    <label><input type="radio" name="language" value="en">English</label>
                    <label><input type="radio" name="language" value="ru" checked>Russian</label>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <button class="btn btn-primary mt-3" type="submit">Find verse</button>
        </form>
    </div>
</@c.page>
