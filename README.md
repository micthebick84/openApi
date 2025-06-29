# Spring Boot OpenAPI 예제 프로젝트

이 프로젝트는 Spring Boot와 OpenAPI 3.0을 사용한 REST API 예제입니다.

## 프로젝트 구조

```
src/
├── main/
│   ├── java/
│   │   └── com/example/openapi/
│   │       ├── OpenApiApplication.java          # 메인 애플리케이션 클래스
│   │       ├── config/
│   │       │   └── OpenApiConfig.java          # OpenAPI 설정
│   │       ├── controller/
│   │       │   └── UserController.java         # 사용자 관리 REST API
│   │       ├── dto/
│   │       │   ├── UserDto.java                # 사용자 DTO
│   │       │   └── ApiResponse.java            # 공통 응답 DTO
│   │       └── exception/
│   │           └── GlobalExceptionHandler.java # 전역 예외 처리
│   └── resources/
│       └── application.yml                     # 애플리케이션 설정
└── test/
    └── java/
        └── com/example/openapi/                # 테스트 코드
```

## 주요 기능

- **사용자 관리 API**: CRUD 작업을 지원하는 REST API
- **OpenAPI 문서화**: Swagger UI를 통한 API 문서 자동 생성
- **데이터 검증**: Bean Validation을 통한 입력 데이터 검증
- **예외 처리**: 전역 예외 처리 및 표준화된 에러 응답
- **응답 표준화**: 일관된 API 응답 형식

## API 엔드포인트

### 사용자 관리 API

| 메서드 | 엔드포인트 | 설명 |
|--------|------------|------|
| GET | `/api/users` | 모든 사용자 조회 |
| GET | `/api/users/{id}` | 특정 사용자 조회 |
| POST | `/api/users` | 새 사용자 생성 |
| PUT | `/api/users/{id}` | 사용자 정보 수정 |
| DELETE | `/api/users/{id}` | 사용자 삭제 |
| GET | `/api/users/search?name={name}` | 이름으로 사용자 검색 |

## 실행 방법

### 1. Java 17 이상 설치
```bash
java -version
```

### 2. Maven 설치 (선택사항)
```bash
mvn -version
```

### 3. 프로젝트 실행
```bash
# Maven을 사용하는 경우
mvn spring-boot:run

# 또는 IDE에서 OpenApiApplication.java 실행
```

### 4. 애플리케이션 접속
- 애플리케이션: http://localhost:8080
- Swagger UI: http://localhost:8080/swagger-ui.html
- API 문서: http://localhost:8080/api-docs

## API 사용 예제

### 사용자 생성
```bash
curl -X POST http://localhost:8080/api/users \
  -H "Content-Type: application/json" \
  -d '{
    "name": "새사용자",
    "email": "newuser@example.com",
    "age": 25
  }'
```

### 사용자 조회
```bash
curl -X GET http://localhost:8080/api/users/1
```

### 사용자 목록 조회
```bash
curl -X GET http://localhost:8080/api/users
```

## 기술 스택

- **Spring Boot 3.2.0**: 웹 애플리케이션 프레임워크
- **SpringDoc OpenAPI 2.2.0**: OpenAPI 3.0 문서화
- **Bean Validation**: 데이터 검증
- **Maven**: 빌드 도구
- **Java 17**: 프로그래밍 언어

## 개발 환경 설정

### IDE 설정
- IntelliJ IDEA, Eclipse, VS Code 등 지원
- Spring Boot DevTools로 자동 재시작 지원

### 로깅
- DEBUG 레벨로 상세한 로그 출력
- 콘솔과 파일 로깅 지원

## 라이선스

MIT License 