DROP SCHEMA if exists playground;

CREATE SCHEMA playground;
USE playground;

CREATE TABLE page (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
title VARCHAR(255),
parent_page_id INT,
INDEX (parent_page_id)
);

CREATE TABLE block (
id BIGINT AUTO_INCREMENT PRIMARY KEY,
type enum('PAGE', 'TEXT'),
content TEXT,
page_id BIGINT
);

# CREATE TABLE page_subpage (
# page_id INT NOT NULL,
# subpage_id INT NOT NULL,
# PRIMARY KEY (page_id, subpage_id),
# INDEX (page_id),
# INDEX (subpage_id),
# FOREIGN KEY (page_id) REFERENCES page(id),
# FOREIGN KEY (subpage_id) REFERENCES page(id)
# );
