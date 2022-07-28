CREATE TABLE IF NOT EXISTS supplier
(
    id   INT PRIMARY KEY AUTO_INCREMENT,
    name VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS product_category
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(256) NOT NULL,
    description VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS customer
(
    id            INT PRIMARY KEY AUTO_INCREMENT,
    first_name    VARCHAR(256) NOT NULL,
    last_name     VARCHAR(256) NOT NULL,
    username      VARCHAR(256) NOT NULL,
    password      VARCHAR(256) NOT NULL,
    email_address VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS product
(
    id          INT PRIMARY KEY AUTO_INCREMENT,
    name        VARCHAR(256) NOT NULL,
    description VARCHAR(256) NOT NULL,
    price       BIGINT       NOT NULL,
    weight      DOUBLE       NOT NULL,
    supplier    INT          NOT NULL REFERENCES supplier (id),
    category    INT          NOT NULL REFERENCES product_category (id),
    image_url   VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS location
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    name           VARCHAR(256) NOT NULL,
    country        VARCHAR(256) NOT NULL,
    city           VARCHAR(256) NOT NULL,
    county         VARCHAR(256) NOT NULL,
    street_address VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS stock
(
    product  INT NOT NULL REFERENCES product (id),
    location INT NOT NULL REFERENCES location (id),
    quantity INT NOT NULL CHECK (quantity >= 0),
    CONSTRAINT PK_stock UNIQUE (product, location)
);

CREATE TABLE IF NOT EXISTS revenue
(
    id       INT PRIMARY KEY AUTO_INCREMENT,
    location INT    NOT NULL REFERENCES location (id),
    date     DATE   NOT NULL,
    sum      DOUBLE NOT NULL
);

CREATE TABLE IF NOT EXISTS order_product
(
    id             INT PRIMARY KEY AUTO_INCREMENT,
    product        INT          NOT NULL REFERENCES product (id),
    shipped_from   INT          NOT NULL REFERENCES location (id),
    customer       INT          NOT NULL REFERENCES customer (id),
    created_at     DATETIME     NOT NULL,
    country        VARCHAR(256) NOT NULL,
    city           VARCHAR(256) NOT NULL,
    county         VARCHAR(256) NOT NULL,
    street_address VARCHAR(256) NOT NULL
);

CREATE TABLE IF NOT EXISTS order_detail
(
    order_product INT NOT NULL REFERENCES order_product (id),
    product       INT NOT NULL REFERENCES product (id),
    quantity      INT NOT NULL CHECK (quantity > 0),
    CONSTRAINT PK_order_detail UNIQUE (order_product, product)
);