# Лабораторна робота №3

## Тема: Annotation Processor

## Виконала: ІО-24 Гайдукевич Олександра

## Номер залікової книжки: 2405, отже варіант: 2405 % 3 = 2

# Опис проєкту:
Цей проєкт реалізує процесор анотацій для перевірки правильності застосування анотацій у Java коді. 
Використовуються анотації, такі як `@NotNull`, `@MaxValue`, `@MinValue`, та `@StringLength`, для валідації полів класів на різні обмеження. 
Процесор перевіряє типи полів та правильність їх застосування, генеруючи помилки або нотатки в разі невідповідностей.

# Вимоги
- JDK версії 22 або вище
- Встановлений Git
- Встановлений Maven

# Як запускати
1. Клонувати репозиторій на свій комп'ютер:
```bash
git clone https://github.com/hdksasha/advanced-java.git
```

2. Перейти в папку лабораторної роботи:
```bash
cd advanced-java/lab3
```

3. Запустити проєкт через javac та java:
```bash
mvn clean package
javac -d out src/main/java/org/example/*.java src/main/java/org/example/entities/*.java src/main/java/org/example/annotations/*.java
java -cp out Main
```
