
--
-- Dumping data for table `phraseanet_templates`
--
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (1, 'Template pour les videos', '
<p style="text-align: center;">
  <a id="video" 
     class="phraseaPlayer" 
     style="width: ${embed.getEmbedItem("thumbnail").width}px; height:${embed.getEmbedItem("thumbnail").height}px; float: none;"
     href="${embed.getEmbedItem("preview").permalink.url}">
    <img src="${embed.getEmbedItem("thumbnail").permalink.url}" 
         alt="${embed.getEmbedItem("preview").permalink.label}" />
  </a>
</p>
', 'video' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (2, 'Template pour les audios', '<object type="application/x-shockwave-flash" data="plugins/phraseanet/swf/dewplayer/dewplayer-mini.swf?mp3=${embed.getEmbedItem(\'preview\').permalink.url}" width="160" height="20" id="dewplayer-mini"><param name="wmode" value="transparent" /><param name="movie" value="plugins/phraseanet/swf/dewplayer/dewplayer-mini.swf?mp3=${embed.getEmbedItem(\'preview\').permalink.url}" /></object>', 'audio' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (3, 'Template pour les images', '<a href="${embed.getEmbedItem(\'preview\').permalink.url}" target="_blank" title="Agrandir"><img src="${embed.getEmbedItem(\'thumbnail\').permalink.url}" alt="Image : ${embed.getEmbedItem(\'preview\').permalink.label}" width="${embed.getEmbedItem(\'thumbnail\').width}px" height="${embed.getEmbedItem(\'thumbnail\').height}px" /></a>', 'image' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (4, 'Template pour les flashs', '', 'flash' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (5, 'Template pour les documents', '', 'document' );
