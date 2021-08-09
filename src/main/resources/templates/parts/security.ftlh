<#assign
known = Session.SPRING_SECURITY_CONTEXT??
>

<#if known>
    <#assign
    user = Session.SPRING_SECURITY_CONTEXT.authentication.principal
    name = "You are logged in as " + user.getUsername()
    isAdmin = user.isAdmin()
    type = true
    >
<#else>
    <#assign
    name = ""
    isAdmin = false
    type = false
    >
</#if>