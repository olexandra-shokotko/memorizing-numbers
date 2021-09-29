<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="jumbotron">
        <h1 class="display-4">Verse</h1>

        <div class="card-columns">
            <div class="card my-3">
                <div class="m-2">
                    <#list result as lines>
                        <#list lines as line>
                            <span>${line.text}</span>
                            <br>
                        </#list>
                    </#list>
                </div>
            </div>
        </div>

<#--
        <div class="card-columns">
        <#list result as lines>
            <#list lines as line>
                <div class="card my-3">
                    <div class="m-2">
                        <span>${line.text}</span>
                    </div>
                </div>
            </#list>
        </#list>
        </div>
-->


    </div>
</@c.page>