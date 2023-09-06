DROP TABLE answers;

CREATE TABLE questions
(
    id BIGSERIAL PRIMARY KEY,
    question VARCHAR(255) NOT NULL,
    test_id BIGINT NOT NULL REFERENCES public.tests(id) ON DELETE CASCADE
);

CREATE TABLE answers
(
    id BIGSERIAL PRIMARY KEY,
    choices VARCHAR(255) NOT NULL DEFAULT 'A',
    answer TEXT NOT NULL,
    question_id BIGINT NOT NULL REFERENCES public.questions(id) ON DELETE CASCADE
);
