-- Creating table app_user
CREATE TABLE IF NOT EXISTS app_user
(
    app_user_id        BIGINT      NOT NULL,
    app_user_name      varchar(50) NOT NULL,
    app_user_email     varchar(50) NOT NULL,
    app_user_image_uri varchar(36) NOT NULL,
    app_user_status    varchar(7) default 'Offline',
    PRIMARY KEY (app_user_id),
    CONSTRAINT unique_app_user_name UNIQUE (app_user_name)
);

CREATE SEQUENCE app_user_sequence START 1 INCREMENT 5;

CREATE INDEX index_app_user_email ON app_user (app_user_email);