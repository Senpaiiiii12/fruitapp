# FruitApp - CRUD REST API với Spring Boot

## Giới thiệu

FruitApp là project backend REST API quản lý trái cây được xây dựng bằng:

- Java 17
- Spring Boot 3
- Spring Data JPA
- PostgreSQL
- Flyway Migration
- Spring Security
- RBAC (Role-Based Access Control)
- Swagger OpenAPI

Project hỗ trợ:

- CRUD Fruit
- DTO Pattern
- Database Migration bằng Flyway
- Authentication
- Authorization theo Role
- Swagger API Documentation

---

# Công nghệ sử dụng

| Công nghệ | Mô tả |
|---|---|
| Java 17 | Ngôn ngữ lập trình |
| Spring Boot 3 | Framework backend |
| Spring Data JPA | ORM thao tác database |
| PostgreSQL | Hệ quản trị cơ sở dữ liệu |
| Flyway | Database Migration |
| Spring Security | Authentication & Authorization |
| Swagger OpenAPI | API Documentation |
| Maven | Quản lý dependency |

---

# Chức năng chính

## Fruit CRUD API

- Thêm trái cây
- Xem danh sách trái cây
- Xem chi tiết trái cây
- Cập nhật trái cây
- Xóa trái cây

---

# RBAC (Role-Based Access Control)

Project sử dụng 2 role:

| Role | Quyền |
|---|---|
| USER | Chỉ xem dữ liệu |
| ADMIN | Full CRUD |

---

# Phân quyền API

| Method | Endpoint | USER | ADMIN |
|---|---|---|---|
| GET | /api/fruits | ✅ | ✅ |
| POST | /api/fruits | ❌ | ✅ |
| PUT | /api/fruits/{id} | ❌ | ✅ |
| DELETE | /api/fruits/{id} | ❌ | ✅ |

---

# Cấu trúc project

```text
src/main/java/com/example/fruitapp
│
├── config
│   └── SecurityConfig.java
│
├── controller
│   └── FruitController.java
│
├── dto
│   └── FruitDTO.java
│
├── entity
│   ├── Fruit.java
│   ├── Role.java
│   └── UserEntity.java
│
├── repository
│   ├── FruitRepository.java
│   └── UserRepository.java
│
├── security
│   └── CustomUserDetailsService.java
│
├── service
│   ├── FruitService.java
│   └── FruitServiceImpl.java
│
└── FruitappApplication.java
```

# Resource Structure

```text
src/main/resources
│
├── application.properties
│
├── db/migration
│   ├── V1__create_fruit_table.sql
│   ├── V2__insert_sample_data.sql
│   ├── V3__create_users_table.sql
│   └── V4__insert_default_users.sql
│
└── static
    └── index.html
```

