<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as logout>

<@c.page>

<div>
    <@logout.logout/>
    <span><a href="/user">Список пользователей</a></span>
</div>

<div>
    <form method="post">
        <input type="text" name="text" placeholder="Введите сообщение"/>
        <input type="text" name="tag" placeholder="Тэг"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" value="Отправить"/>
    </form>
</div>

<p>Список сообщений</p>
<div>
    <form action="/main" method="get">
        <input type="text" name="filter" placeholder="Тэг" value="${filter!""}"/>
        <input type="submit" value="Найти"/>
    </form>
</div>
<#list messages as message>
<div>
    <b>${message.id}</b>
    <span>${message.text}</span>
    <i>${message.tag}</i>
    <strong>${message.authorName}</strong>
</div>
<#else>
<p>Список пустой</p>
</#list>

</@c.page>