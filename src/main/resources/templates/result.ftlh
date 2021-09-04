<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="jumbotron">
        <h1 class="display-4">Verse</h1>
<#--        <#if res??>
            <div>${res}</div>
        <#else><div>error</div>
        </#if>-->

        <#list result as lines>
            <p>
                <#list lines as line>
                    <span>${line.text}</span>
                    <br>
                </#list>
            </p>
        </#list>

        <#--<label>${res}</label>-->
    </div>
</@c.page>