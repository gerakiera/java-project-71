# Вычислитель отличий
Вычислитель отличий- программа, определяющая разницу между двумя файлами.
Возможные форматы файлов- json и yml;
Возможные форматы вывода:
- *stylish* (является дефолтным, если не выбран ни один другой формат)
- *plain*
- *json*

Вызов справочной информации:
```
./build/install/app/bin/app -h
```
Пример команды для сравнения файлов:
```
./build/install/app/bin/app -f plain filepath1.json filepath2.json
```
## Демонстрация проекта:
### Сравнение плоских файлов (JSON и yaml)
[![asciicast](https://asciinema.org/a/4jI7d8qawCIb3YvwUrJGCIP4N.svg)](https://asciinema.org/a/4jI7d8qawCIb3YvwUrJGCIP4N)
### Hexlet tests and linter status:
[![Actions Status](https://github.com/gerakiera/java-project-71/actions/workflows/hexlet-check.yml/badge.svg)](https://github.com/gerakiera/java-project-71/actions)
<a href="https://codeclimate.com/github/gerakiera/java-project-71/maintainability"><img src="https://api.codeclimate.com/v1/badges/1e5efb3d4dd7fefdd2f1/maintainability" /></a>
<a href="https://codeclimate.com/github/gerakiera/java-project-71/test_coverage"><img src="https://api.codeclimate.com/v1/badges/1e5efb3d4dd7fefdd2f1/test_coverage" /></a>
[![Java CI](https://github.com/gerakiera/java-project-71/actions/workflows/javaCl.yml/badge.svg)](https://github.com/gerakiera/java-project-71/actions/workflows/javaCl.yml)
