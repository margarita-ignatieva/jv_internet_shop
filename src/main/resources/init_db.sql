CREATE SCHEMA `internet_shop` DEFAULT CHARACTER SET utf8;

CREATE TABLE `internet_shop`.`products` (
    `product_id` BIGINT         NOT NULL AUTO_INCREMENT,
    `product_name` VARCHAR(225) NOT NULL,
    `product_price` DOUBLE      NOT NULL,
    `deleted` boolean NOT NULL DEFAULT false,
    PRIMARY KEY (`product_id`));

CREATE TABLE `internet_shop`.`users` (
    `user_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_name` VARCHAR(255) NOT NULL,
    `user_login` VARCHAR(255) NOT NULL,
    `user_password` VARCHAR(255) NULL,
    `salt` BINARY(16) NOT NULL,
    `deleted` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (id),
    UNIQUE (login)

CREATE TABLE `internet_shop`.`user_roles` (
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(11) NOT NULL,
    `role_id` BIGINT(11) NOT NULL,
     PRIMARY KEY (`id`),
     FOREIGN KEY (user_id)
         REFERENCES public.users (id) MATCH SIMPLE
         ON UPDATE NO ACTION
         ON DELETE NO ACTION
     FOREIGN KEY (role_id)
         REFERENCES public.roles (id) MATCH SIMPLE
         ON UPDATE NO ACTION
         ON DELETE NO ACTION
);

CREATE TABLE `internet_shop`.`shopping_carts` (
    `cart_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(11) NOT NULL,
    `deleted` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`cart_id`),
    FOREIGN KEY (`user_id`)
         REFERENCES `internet_shop`.`users` (`user_id`)
         ON DELETE NO ACTION
         ON UPDATE NO ACTION
);

CREATE TABLE `internet_shop`.`shopping_cart_products` (
    `cart_id` BIGINT(11) NOT NULL,
    `product_id` BIGINT(11) NOT NULL,
     FOREIGN KEY (cart_id)
         REFERENCES public.shopping_carts (id)
         ON UPDATE NO ACTION
         ON DELETE NO ACTION,
     FOREIGN KEY (product_id)
         REFERENCES public.products (id)
         ON UPDATE NO ACTION
         ON DELETE NO ACTION
);

CREATE TABLE `internet_shop`.`orders` (
    `order_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `user_id` BIGINT(11) NOT NULL,
    `deleted` TINYINT NOT NULL DEFAULT 0,
    PRIMARY KEY (`order_id`),
    FOREIGN KEY (`user_id`)
        REFERENCES `internet_shop`.`users` (`user_id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE TABLE `internet_shop`.`order_products` (
    `id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `order_id` BIGINT(11) NOT NULL,
    `product_id` BIGINT(11) NOT NULL,PRIMARY KEY (`id`),
    FOREIGN KEY (`product_id`)
        REFERENCES `internet_shop`.`products` (`product_id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION,
    FOREIGN KEY (`order_id`)
        REFERENCES `internet_shop`.`orders` (`order_id`)
        ON DELETE NO ACTION
        ON UPDATE NO ACTION
);

CREATE TABLE `internet_shop`.`roles` (
    `role_id` BIGINT(11) NOT NULL AUTO_INCREMENT,
    `role_name` VARCHAR(255) NOT NULL,
    PRIMARY KEY (`role_id`));

INSERT INTO roles(role_name)
VALUES ('ADMIN'), ('USER');
