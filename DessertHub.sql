CREATE TABLE USER (
    USER_ID VARCHAR(20) NOT NULL,
    USER_PW VARCHAR(20) NOT NULL,
    USER_EM VARCHAR(50) NOT NULL,
    USER_NN VARCHAR(20) NOT NULL, 
    USER_PI BLOB,                     

    CONSTRAINT USER_PK PRIMARY KEY(USER_ID) 
);

CREATE TABLE POST (
    POST_NUM INTEGER NOT NULL,               
    USER_ID VARCHAR(20) NOT NULL,            
    USER_NN VARCHAR(20) NOT NULL,            
    POST_TITLE VARCHAR(50) NOT NULL,         
    POST_BODY TEXT,                          
    POST_IMG BLOB,                           
    POST_LIKED INTEGER DEFAULT 0,            
    POST_COMMENT INTEGER DEFAULT 0,          
    POST_REGDATE DATE DEFAULT CURRENT_DATE,  

    CONSTRAINT POST_PK PRIMARY KEY(POST_NUM),

    CONSTRAINT FK_USER_ID FOREIGN KEY(USER_ID) REFERENCES users(USER_ID),
    CONSTRAINT FK_USER_NN FOREIGN KEY(USER_NN) REFERENCES users(USER_NN) 
);

CREATE TABLE GALLERY (
    G_NUM INTEGER NOT NULL,              
    USER_ID VARCHAR(20) NOT NULL,        
    USER_NN VARCHAR(20) NOT NULL,        
    G_TITLE VARCHAR(50) NOT NULL,        
    G_HASHTAG VARCHAR(200),              
    G_IMG BLOB NOT NULL,                 
    G_LIKED INTEGER DEFAULT 0,           
    G_REGDATE DATE DEFAULT CURRENT_DATE, 

    CONSTRAINT GALLERY_PK PRIMARY KEY(G_NUM),    

    CONSTRAINT FK_USER_ID FOREIGN KEY(USER_ID) REFERENCES users(USER_ID),
    CONSTRAINT FK_USER_NN FOREIGN KEY(USER_NN) REFERENCES users(USER_NN) 
);

CREATE TABLE COMMENT (
    C_NUM INTEGER NOT NULL,               
    POST_NUM INTEGER NOT NULL,            
    USER_ID VARCHAR(20) NOT NULL,         
    USER_PW VARCHAR(20) NOT NULL,         
    C_BODY TEXT,                          
    C_REGDATE DATE DEFAULT CURRENT_DATE,  

    CONSTRAINT COMMENT_PK PRIMARY KEY(C_NUM), 

    CONSTRAINT FK_POST_NUM FOREIGN KEY(POST_NUM) REFERENCES posts(POST_NUM),
    CONSTRAINT FK_USER_ID FOREIGN KEY(USER_ID) REFERENCES users(USER_ID),   
    CONSTRAINT FK_USER_PW FOREIGN KEY(USER_PW) REFERENCES users(USER_NN)    
);

CREATE TABLE FAVORITE (
    USER_ID VARCHAR(20) NOT NULL,         
    ARTICLE_NUM INTEGER NOT NULL,         
    ARTICLE_TYPE VARCHAR(1) NOT NULL,     

    CONSTRAINT FK_USER_ID FOREIGN KEY(USER_ID) REFERENCES users(USER_ID)
);

CREATE TABLE DESSERTS (
    D_INDEX INTEGER NOT NULL,         
    D_NAME VARCHAR(20) NOT NULL,      
    D_IMAGE BLOB,                    
    D_CHARA VARCHAR(400),            

    CONSTRAINT D_PK PRIMARY KEY(D_INDEX) 
);