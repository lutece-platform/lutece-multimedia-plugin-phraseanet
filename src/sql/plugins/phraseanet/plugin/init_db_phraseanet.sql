
--
-- Dumping data for table `phraseanet_dimension`
--
INSERT INTO phraseanet_dimension (id_dimension, dimension_name, dimension_width, dimension_height) VALUES (1,'260 x 180',260,180);

--
-- Dumping data for table `phraseanet_media`
--
INSERT INTO phraseanet_media (id_media, media_name, media_description, url_icon, insert_template, media_type, bases, default_width, default_height ) 
VALUES (1,'Image','Images de la collection ','images/admin/skin/plugins/phraseanet/image_icon.png','<img src="${url}" width="${width}" height="${height}" />', 'image', NULL, 400, 300 );
INSERT INTO phraseanet_media (id_media, media_name, media_description, url_icon, insert_template, media_type, bases, default_width, default_height) 
VALUES (2,'Vidéo','Vidéos','images/admin/skin/plugins/phraseanet/video_icon.png', '<object id="home" width="${width}" height="${height}" data="plugins/phraseanet/flowplayer/flowplayer-3.2.7.swf" type="application/x-shockwave-flash"> <param name="movie" value="plugins/phraseanet/flowplayer/flowplayer-3.2.7.swf" /> <param name="allowfullscreen" value="true" /> <param name=autoplay value=false> <param name="flashvars" value=config={"clip":"${embed.document.permalink.getURL()}"} /></object>', 'video', NULL, 400, 300);
INSERT INTO phraseanet_media (id_media, media_name, media_description, url_icon, insert_template, media_type, bases, default_width, default_height) 
VALUES (3,'Audio','Documents audio','images/admin/skin/plugins/phraseanet/audio_icon.png', 'player audio', 'audio', NULL, 400, 300 );

--
-- Dumping data for table `phraseanet_media_dimension`
--
INSERT INTO phraseanet_media_dimension (id_media, id_dimension) VALUES (1,1);
