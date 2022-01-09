<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <h2 class="display-5">Login page</h2>
    <@l.login "/login" false/>
</@c.page>