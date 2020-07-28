<#-- @ftlvariable name="userRegister" type="io.github.md678685.runway.controller.model.UserRegisterDto" -->
<#import "/spring.ftl" as spring />
<#import "*/layout/base.ftlh" as base />
<#import "*/utils/csrf.ftlh" as csrf>

<#-- TODO: client-side verification -->

<#assign title><@spring.message "register.form.title" /></#assign>
<@base.base title="${title}">
    <form action="/register" method="post">
        <@spring.bind path="userRegister" />
        <p>
            <@spring.showErrors "<br>"/>
        </p>
        <@csrf.formField />
        <div>
            <label for="username">Username</label>
            <@spring.formInput "userRegister.username" "required" />
            <p>
                <@spring.showErrors "<br>"/>
            </p>
        </div>

        <div>
            <label for="email">Email</label>
            <@spring.formInput "userRegister.email" "required" "email" />
            <p>
                <@spring.showErrors "<br>"/>
            </p>
        </div>

        <div>
            <label for="password">Password</label>
            <@spring.formPasswordInput "userRegister.password" "required" />
            <p>
                <@spring.showErrors "<br>"/>
            </p>
        </div>

        <div>
            <label for="repeatPassword">Repeat password</label>
            <@spring.formInput "userRegister.repeatPassword" "required" "password" />
            <p>
                <@spring.showErrors "<br>"/>
            </p>
        </div>

        <div>
            <input type="submit" value="Register">
        </div>
    </form>
</@base.base>