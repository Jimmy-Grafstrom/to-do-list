CREATE TABLE grocery_list
(
    id        BIGSERIAL PRIMARY KEY,
    name      VARCHAR(255) NOT NULL,
    quantity  INT          NOT NULL,
    category  VARCHAR(255),
    purchased BOOLEAN      NOT NULL
);