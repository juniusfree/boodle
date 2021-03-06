CREATE USER ADMIN WITH PASSWORD 'admin';

CREATE TABLE categories (
    id serial PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    monthly_budget NUMERIC(10, 2)
);

CREATE TABLE expenses (
    id serial PRIMARY KEY,
    DATE DATE,
    id_category INTEGER REFERENCES categories (id),
    item VARCHAR(1000),
    amount NUMERIC(10, 2),
    from_savings BOOLEAN
);

CREATE TABLE aims (
    id serial PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    target NUMERIC(10, 2) NOT NULL,
    achieved BOOLEAN DEFAULT FALSE,
    achieved_on DATE
);

CREATE TABLE transactions (
    id serial PRIMARY KEY,
    id_aim INTEGER REFERENCES aims (id),
    item VARCHAR(1000),
    amount NUMERIC(10, 2),
    DATE DATE
);

CREATE TABLE savings (
    id serial PRIMARY KEY,
    item VARCHAR(1000),
    amount NUMERIC(10, 2),
    date DATE
);

CREATE TABLE funds (
    id serial PRIMARY KEY,
    name VARCHAR(30) NOT NULL,
    amount NUMERIC(10, 2),
    date DATE
);

GRANT ALL PRIVILEGES ON DATABASE "boodle" TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE categories TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE expenses TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE aims TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE transactions TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE savings TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE funds TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE categories_id_seq TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE expenses_id_seq TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE aims_id_seq TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE transactions_id_seq TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE savings_id_seq TO ADMIN;

GRANT ALL PRIVILEGES ON TABLE funds_id_seq TO ADMIN;

GRANT postgres TO ADMIN;
