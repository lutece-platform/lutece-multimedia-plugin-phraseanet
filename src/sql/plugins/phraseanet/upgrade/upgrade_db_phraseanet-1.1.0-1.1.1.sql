 /* Add the token Key for accounts*/
ALTER TABLE phraseanet_account 
ADD COLUMN token char(32) COLLATE utf8_unicode_ci DEFAULT NULL 
AFTER password;

/* Add a audio player template with dewplayer */
UPDATE phraseanet_template 
SET default_template ='<object type="application/x-shockwave-flash" data="plugins/phraseanet/swf/dewplayer/dewplayer-mini.swf?mp3=${embed.getEmbedItem(\'preview\').permalink.url}" width="160" height="20" id="dewplayer-mini"><param name="wmode" value="transparent" /><param name="movie" value="plugins/phraseanet/swf/dewplayer/dewplayer-mini.swf?mp3=${embed.getEmbedItem(\'preview\').permalink.url}" /></object>' 
WHERE id_template = 2 ;

/* Update the picture tag */
UPDATE phraseanet_template 
SET default_template ='<a href="${embed.getEmbedItem(\'preview\').permalink.url}" target="_blank" title="Agrandir"><img src="${embed.getEmbedItem(\'thumbnail\').permalink.url}" alt="Image : ${embed.getEmbedItem(\'preview\').permalink.label}" width="${embed.getEmbedItem(\'thumbnail\').width}px" height="${embed.getEmbedItem(\'thumbnail\').height}px" /></a>'
WHERE id_template = 3 ;