-- MariaDB users 테이블 생성 스크립트

-- netis 데이터베이스 사용
USE netis;

-- users 테이블이 존재하면 삭제
DROP TABLE IF EXISTS users;

-- users 테이블 생성
CREATE TABLE users (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    email VARCHAR(255) NOT NULL UNIQUE,
    age INT NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
);

-- 샘플 데이터 삽입
INSERT INTO users (name, email, age) VALUES 
('홍길동', 'hong@example.com', 25),
('김철수', 'kim@example.com', 30),
('이영희', 'lee@example.com', 28);

-- 인덱스 생성
CREATE INDEX idx_users_email ON users(email);
CREATE INDEX idx_users_name ON users(name); 