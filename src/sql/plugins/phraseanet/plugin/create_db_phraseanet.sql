--
-- Table structure for table phraseanet_media
--
DROP TABLE IF EXISTS phraseanet_media;
CREATE TABLE phraseanet_media
(
	id_media int NOT NULL,
	media_name varchar(60) NOT NULL,
	media_description varchar(255) NOT NULL,
        url_icon varchar(255) default NULL,
        insert_template long varchar default NULL,
	media_type varchar(60) default NULL,
	bases varchar(60) default NULL,
	default_width int default NULL,
	default_height int default NULL,
	PRIMARY KEY (id_media)
);

