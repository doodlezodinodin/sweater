<#import "parts/common.ftl" as c>

<@c.page>

<div class="form-row">
    <div class="form-group col-md-6">
        <form action="/main" method="get" class="form-inline">
            <input class="form-control" type="text" name="filter" placeholder="Поиск по тэгу"
                   value="${filter?ifExists}"/>
            <button type="submit" class="btn btn-primary ml-2">Найти</button>
        </form>
    </div>
</div>

<a class="btn btn-primary" data-toggle="collapse" href="#collapseExample" role="button" aria-expanded="false"
   aria-controls="collapseExample">
    Написать сообщение
</a>
<div class="collapse" id="collapseExample">
    <div class="form-group mt-3">
        <form method="post" enctype="multipart/form-data">
            <div class="form-group">
                <input type="text" name="text" class="form-control" placeholder="Введите сообщение"/>
            </div>
            <div class="form-group">
                <input type="text" name="tag" class="form-control" placeholder="Тэг"/>
            </div>
            <div class="form-group">
                <div class="custom-file">
                    <input type="file" name="file" id="customFile">
                    <label class="custom-file-label" for="customFile">Прикрепить файл (не обязательно)</label>
                </div>
            </div>
            <input type="hidden" name="_csrf" value="${_csrf.token}">
            <div class="form-group">
                <button type="submit" class="btn btn-primary">Отправить</button>
            </div>
        </form>
    </div>
</div>

<div class="card-colums">
<#list messages as message>
    <div class="card my-3">

    <#if message.filename??>
        <img src="/img/${message.filename}" alt="${message.filename} class="card-img-top">
    </#if>
        <div class="m-2">
            <span>${message.text}</span>

        </div>
        <div class="card-footer text-muted">
            #<i>${message.tag}</i> Автор: ${message.authorName}
        </div>
    </div>
<#else>
<p>Список пустой</p>
</#list>
</div>

</@c.page>