### Info21

- Для работы базы данных создается отдельная схема под названием `app`, скрипт создания лежит в ресурсках `schema.sql`
- Также создается в отдельной схеме `liquibase` - схема для хранения истории работы с таблицами базы данных
- Файл миграции находится в ресурсах под названием `db.changelog-master.yaml` из него вызываются скрипты и файлы миграции для создания таблиц и наполнения данными `import_data`

Запуск создания таблиц базы и миграции:
```bash
./gradlew update
```