CREATE TABLE IF NOT EXISTS users (
    user_id SERIAL PRIMARY KEY,
    email text NOT NULL,
    password_hash text NOT NULL,
    display_name varchar(32) NOT NULL
);

CREATE TABLE IF NOT EXISTS projects (
    project_id SERIAL PRIMARY KEY,
    title text NOT NULL,
    project_owner integer references users(user_id) NOT NULL
);