--
-- Table structure for table phraseanet_account
--
DROP TABLE IF EXISTS phraseanet_account;
CREATE TABLE phraseanet_account
(
	id_account int NOT NULL,
	name varchar(255) NOT NULL,
	description varchar(255) default NULL,
  access_url varchar(255) default NULL,
  customer_id CHAR(32) NOT NULL,
	customer_secret CHAR(32) default NULL,
	autthorize_end_point varchar(255) default NULL,
	access_end_point varchar(255) default NULL,
	phraseanet_id varchar(255) default NULL,
	password varchar(255) default NULL,
	token CHAR(32) default NULL,
	PRIMARY KEY (id_account),
    UNIQUE (customer_id)
);

--
-- Table structure for table phraseanet_default_template
--
DROP TABLE IF EXISTS phraseanet_template;
CREATE TABLE phraseanet_template
(	
	id_template int NOT NULL,
	name varchar(255) NOT NULL,
	default_template TEXT default NULL,
	media_type varchar(255) NOT NULL,
	PRIMARY KEY (id_template)
);

--
-- Table structure for table phraseanet_media
--
DROP TABLE IF EXISTS phraseanet_media;
CREATE TABLE phraseanet_media
(
	id_media int NOT NULL,
	id_account int NOT NULL,
	media_name varchar(60) NOT NULL,
	media_description varchar(255) NOT NULL,
  url_icon varchar(255) default NULL,
  insert_template text default NULL,
	media_type varchar(60) default NULL,
	PRIMARY KEY (id_media)
);

ALTER TABLE phraseanet_media ADD CONSTRAINT FK_ID_ACCOUNT FOREIGN KEY (id_account) REFERENCES phraseanet_account (id_account);
