<#import "parts/common.ftl" as c>

<@c.page>
<p>Edit user</p>

<form action="/user" method="post">
    <input type="text" value="${user.username}" name="username" title=""/>
    <#list roles as role>
        <label for=""><input type="checkbox" name="${role}" ${user.roles?seq_contains(role)?string("checked", "")}>${role}</label>
    </#list>

    <input type="hidden" value="${user.id}" name="userId"/>
    <input type="hidden" name="_csrf" value="${_csrf.token}"/>
    <input type="submit" text="Save"/>
</form>
</@c.page>