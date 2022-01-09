<#import "parts/common.ftlh" as c>
<#import "parts/login.ftlh" as l>

<@c.page>
    <div class="mb-1">
        <h2 class="display-5">Registration</h2>
    </div>
    ${message?ifExists}
    <@l.login "/registration" true />
</@c.page>