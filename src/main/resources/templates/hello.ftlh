<#import "/spring.ftl" as spring />
<#import "*/layout/base.ftlh" as base />

<#assign title>Super secret logged in page</#assign>
<@base.base title="${title}">
    <h1>You're logged in!</h1>
    <p>Hello ${username}</p>
</@base.base>
