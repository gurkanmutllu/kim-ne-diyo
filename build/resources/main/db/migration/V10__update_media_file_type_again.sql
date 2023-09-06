DROP TABLE news;

CREATE TABLE news
(
    id BIGSERIAL PRIMARY KEY,
    header TEXT NOT NULL,
    content TEXT NOT NULL,
    media_file BYTEA
);