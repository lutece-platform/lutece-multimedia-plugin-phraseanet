
--
-- Dumping data for table `phraseanet_media`
--

INSERT INTO phraseanet_media (id_media, media_name, media_description, url_icon, insert_template, media_type, bases, default_width, default_height ) 
VALUES (1,'Image','Images de la collection ','images/admin/skin/plugins/phraseanet/image_icon.png','<img src="${embed.document.permalink.getURL()}" width="${width}" height="${height}" />', 'image', NULL, 400, 300 );
INSERT INTO `phraseanet_media` (`id_media`, `media_name`, `media_description`, `url_icon`, `insert_template`, `media_type`, `bases`, `default_width`, `default_height`) 
VALUES (2, 'Toute vidéo', 'Ensemble des vidéos disponible de la banque de contenu.', 'images/admin/skin/plugins/phraseanet/video_icon.png', '<!-- Version simple 1.0 -->\r\n<div id="video" style="width: ${embed.thumbnail.getWidth()}px; height:${embed.thumbnail.getHeight()}px;">\r\n  <a class="phraseaPlayer" \r\n     href="${embed.preview.permalink.getUrl()}">\r\n    <img src="${embed.thumbnail.permalink.getUrl()}" \r\n         alt="${embed.preview.permalink.getLabel()}" />\r\n  </a>\r\n</div>', 'video', '', 0, 0);
INSERT INTO `phraseanet_media` (`id_media`, `media_name`, `media_description`, `url_icon`, `insert_template`, `media_type`, `bases`, `default_width`, `default_height`) 
VALUES (3, 'Vidéo tous public', 'Vidéos utilisable sur Paris.fr', 'images/admin/skin/plugins/phraseanet/video_icon.png', '<div id="video" style="width: ${embed.thumbnail.getWidth()}px; height:${embed.thumbnail.getHeight()}px;">\r\n  <a class="phraseaPlayer" \r\n     href="${embed.preview.permalink.getUrl()}">\r\n    <img src="${embed.thumbnail.permalink.getUrl()}" \r\n         alt="${embed.preview.permalink.getLabel()}" />\r\n  </a>\r\n</div>', 'video', '54,57,59,60,62,63,64,65,68,69', 0, 0);
INSERT INTO `phraseanet_media` (`id_media`, `media_name`, `media_description`, `url_icon`, `insert_template`, `media_type`, `bases`, `default_width`, `default_height`) 
VALUES (4, 'Vidéo intranet', 'Vidéos diffusable uniquement en intranet', 'images/admin/skin/plugins/phraseanet/video_icon.png', '<div id="video" style="width: ${embed.thumbnail.getWidth()}px; height:${embed.thumbnail.getHeight()}px;">\r\n  <a class="phraseaPlayer" \r\n     href="${embed.preview.permalink.getUrl()}">\r\n    <img src="${embed.thumbnail.permalink.getUrl()}" \r\n         alt="${embed.preview.permalink.getLabel()}" />\r\n  </a>\r\n</div>', 'video', '58,55,70,72,76,79', 0, 0);
INSERT INTO phraseanet_media (id_media, media_name, media_description, url_icon, insert_template, media_type, bases, default_width, default_height) 
<<<<<<< .mine
VALUES (5,'Audio','Documents audio','images/admin/skin/plugins/phraseanet/audio_icon.png', 'player audio', 'audio', NULL, 400, 300 );
=======
VALUES (2,'Vidéo','Vidéos','images/admin/skin/plugins/phraseanet/video_icon.png', '<object id="home" width="${width}" height="${height}" data="plugins/phraseanet/flowplayer/flowplayer-3.2.7.swf" type="application/x-shockwave-flash"> <param name="movie" value="plugins/phraseanet/flowplayer/flowplayer-3.2.7.swf" /> <param name="allowfullscreen" value="true" /> <param name=autoplay value=false> <param name="flashvars" value=config={"clip":"${embed.getEmbedItem(\"document\").permalink.getUrl()}"} /></object>', 'video', NULL, 400, 300);
INSERT INTO phraseanet_media (id_media, media_name, media_description, url_icon, insert_template, media_type, bases, default_width, default_height) 
VALUES (3,'Audio','Documents audio','images/admin/skin/plugins/phraseanet/audio_icon.png', 'player audio', 'audio', NULL, 400, 300 );

>>>>>>> .r39368
