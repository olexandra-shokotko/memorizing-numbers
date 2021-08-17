<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="jumbotron">
        <div class="mb-1">Add new verse</div>

        <form action="/add-verse" method="post">
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Verse Name :</label>
                <div class="col-sm-6">
                    <input type="text" name="verseName" class="form-control" placeholder="Verse name" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Author:</label>
                <div class="col-sm-6">
                    <input type="text" name="author" class="form-control" placeholder="Author" />
                </div>
            </div>
            <div class="form-group row">
                <label class="col-sm-2 col-form-label">Verse:</label>
                <div class="col-sm-6">
                    <textarea type="text" name="verse" class="form-control" placeholder="Verse"></textarea>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}" />

            <button class="btn btn-primary" type="submit">Add verse</button>

            <a href="/verses">List of verses</a>
        </form>
    </div>
</@c.page>