
--
-- Dumping data for table core_admin_right
--
INSERT INTO core_admin_right (id_right,name,level_right,admin_url,description,is_updatable,plugin_name,id_feature_group,icon_url,documentation_url) VALUES 
('PHRASEANET_MANAGEMENT','phraseanet.adminFeature.media_handler_management.name',1,'jsp/admin/plugins/phraseanet/ManageMediaHandlers.jsp','phraseanet.adminFeature.media_handler_management.description',0,'phraseanet','APPLICATIONS','images/admin/skin/plugins/phraseanet/phraseanet.png', NULL);

--
-- Dumping data for table core_user_right
--
INSERT INTO core_user_right (id_right,id_user) VALUES ('PHRASEANET_MANAGEMENT',1);

