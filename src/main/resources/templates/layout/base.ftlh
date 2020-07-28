<#import "/spring.ftl" as spring />
<#import "*/utils/runway.ftlh" as runway />

<#macro base title additionalScripts="" scriptsEnabled=true authEnabled=true showHeader=true showFooter=true noContainer=false additionalMeta="" additionalStyling="">
    <!DOCTYPE html>
    <html lang="<@spring.message "general.language" />">
    <head>
        <title>${title} | Runway</title>

        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        ${additionalMeta}

        <link rel="apple-touch-icon" sizes="180x180"
              href="https://papermc.io/favicons/apple-touch-icon.png?v=BG70oBaPzN">
        <link rel="icon" type="image/png" sizes="32x32"
              href="https://papermc.io/favicons/favicon-32x32.png?v=BG70oBaPzN">
        <link rel="icon" type="image/png" sizes="16x16"
              href="https://papermc.io/favicons/favicon-16x16.png?v=BG70oBaPzN">

        <link href="https://fonts.googleapis.com/css?family=Montserrat:400,700" rel="stylesheet">
        <#--<link rel="stylesheet" type="text/css"
              href="<@runway.url "styles/font-awesome.css" />">-->
        <link rel="stylesheet" type="text/css"
              href="<@runway.url "bootstrap/css/bootstrap.min.css" />">
        <#--<link rel="stylesheet" type="text/css"
              href="<@runway.url "styles/app.css" />">-->

        <#-- See https://goo.gl/OOhYW5 and https://goo.gl/qRE0vM -->
        <link rel="manifest" href="<@runway.url "manifest/manifest.json" />">
        <meta name="theme-color" content="#333">
    </head>
    <body class="site">
    <#if showHeader>
        <#import "header.ftlh" as header>
        <@header.header authEnabled=authEnabled />
    </#if>

    <div class="site-content">
        <#if noContainer>
            <#nested>
        <#else>
            <div class="container<#if showHeader> site-header-margin</#if>">
                <#assign var><#--<@hangar.config "ore.staging" />-->true</#assign>
                <#if var == "true">
                    <div class="row">
                        <div class="col-xs-12">
                            <div class="staging-warning"><@spring.message "general.stagingWarning" /></div>
                        </div>
                    </div>
                </#if>
                <div class="row">
                    <div class="col-xs-12">
<#--                        <#import "../utils/alert.ftlh" as alert>-->
<#--                        <@alert.alert "success" />-->
<#--                        <@alert.alert "error" />-->
<#--                        <@alert.alert "info" />-->
<#--                        <@alert.alert "warning" />-->
                    </div>
                </div>

                <#nested>
            </div>
        </#if>
    </div>

    <#if showFooter>
        <#import "footer.ftlh" as footer>
        <@footer.footer />
    </#if>

    <#if scriptsEnabled>
<#--        <script type="text/javascript" src="${routes.getRouteUrl("javaScriptRoutes")}"></script>-->
<#--        <script type="text/javascript" src="<@runway.url "build/vendors.js" />"></script>-->
<#--        <script type="text/javascript" src="<@runway.url "build/font-awesome.js" />"></script>-->
<#--        <script type="text/javascript" src="<@runway.url "build/commons.js" />"></script>-->
<#--        <script type="text/javascript" src="<@runway.url "lib/moment/min/moment.min.js" />"></script>-->
<#--        <script type="text/javascript" src="<@runway.url "bootstrap/js/bootstrap.min.js" />"></script>-->
<#--        <script type="text/javascript" src="<@runway.url "lib/clipboard/dist/clipboard.min.js" />"></script>-->
<#--        <script type="text/javascript" src="<@runway.url "javascripts/main.js" />"></script>-->
<#--        <script type="text/javascript" src="<@runway.url "javascripts/apiRequests.js" />"></script>-->

        ${additionalScripts}
    </#if>
    </body>
    </html>
</#macro>
