CREATE TABLE IF NOT EXISTS chats(
    id SERIAL PRIMARY KEY,
    name VARCHAR UNIQUE,
    owner_id INTEGER,
    last_updated_date TIMESTAMP NOT NULL DEFAULT NOW(),
    created_date TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY(owner_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS messages(
    id SERIAL PRIMARY KEY,
    content VARCHAR,
    chat_id INTEGER,
    owner_id INTEGER,
    last_updated_date TIMESTAMP NOT NULL DEFAULT NOW(),
    created_date TIMESTAMP NOT NULL DEFAULT NOW(),
    FOREIGN KEY(chat_id) REFERENCES chats(id),
    FOREIGN KEY(owner_id) REFERENCES users(id)
);

CREATE TABLE IF NOT EXISTS users_chats(
    user_id INTEGER,
    chat_id INTEGER,
    PRIMARY KEY(user_id, chat_id),
    FOREIGN KEY(user_id) REFERENCES users(id),
    FOREIGN KEY(chat_id) REFERENCES chats(id)
);