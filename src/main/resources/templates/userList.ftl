<#import "parts/common.ftl" as c>

<@c.page>

<p>Список пользователей</p>

<table border=0>
    <tr>
        <th>Имя</th>
        <th>Role</th>
    </tr>
    <#list users as user>
    <tr>
        <td>${user.username}</td>
        <td><#list user.roles as role>${role}<sep>, </#list></td>
        <td><a href="/user/${user.id}">edit</a></td>
    </tr>
    </#list>
</table>

</@c.page>



