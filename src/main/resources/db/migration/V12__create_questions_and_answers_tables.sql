ALTER TABLE questions
    ADD CONSTRAINT FK_QUESTIONS_ON_TESTS FOREIGN KEY (test_id)
                       REFERENCES tests(id);

ALTER TABLE answers
    ADD CONSTRAINT FK_ANSWERS_ON_QUESTIONS FOREIGN KEY (question_id)
        REFERENCES questions(id);
