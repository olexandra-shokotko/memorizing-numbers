<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="jumbotron">
        <div class="mb-1">Find a verse</div>

        <form action="/find-verse" method="post">
            <div>
                <label class="col-form-label navbar-brand mb-2">Enter phone number</label>
                <input type="text" name="number" class="form-control col-sm-7" placeholder="Phone number" />
            </div>
            <div>
                <label class="col-form-label navbar-brand mt-3">Verse language</label>
                <div>
                    <label><input type="radio" name="language" value="en">English</label>
                    <label><input type="radio" name="language" value="ru">Russian</label>
                </div>
            </div>
            <hr class="my-4">

            <div class="input-group-prepend">
                <div>
                    <label><input type="checkbox" name="length">The number of digits in the number corresponds to the number of words in the verse</label>
                </div>
            </div>

            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <button class="btn btn-primary mt-3" type="submit">Find verse</button>
        </form>
    </div>
</@c.page>
