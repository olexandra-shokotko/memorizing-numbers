<#import "parts/common.ftlh" as c>

<@c.page>
    <div class="jumbotron">
        <h1 class="display-4">Verses for <strong>${number}</strong></h1>

        <#if result?has_content>
            <div class="card-columns">
                <#list result as lines>
                    <div class="card my-3">
                        <div class="m-2">

                            <#assign counter = 0>
                            <#assign name></#assign>
                            <#assign author></#assign>

                            <#list lines as line>
                                <#assign items=line.text?trim?split(" ") >
                                <#assign name=line.verse.name >
                                <#assign author=line.verse.author >


                                <#list items as item>
                                    <#if item?matches("-")>
                                        <#assign lengthNumber=lengthNumber+1>
                                    </#if>

                                    <#if counter < lengthNumber>
                                        <span class="full-text" title="${line.verse.name + "\n" + line.verse.author}"><strong>${item}</strong></span>
                                    <#else>
                                        <span class="full-text" title="${line.verse.name + "\n" + line.verse.author}">${item}</span>
                                    </#if>
                                    <#assign counter = counter + 1>
                                </#list>
                                <br>

                            </#list>

                            <div style="font-weight:bold; font-size:10px; text-align:right">
                                ${name + " - " + author}
                            </div>

                        </div>
                    </div>
                </#list>
            </div>
        <#else>
            <h4>Sorry. Nothing for your request :(</h4>
        </#if>

    </div>
</@c.page>
