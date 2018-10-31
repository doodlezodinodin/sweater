<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>
<p>Registration page</p>

<p>${message?ifExists}</p>

<@l.login "/registration"/>

</@c.page>