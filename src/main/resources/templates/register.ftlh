<#-- @ftlvariable name="returnTo" type="java.lang.String" -->
<#import "/spring.ftl" as spring />
<#import "*/layout/base.ftlh" as base />

<#assign title>Super exclusive anonymous page</#assign>
<@base.base title="${title}">
    <p>Something something register page return to ${returnTo!"Unknown"}</p>
</@base.base>
