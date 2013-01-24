
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
VALUES (2, 'Template pour les audios', '', 'audio' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (3, 'Template pour les images', '<img src="${embed.getEmbedItem(\'preview\').permalink.url}" width="${embed.getEmbedItem(\'preview\').width}" height="${embed.getEmbedItem(\'preview\').height}" />', 'image' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (4, 'Template pour les flashs', '', 'flash' );
INSERT INTO phraseanet_template ( id_template, name, default_template, media_type)
VALUES (5, 'Template pour les documents', '', 'document' );
