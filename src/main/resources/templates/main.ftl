<#import "parts/common.ftl" as c>
<#import "parts/login.ftl" as logout>

<@c.page>

<div>
    <@logout.logout/>
</div>

<div>
    <form action="addMessage" method="post">
        <input type="text" name="text" placeholder="Введите сообщение"/>
        <input type="text" name="tag" placeholder="Тэг"/>
        <input type="hidden" name="_csrf" value="${_csrf.token}">
        <input type="submit" text="Отправить"/>
    </form>
</div>

<div>
    <form action="/main" method="get">
        <input type="text" name="tag" placeholder="Тэг" value="${tag}"/>
        <input type="submit" text="Найти"/>
    </form>
</div>

<p>Список сообщений</p>
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