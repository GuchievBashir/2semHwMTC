CREATE TABLE users (
                       id   BIGSERIAL PRIMARY KEY,
                       name VARCHAR(255) NOT NULL,
                       age  INTEGER
);

CREATE TABLE books (
                       id        BIGSERIAL PRIMARY KEY,
                       title     VARCHAR(255) NOT NULL,
                       author_id BIGINT NOT NULL,
                       CONSTRAINT fk_books_on_author FOREIGN KEY (author_id) REFERENCES users (id)
);

CREATE TABLE courses (
                         id   BIGSERIAL PRIMARY KEY,
                         name VARCHAR(255) NOT NULL
);

CREATE TABLE universities (
                              id   BIGSERIAL PRIMARY KEY,
                              name VARCHAR(255) NOT NULL
);