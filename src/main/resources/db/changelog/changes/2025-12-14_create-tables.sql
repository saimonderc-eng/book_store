-- liquibase formatted sql

--changeset magamed:1
CREATE TABLE orders
(
    ID          BIGSERIAL PRIMARY KEY,
    TOTAL_PRICE DOUBLE PRECISION NOT NULL,
    AUTHOR_ID   BIGINT           NOT NULL,
    FOREIGN KEY (AUTHOR_ID) REFERENCES users (ID)
);

--changeset magamed:2
CREATE TABLE order_books
(
    ORDER_ID BIGINT NOT NULL,
    BOOK_ID  BIGINT NOT NULL,
    FOREIGN KEY (ORDER_ID) REFERENCES orders(ID),
    FOREIGN KEY (BOOK_ID) REFERENCES books(ID)
);