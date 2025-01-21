CREATE DATABASE desserthub
    DEFAULT CHARACTER SET = 'utf8mb4';

CREATE TABLE gallery(  
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    user_id VARCHAR(255),
    user_nn VARCHAR(255),
    gallery_title VARCHAR(255),
    gallery_hashtag VARCHAR(255),
    gallery_img LONGTEXT,
    gallery_liked INT DEFAULT 0,
    gallery_writeday DATETIME
) COMMENT '';
CREATE TABLE board(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    user_id VARCHAR(255),
    user_nn VARCHAR(255),
    board_category VARCHAR(255),
    board_title VARCHAR(255),
    board_content TEXT,
    board_img LONGTEXT,
    board_writeday DATETIME,
    board_view INT DEFAULT 0,
    board_liked INT DEFAULT 0,
    board_comment INT DEFAULT 0
) COMMENT '';

CREATE TABLE user(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    user_id VARCHAR(255),
    user_pw VARCHAR(255),
    user_em VARCHAR(255),
    user_nn VARCHAR(255),
    user_pi LONGTEXT
) COMMENT '';

CREATE TABLE dessert(
    id int NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    dessert_name VARCHAR(255),
    dessert_img LONGTEXT,
    dessert_chara VARCHAR(255)
) COMMENT '';

SELECT * FROM gallery;
SELECT * FROM board;

commit;