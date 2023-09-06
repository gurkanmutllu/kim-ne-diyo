CREATE TABLE news
(
    id BIGSERIAL PRIMARY KEY,
    header TEXT NOT NULL,
    content TEXT NOT NULL,
    img_path TEXT
);