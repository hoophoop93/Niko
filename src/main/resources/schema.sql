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

CREATE TABLE IF NOT EXISTS moods (
    mood_id SERIAL PRIMARY KEY,
    mood INTEGER NOT NULL,
    dateAdd DATE NOT NULL,
    user_id INTEGER references users(user_id) NOT NULL,
    project_id INTEGER references projects(project_id) NOT NULL
);