DROP DATABASE IF EXISTS solilo;

CREATE DATABASE solilo;

USE solilo;
 
/* create table */
CREATE TABLE quicky (
	created DATETIME PRIMARY KEY,
	message MEDIUMTEXT NOT NULL,
	modified BOOLEAN DEFAULT false,
	visible BOOLEAN DEFAULT true,
	sentiment INTEGER DEFAULT 2
);

/* insert datetime as yyyy-mm-dd hh:mm:ss (yyyy-MM-dd %h:%m:%s) */
/* insert some data */

INSERT INTO quicky values ("2022-03-01 15:27:15","Hello World", false, true,3);
