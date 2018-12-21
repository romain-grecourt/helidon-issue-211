# Helidon 211

See https://github.com/oracle/helidon/issues/211

## Build

```bash
mvn package
```

## Run

```bash
cd seapp ; mvn exec:java
```

In another tab

```bash
cd mpapp ; mvn exec:java
```

In another tab

```bash
curl http://localhost:8080
```
