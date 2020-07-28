<#import "/spring.ftl" as spring />
<#import "*/utils/runway.ftlh" as runway />

<#macro header authEnabled=true>
    <nav id="topbar" class="navbar-main navbar-inverse">
        <div class="container">
            <#-- Left navbar -->
            <div id="sp-logo-container">
                <a class="logo" href="<#--${routes.getRouteUrl("showHome")}-->">
                    <img src="https://papermc.io/images/logo-marker.svg" alt="Paper logo">
                    <i class="fas fa-fw fa-chevron-down"></i>
                </a>

                <div id="sp-logo-menu">
                    <ul id="sp-logo-dropdown">
                        <li><a href="https://www.papermc.io">
                                <i class="fa-fw fas fa-home"></i><@spring.message "general.home" />
                            </a></li>
                        <li><a href="https://papermc.io/forums">
                                <i class="fa-fw fas fa-comments"></i><@spring.message "general.forums" />
                            </a></li>
                        <li><a href="https://github.com/PaperMC">
                                <i class="fa-fw fas fa-code"></i><@spring.message "general.code" />
                            </a></li>
                        <li><a href="https://paper.readthedocs.io">
                                <i class="fa-fw fas fa-book"></i><@spring.message "general.docs" />
                            </a></li>
                        <li><a href="https://papermc.io/javadocs">
                                <i class="fa-fw fas fa-graduation-cap"></i><@spring.message "general.javadocs" />
                            </a></li>
                        <li><a href="<@spring.message "general.hangar.url" />">
                                <img src="<@runway.url "images/ore-nav.svg" />" alt=""
                                     class="fa-fw ore-nav"><@spring.message "general.plugins" />
                                (<@spring.message "general.hangar" />)
                            </a></li>
                        <li><a href="https://papermc.io/downloads">
                                <i class="fa-fw fas fa-download"></i><@spring.message "general.getsponge" />
                            </a></li>
                        <li><a href="https://papermc.io/community">
                                <i class="fa-fw fas fa-comment"></i><@spring.message "general.irc" />
                            </a></li>
                    </ul>
                </div>
            </div>

            <#-- Right navbar -->
            <#if authEnabled>
                <ul class="nav navbar-nav navbar-collapse collapse navbar-right">
                    <#if cu??>
                        <@runway.hasPerm "IsStaff">
                            <li class="nav-icon staff-icon" <@tooltip title=title />>
                                <a href="<#--${routes.getRouteUrl("admin.home", "", "")}-->">
                                    <i class="icon fas fa-user-tie"></i>
                                </a>
                            </li>
                        </@runway.hasPerm>

                    <#-- Show user controls -->

                    <#else>
                        <li>
                            <div class="btn-group-login">
                                <a href="<#--${routes.getRouteUrl("users.signUp")}-->"
                                   class="btn btn-primary navbar-btn"><@spring.message "general.signup" /></a>
                                <a href="<#--${routes.getRouteUrl("users.login", "", "", rc.requestUri?url)}-->"
                                   class="btn btn-primary navbar-btn"><@spring.message "general.login" /></a>
                            </div>
                        </li>
                    </#if>
                </ul>
            </#if>
        </div>
    </nav>
</#macro>

<#macro tooltip title>
    data-toggle="tooltip" data-placement="bottom" title="${title}"
</#macro>
