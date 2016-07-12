CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    email text NOT NULL,
    password_hash text NOT NULL,
    display_name varchar(32) NOT NULL
);