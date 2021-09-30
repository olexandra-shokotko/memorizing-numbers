<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="jumbotron">
        <h1 class="display-4">Verses for <strong>${number}</strong></h1>

        <#if result?has_content>
            <div class="card-columns">
                <#list result as lines>
                    <div class="card my-3">
                        <div class="m-2">
                            <#list lines as line>
                                <div style="font-weight:bold; font-size:10px; text-align:right">
                                    ${line.verse.name + " - " + line.verse.author}
                                </div>
                                <span class="full-text" title="${line.verse.name + "\n" + line.verse.author}">${line.text}</span>
<#--                                <span style="display: none" class="limit-text" title="${line.verse.name + "\n" + line.verse.author}">${line.limitText(3)}</span>-->
                                <br>
                            </#list>
                        </div>
                    </div>
                </#list>
            </div>
        <#else>
            <h4>Sorry. Nothing for your request :(</h4>
        </#if>

    </div>
</@c.page>
