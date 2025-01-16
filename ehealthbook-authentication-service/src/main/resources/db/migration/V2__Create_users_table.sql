CREATE TABLE users (
    id UUID PRIMARY KEY,
    email VARCHAR(100) NOT NULL UNIQUE,
    password VARCHAR(255) NOT NULL,
    role_id UUID NOT NULL,
    FOREIGN KEY (role_id) REFERENCES roles (id)
);