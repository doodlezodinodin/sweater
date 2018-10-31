<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as l>

<@c.page>

<div class="mb-1">
    <p>Registration page</p>
</div>

<p>${message?ifExists}</p>

<@l.login "/registration" true/>

</@c.page>