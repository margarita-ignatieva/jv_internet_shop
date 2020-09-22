CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internet_shop`.`products` (
    `product_id` BIGINT         NOT NULL AUTO_INCREMENT,
    `product_name` VARCHAR(225) NOT NULL,
    `product_price` DOUBLE      NOT NULL,
    `deleted` boolean NOT NULL DEFAULT false,
    PRIMARY KEY (`product_id`));
