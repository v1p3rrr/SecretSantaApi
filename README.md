# Задание 2. Тайный Санта

Необходимо реализовать RESTful API сервис для игры в Тайного Санту.

## Типы данных:

### Группа

| Тип данных   | Свойства                                     |
| ------------ | -------------------------------------------- |
| **Группа**   | id – идентификатор группы, натуральное число |
|              | name – название группы, строка              |
|              | description – описание группы, строка        |
|              | participants – участники группы, список объектов типа "Участник" |

### Участник

| Тип данных   | Свойства                                |
| ------------ | --------------------------------------- |
| **Участник** | id – идентификатор участника, натуральное число |
|              | name – имя участника, строка           |
|              | wish – пожелание, строка               |
|              | recipient - подопечный, объект типа "Участник" |

## Возможные действия:

| Метод | Endpoint | Пояснение | Описание |
| ----- | -------- | ---------- | -------- |
| POST  | /group   | В запросе передается body в формате JSON:<br>`{`<br>`"name": "string",`<br>`"description": "string"`<br>`}`<br><br> В ответе ожидается идентификатор созданной группы, например 1 | Добавление группы с возможностью указания названия (name), описания (description) <br><br> Описание – не обязательный параметр, название – обязательный |
| GET   | /groups  | В ответе ожидается полный список групп, без указания участников в формате JSON:<br>`[`<br>`{ "id": number, "name": "string", "description": "string" },`<br>`...`<br>`]` | Получение краткой информации о всех группах (без информации о участниках) |
| GET   | /group/{id}  | ID группы, полную информацию о которой необходимо получить, передается в виде path-параметра, например: `/group/1` | Получение полной информации (с информацией об участниках) о группе по идентификатору |
|       |              | В ответе ожидается полная информация о группе, с указанием участников в формате JSON:<br>`{`<br>&nbsp;`"id": number,`<br>&nbsp;`"name": "string",`<br>&nbsp;`"description": "string",`<br>&nbsp;`"participants": `<br>&nbsp;` [`<br>&nbsp;&nbsp;`{ `<br>&nbsp;&nbsp;`"id": number, `<br>&nbsp;&nbsp;`"name": "string", `<br>&nbsp;&nbsp;`"wish": "string", `<br>&nbsp;&nbsp;`"recipient": `<br>&nbsp;&nbsp;`{ `<br>&nbsp;&nbsp;&nbsp;`"id": number, `<br>&nbsp;&nbsp;&nbsp;`"name": "string", `<br>&nbsp;&nbsp;&nbsp;`"wish": "string" `<br>&nbsp;&nbsp;`} `<br>&nbsp;`},`<br>&nbsp;`...`<br>&nbsp;`]`<br>`}` | *До проведения жеребьевки recipient у участников не заполнен |
| PUT     | /group/{id}  | ID редактируемой группы, передается как path-параметр, например: `/group/1` | Редактирование группы по идентификатору группы |
|         |              | Редактируемые свойства передаются в body запроса в формате JSON: `{ "name": "string", "description": "string" }` | Редактировать можно только свойства name, description |
| DELETE  | /group/{id}  | ID удаляемой группы передается как path-параметр, например: `/group/1` | Удаление группы по идентификатору |
| POST    | /group/{id}/participant | ID группы, в которую добавляется участник, передается как path-параметр: например: `/group/1/participant` | Добавление участника в группу по идентификатору группы |
|         |              | В запросе передается body с информацией о добавляемом участнике в формате JSON: `{ "name": "string", "wish": "string" }` | Пожелания – не обязательный параметр, имя – обязательный |
| DELETE  | /group/{groupId}/participant/{participantId} | ID редактируемой группы и ID удаляемого участника передаются как path-параметры, например: `/group/1/participant/123` | Удаление участника из группы по идентификаторам группы и участника |
| POST    | /group/{id}/toss               | ID группы передается в виде path-параметра, например: `/group/1/toss` <br><br>  В ответе ожидается список объектов типа "Участник" с указанными положенными в формате JSON: `[ { "id": number, "name": "string", "wish": "string", "recipient": { "id": number, "name": "string", "wish": "string" } }, ... ]`| Проведение жеребьевки в группе по идентификатору группы <br><br> Проведение жеребьевки возможно только в том случае, когда количество участников группы >= 3|
| GET   | /group/{groupId}/participant/{participantId}/recipient | ID группы и участника передаются в виде path-параметров, например: `/group/1/recipient/2` <br><br> В ответе ожидается объект типа "Участник" – положенный участника, чей идентификатор передан, в формате JSON: `{ "id": number, "name": "string", "wish": "string" }`| Получение информации для конкретного участника группы, кому он дарит подарок |

Сервис должен запускаться на порту 8080.

При доступных и действительных запросах код ответа должен быть равен 200, 201, или 204.

При недоступных или недействительных запросах сервис должен возвращать соответствующие сообщения об ошибках, код ответа должен останавливаться от 200, 201, 202 или 204.

Можно использовать любые open source библиотеки.

Проверка будет производиться автоматизированным тестировщиком ПО.
Необходимо также приложить исходный код в виде архива и выгрузить его в любой git-репозиторий (github, gitlab, bitbucket) с предоставлением публичного бесплатного доступа и приложить ссылку.

## Критерии оценки:

1. Проверка автоматизированным тестировщиком ПО.
2. Описание технического решения
   2.1. Соответствие принципам SOLID
   2.2. Описание документации API в одной из общепринятых спецификаций (рекомендуется OpenAPI (Swagger))

## Требования к структуре оформления решения:

1. Исходный код решения.
   1.1. Оценка формируется исходя из результатов проверки API автотестом
   1.2. Возможность запуска инфраструктуры приложения в контейнерах (docker-compose)
2. Описание технического решения:
   2.1. Соответствие принципам SOLID
   2.2. Описание документации API в одной из общепринятых спецификаций (рекомендуется OpenAPI (Swagger))
2.3. Описание настроек среды выполнения и запуска приложения