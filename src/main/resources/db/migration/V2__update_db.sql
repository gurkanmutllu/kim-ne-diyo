ALTER TABLE api_user
    ADD COLUMN user_name TEXT NOT NULL,
    ADD COLUMN user_surname TEXT NOT NULL,
    ADD COLUMN user_mail TEXT NOT NULL,
    ADD COLUMN user_phone TEXT NOT NULL;
