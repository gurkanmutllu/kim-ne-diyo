CREATE TABLE tests
(
    id BIGSERIAL PRIMARY KEY,
    test_name TEXT NOT NULL,
    content TEXT NOT NULL,
    media_file BYTEA,
    user_id BIGINT  NOT NULL REFERENCES public.users(id)
);

CREATE TABLE answers
(
    id BIGSERIAL PRIMARY KEY,
    choices VARCHAR(255) NOT NULL DEFAULT 'A',
    answer TEXT NOT NULL,
    tests_id BIGINT NOT NULL REFERENCES public.tests(id) ON DELETE CASCADE
);
