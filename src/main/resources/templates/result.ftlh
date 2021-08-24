<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="jumbotron">
        <h1 class="display-4">Verse</h1>
        <#if res??>
            <div>${res}</div>
        <#else><div>error</div>
        </#if>

        <#--<label>${res}</label>-->
    </div>
</@c.page>