CREATE TABLE tb_users (
    id TEXT PRIMARY KEY UNIQUE,
    login TEXT UNIQUE NOT NULL,
    password TEXT UNIQUE NOT NULL,
    role TEXT NOT NULL
);