ALTER TABLE public.users
    ADD COLUMN role VARCHAR(255) NOT NULL DEFAULT 'USER';

ALTER TABLE public.news
    ADD COLUMN user_id BIGINT  NOT NULL REFERENCES public.users(id) ON DELETE CASCADE;
