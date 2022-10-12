Сервер обработки POST запросов.

Для запуска приложения через Docker необходимы следующие команды в терминале:
1. docker build -t <имя приложения> . (создать образ приложения)
2. docker-compose build (создать контейнер на основе образа)
3. docker-compose up (запустить контейнер)

Для остановки приложения через терминал необходимо нажать CTRL + C

Приложение работает на порту 5500 (настроить можно в файле src/main/resources/application.properties).


Примеры запросов:
1. 
http://localhost:5500/transfer

{
"cardFromNumber": "1111112111111111",
"cardFromValidTill": "01/23",
"cardFromCVV": "354",
"cardToNumber": "2222222222222222",
"amount": {
"value": 3,
"currency": "RUR"
}
}

2.
http://localhost:5500/confirmOperation

{
"operationId": "100",
"code": "0000"
}

