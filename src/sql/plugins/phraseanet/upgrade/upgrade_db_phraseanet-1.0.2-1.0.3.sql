--
-- Table structure for table phraseanet_account
--
DROP TABLE IF EXISTS phraseanet_account;
CREATE TABLE phraseanet_account
(
	id_account int NOT NULL,
	name varchar(255) NOT NULL,
	description varchar(255) NOT NULL,
    access_url varchar(255) NOT NULL,
    customer_id varchar(255) NOT NULL,
	customer_secret varchar(255) NOT NULL,
	autthorize_end_point varchar(255) NOT NULL,
	access_end_point varchar(255) NOT NULL,
	phraseanet_id varchar(255) NOT NULL,
	password varchar(255) NOT NULL,
	PRIMARY KEY (id_account)
);

--
-- Table structure for table phraseanet_default_template
--
DROP TABLE IF EXISTS phraseanet_template;
CREATE TABLE phraseanet_template
(	
	id_template int NOT NULL,
	name varchar(255) NOT NULL,
	default_template long varchar default NULL,
	media_type varchar(255) NOT NULL,
	PRIMARY KEY (id_template)
);

--
-- init datas for phraseanet_default_template
--
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (1, 'Template pour les videos', '<div id="video" style="width: ${embed.getEmbedItem(\'thumbnail\').width}px; height:${embed.getEmbedItem(\'thumbnail\').height}px;">\r\n  <a class="phraseaPlayer" \r\n     href="${embed.getEmbedItem(\'preview\').permalink.url}">\r\n    <img src="${embed.getEmbedItem(\'thumbnail\').permalink.url}" \r\n         alt="${embed.getEmbedItem(\'preview\').permalink.label}" />\r\n  </a>\r\n</div>', 'video' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (2, 'Template pour les audios', '', 'audio' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (3, 'Template pour les images', '<img src="${embed.getEmbedItem(\'document\').permalink.Url}" width="${embed.getEmbedItem(\'document\').width}" height="${embed.getEmbedItem(\'document\').height}" />', 'image' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (4, 'Template pour les flashs', '', 'flash' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (5, 'Template pour les documents', '', 'document' );

TRUNCATE TABLE phraseanet_media;

ALTER TABLE phraseanet_media ADD COLUMN id_account int NOT NULL AFTER id_media;
ALTER TABLE phraseanet_media ADD CONSTRAINT FK_ID_ACCOUNT FOREIGN KEY (id_account) REFERENCES ods_acte (phraseanet_media);

ALTER TABLE phraseanet_media DROP bases;
ALTER TABLE phraseanet_media DROP default_width;
ALTER TABLE phraseanet_media DROP default_height;