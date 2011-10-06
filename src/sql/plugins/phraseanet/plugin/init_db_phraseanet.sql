
--
-- Dumping data for table `phraseanet_dimension`
--
INSERT INTO phraseanet_dimension (id_dimension, dimension_name, dimension_width, dimension_height) VALUES (1,'260 x 180',260,180);

--
-- Dumping data for table `phraseanet_media`
--
INSERT INTO phraseanet_media (id_media, media_name, media_description, url_icon, insert_template) VALUES (1,'Vidéo','Vidéo',NULL,NULL);

--
-- Dumping data for table `phraseanet_media_dimension`
--
INSERT INTO phraseanet_media_dimension (id_media, id_dimension) VALUES (1,1);
