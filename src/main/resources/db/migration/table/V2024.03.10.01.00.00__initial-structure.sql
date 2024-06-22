CREATE TABLE expense (
    id                      VARCHAR(255) PRIMARY KEY,
    description             VARCHAR(255) NOT NULL,
    amount                  DECIMAL(10, 2) NOT NULL,
    budget_category_id      VARCHAR(255) NOT NULL,
    user_id                 VARCHAR(255) NOT NULL,
    creation_timestamp      TIMESTAMP NOT NULL,
    modification_timestamp  TIMESTAMP NOT NULL
);
