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
-- init datas for phraseanet_default_template
--
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (1, 'Template pour les videos', '<div id="video" style="width: ${embed.getEmbedItem(\'thumbnail\').width}px; height:${embed.getEmbedItem(\'thumbnail\').height}px;">\r\n  <a class="phraseaPlayer" \r\n     href="${embed.getEmbedItem(\'preview\').permalink.url}">\r\n    <img src="${embed.getEmbedItem(\'thumbnail\').permalink.url}" \r\n         alt="${embed.getEmbedItem(\'preview\').permalink.label}" />\r\n  </a>\r\n</div>', 'video' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (2, 'Template pour les audios', '<object type="application/x-shockwave-flash" data="plugins/phraseanet/swf/dewplayer/dewplayer-mini.swf?mp3=${embed.getEmbedItem(\'preview\').permalink.url}" width="160" height="20" id="dewplayer-mini"><param name="wmode" value="transparent" /><param name="movie" value="plugins/phraseanet/swf/dewplayer/dewplayer-mini.swf?mp3=${embed.getEmbedItem(\'preview\').permalink.url}" /></object>', 'audio' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (3, 'Template pour les images', '<img src="${embed.getEmbedItem(\'preview\').permalink.url}" width="${embed.getEmbedItem(\'preview\').width}" height="${embed.getEmbedItem(\'preview\').height}" />', 'image' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (4, 'Template pour les flashs', '', 'flash' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (5, 'Template pour les documents', '', 'document' );

TRUNCATE TABLE phraseanet_media;

ALTER TABLE phraseanet_media ADD COLUMN id_account int NOT NULL AFTER id_media;
ALTER TABLE phraseanet_media ADD CONSTRAINT FK_ID_ACCOUNT FOREIGN KEY (id_account) REFERENCES id_account (phraseanet_account);

ALTER TABLE phraseanet_media DROP bases;
ALTER TABLE phraseanet_media DROP default_width;
ALTER TABLE phraseanet_media DROP default_height;