# Для запуска

________________________
Нужна база данных `PostgreSQL`. В корне исходных файлов лежит файл `docker-compose.yml`.
Его можно запустить командой `docker-compose up -d`. Таким образом, настраивать бд не понадобится.
Иначе можно настроить подключение к своей БД в `файлах настроек properties`.

В папке `sql` лежит 2 скрипта: `create_tables.sql`, который создает нужные таблицы, и `fill_tables.sql`,
который заполняет таблицы данными. Они автоматически срабатывают при использовании `docker-compose`.
При использовании же своей БД необходимо их выполнить самому. Иначе приложение не запустится,
так как установлено `jpa.hibernate.ddl-auto=validate`.

# REST API

________________________

## 1. Загрузчик

### Запрос

### `POST http://localhost:8080/api/v1/files`

### `Content-Type: multipart/form-data`

### Ответ

### `0c9dcea9-bc80-45a9-8b15-43b7791525f7`

## 2. Добавление нового пользователя

### Запрос

### `POST http://localhost:8080/api/v1/users`

```json
{
  "name": "kanat",
  "email": "nash.kanat@gmail.com",
  "imageUri": "dd6dc79d-35ed-44c2-9157-1a79fbf8e7d6"
}
```

### Ответ

### `525`

## 3. Получение информации о пользователе

### Запрос

### `GET http://localhost:8080/api/v1/users/525`

### Ответ

```json
{
  "id": 525,
  "name": "username",
  "email": "name@gmail.com",
  "imageUri": "a0341e75-0e68-4a80-8d98-544486b0ee63"
}
 ```

## 4. Изменение статуса пользователя (Online, Offline)

### Запрос

### `PATCH http://localhost:8080/api/v1/users/525`

```json
{
  "status": "Online"
}
```

### Ответ

```json
{
  "id": 525,
  "oldStatus": "Offline",
  "newStatus": "Online"
}
 ```
