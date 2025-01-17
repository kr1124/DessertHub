CREATE TABLE gallery(  
    gallery_id int NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    user_id VARCHAR(255),
    user_nn VARCHAR(255),
    gallery_title VARCHAR(255),
    gallery_hashtag VARCHAR(255),
    gallery_img TEXT,
    gallery_liked INT,
    gallery_writeday DATETIME
) COMMENT '';
CREATE TABLE board(
    board_code int NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    user_id VARCHAR(255),
    user_nn VARCHAR(255),
    board_title VARCHAR(255),
    board_content TEXT,
    board_img TEXT,
    board_writeday DATETIME,
    board_liked INT,
    board_comment INT
) COMMENT '';

CREATE TABLE user(
    user_code int NOT NULL PRIMARY KEY AUTO_INCREMENT COMMENT 'Primary Key',
    user_id VARCHAR(255),
    user_pw VARCHAR(255),
    user_em VARCHAR(255),
    user_nn VARCHAR(255),
    user_pi TEXT
) COMMENT '';

SELECT * FROM gallery;
SELECT * FROM board;

commit;