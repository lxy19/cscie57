CREATE USER IF NOT EXISTS 'lix8'@'localhost' IDENTIFIED BY '1234';

DROP DATABASE IF EXISTS BOOKDB;

CREATE DATABASE BOOKDB;
USE BOOKDB;
GRANT ALL PRIVILEGES ON BOOKDB.* TO 'lix8'@'localhost';
FLUSH PRIVILEGES;

/*in case of java.sql.SQLException: The server timezone value 'UTC' is unrecognized or represents more than one timezone. */
SET GLOBAL time_zone = '+5:00';