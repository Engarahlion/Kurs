# Процедура запуска автотестов

1. Установить IntelliJ IDEA 2024.2.3 (Ultimate Edition)
2. Установить и открыть Docker Desktop
3. Открыть проект в IntelliJ IDEA
4. Открыть файл build.gradle и обновить сборку
5. В окне терминала запустить команду: docker compose up
6. В втором окне терминала запустить команду: java -jar artifacts/aqa-shop.jar
7. В третьем окне терминала запустить команду: ./gradlew test
8. В третьем окне терминала запустить команду: ./gradlew allureServe