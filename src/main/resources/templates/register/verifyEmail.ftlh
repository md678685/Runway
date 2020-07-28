<#-- @ftlvariable name="user" type="io.github.md678685.runway.controller.model.UserRegisterDto" -->
<#import "/spring.ftl" as spring />
<#import "*/layout/base.ftlh" as base />

<#assign title><@spring.message "register.form.title" /></#assign>
<@base.base title="${title}">
    <div>
        <h2>Verify your email</h2>
        <p>An email was sent to ${currentUser.email}. Click the link in the email sent to verify your account.</p>
    </div>
</@base.base>