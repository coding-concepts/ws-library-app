
CREATE TABLE BOOK (
ID BIGINT NOT NULL  AUTO_INCREMENT, -- BOOK ID
TITLE VARCHAR(255) NOT NULL,
PRIMARY KEY (ID)
);

ALTER TABLE BOOK ADD COLUMN AUTHOR VARCHAR(255) NOT NULL;

ALTER TABLE BOOK ADD  UNIQUE (TITLE);
