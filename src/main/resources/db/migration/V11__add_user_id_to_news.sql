ALTER TABLE public.news
    ADD COLUMN user_id BIGINT  NOT NULL REFERENCES public.users(id) ON DELETE CASCADE;