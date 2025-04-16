
## Структура проекта

![ТестовоеОтпускные](https://github.com/user-attachments/assets/a9e8b847-0682-4ceb-81df-d793c5687760)

## Стек

Проект реализован на следующем стеке:
- **Java 11**
- **Spring Boot 2.7.18**
- **Spring Web**
- **Spring Validation**
- **Lombok**
- **JUnit 5**
- **Gradle**

#### server.port=8089

## API Эндпоинты
### GET /calculacte

🔸 Тело запроса (application/json)
Можно передать данные двумя способами:

По количеству дней:
```json
{
  "salary": 100000,
  "num_days": 10
}
```

По диапазону дат:
```json
{
  "salary": 100000,
  "start_date": "01-05-2024",
  "end_date": "10-05-2024"
}
```
Приоритет имеет диапазон дат, если указаны и даты, и количество дней.

📤 Пример успешного ответа
```json
{
  "pay_vacation": 20400.00
}
```
