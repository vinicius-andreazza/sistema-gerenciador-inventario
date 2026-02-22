USE sgidb;

-- ========================
-- USERS
-- ========================
INSERT INTO T_SGI_USERS (nm_user, ds_password, ds_role) VALUES
('admin', '$2a$12$yC4FVA1Yvitp6v9ihpmbO.ugREdtNI0xc/KBwk0vOqZfnMPKBrFr6', 'ROLE_ADMIN'),
('vinicius', '$2a$12$GbTFUFlg14ButSLvPBIVyOIrUS7eLaViUO9wsjaWpqtVQyJueQ3v.', 'ROLE_USER'),
('pedro', '$2a$12$bpE6vfIF9mRguboS4uLXYueWcisYT5A0gZXxzl3NdlIFXpR2Bhtoa', 'ROLE_USER'),
('felipe', '$2a$12$6PPnOPKlNKBTX51Sv1.jUOEOX/JxSvuYmPIMId8m4TXqS4d2gcGpm', 'ROLE_USER'),
('julio', '$2a$12$rdrXUsDOcLhg.NzyNDxLGOh2LbLukv4esXr2qQ37CNZ2zubR/ge6O', 'ROLE_USER');

-- ========================
-- SUPPLIERS
-- ========================
INSERT INTO T_SGI_SUPPLIER (nm_supplier, ds_phone, ds_email) VALUES
('Fornecedor Alpha', '(47) 999990001', 'alpha@email.com'),
('Fornecedor Beta', '(47) 999990002', 'beta@email.com'),
('Fornecedor Gamma', '(47) 999990003', 'gamma@email.com'),
('Fornecedor Delta', '(47) 999990004', 'delta@email.com'),
('Fornecedor Omega', '(47) 999990005', 'omega@email.com');

-- ========================
-- LOCALS (20)
-- ========================
INSERT INTO T_SGI_LOCAL (nm_sector, nr_position, ds_shelf) VALUES
('A',1,'A1'),('A',2,'A2'),('A',3,'A3'),('A',4,'A4'),('A',5,'A5'),
('B',1,'B1'),('B',2,'B2'),('B',3,'B3'),('B',4,'B4'),('B',5,'B5'),
('C',1,'C1'),('C',2,'C2'),('C',3,'C3'),('C',4,'C4'),('C',5,'C5'),
('D',1,'D1'),('D',2,'D2'),('D',3,'D3'),('D',4,'D4'),('D',5,'D5');

-- ========================
-- ITEMS (20 PRODUTOS)
-- ========================
INSERT INTO T_SGI_ITEM
(id_item, nm_item, ds_category, tp_item, ds_item, nr_quantity, nr_minimium_quantity, ds_measure, st_item, id_user, id_local, vl_value)
VALUES
(1,'Produto 1','ELETRÔNICOS','PRODUTO','Produto eletrônico 1',5,10,'UN','ATIVO',1,1,100),
(2,'Produto 2','ELETRÔNICOS','PRODUTO','Produto eletrônico 2',40,10,'UN','ATIVO',2,2,120),
(3,'Produto 3','ELETRÔNICOS','PRODUTO','Produto eletrônico 3',9,10,'UN','ATIVO',3,3,140),
(4,'Produto 4','ELETRÔNICOS','PRODUTO','Produto eletrônico 4',20,5,'UN','ATIVO',4,4,160),
(5,'Produto 5','ELETRÔNICOS','PRODUTO','Produto eletrônico 5',10,5,'UN','ATIVO',5,5,180),
(6,'Produto 6','MADEIRA','PRODUTO','Produto móveis 6',60,10,'UN','ATIVO',1,6,200),
(7,'Produto 7','MADEIRA','PRODUTO','Produto móveis 7',70,10,'UN','ATIVO',2,7,220),
(8,'Produto 8','MADEIRA','PRODUTO','Produto móveis 8',80,10,'UN','ATIVO',3,8,240),
(9,'Produto 9','MADEIRA','PRODUTO','Produto móveis 9',90,10,'UN','ATIVO',4,9,260),
(10,'Produto 10','MADEIRA','PRODUTO','Produto móveis 10',100,10,'UN','ATIVO',5,10,280),
(11,'Produto 11','QUÍMICOS','PRODUTO','Produto limpeza 11',25,35,'UN','ATIVO',1,11,300),
(12,'Produto 12','QUÍMICOS','PRODUTO','Produto limpeza 12',35,5,'UN','ATIVO',2,12,320),
(13,'Produto 13','QUÍMICOS','PRODUTO','Produto limpeza 13',45,5,'UN','ATIVO',3,13,340),
(14,'Produto 14','QUÍMICOS','PRODUTO','Produto limpeza 14',55,5,'UN','ATIVO',4,14,360),
(15,'Produto 15','QUÍMICOS','PRODUTO','Produto limpeza 15',65,5,'UN','ATIVO',5,15,380),
(16,'Produto 16','GRÃOS','PRODUTO','Produto alimento 16',15,5,'KG','ATIVO',1,16,400),
(17,'Produto 17','GRÃOS','PRODUTO','Produto alimento 17',25,5,'KG','ATIVO',2,17,420),
(18,'Produto 18','GRÃOS','PRODUTO','Produto alimento 18',35,5,'KG','ATIVO',3,18,440),
(19,'Produto 19','GRÃOS','PRODUTO','Produto alimento 19',45,5,'KG','ATIVO',4,19,460),
(20,'Produto 20','GRÃOS','PRODUTO','Produto alimento 20',55,5,'KG','ATIVO',5,20,480);

-- ========================
-- ITEMS (20 MATÉRIAS-PRIMAS)
-- ========================
INSERT INTO T_SGI_ITEM
(id_item, nm_item, ds_category, tp_item, ds_item, nr_quantity, nr_minimium_quantity, ds_measure, st_item, id_user, id_local, vl_value)
VALUES
(21,'MP 1','INSUMO','MATERIA_PRIMA','Materia prima 1',100,120,'KG','ATIVO',1,1,10),
(22,'MP 2','INSUMO','MATERIA_PRIMA','Materia prima 2',110,200,'KG','ATIVO',2,2,11),
(23,'MP 3','INSUMO','MATERIA_PRIMA','Materia prima 3',120,20,'KG','ATIVO',3,3,12),
(24,'MP 4','INSUMO','MATERIA_PRIMA','Materia prima 4',130,20,'KG','ATIVO',4,4,13),
(25,'MP 5','INSUMO','MATERIA_PRIMA','Materia prima 5',140,20,'KG','ATIVO',5,5,14),
(26,'MP 6','INSUMO','MATERIA_PRIMA','Materia prima 6',150,20,'KG','ATIVO',1,6,15),
(27,'MP 7','INSUMO','MATERIA_PRIMA','Materia prima 7',160,20,'KG','ATIVO',2,7,16),
(28,'MP 8','INSUMO','MATERIA_PRIMA','Materia prima 8',170,20,'KG','ATIVO',3,8,17),
(29,'MP 9','INSUMO','MATERIA_PRIMA','Materia prima 9',10,20,'KG','ATIVO',4,9,18),
(30,'MP 10','INSUMO','MATERIA_PRIMA','Materia prima 10',190,20,'KG','ATIVO',5,10,19),
(31,'MP 11','INSUMO','MATERIA_PRIMA','Materia prima 11',200,20,'KG','ATIVO',1,11,20),
(32,'MP 12','INSUMO','MATERIA_PRIMA','Materia prima 12',210,20,'KG','ATIVO',2,12,21),
(33,'MP 13','INSUMO','MATERIA_PRIMA','Materia prima 13',220,20,'KG','ATIVO',3,13,22),
(34,'MP 14','INSUMO','MATERIA_PRIMA','Materia prima 14',230,20,'KG','ATIVO',4,14,23),
(35,'MP 15','INSUMO','MATERIA_PRIMA','Materia prima 15',240,20,'KG','ATIVO',5,15,24),
(36,'MP 16','INSUMO','MATERIA_PRIMA','Materia prima 16',250,20,'KG','ATIVO',1,16,25),
(37,'MP 17','INSUMO','MATERIA_PRIMA','Materia prima 17',260,20,'KG','ATIVO',2,17,26),
(38,'MP 18','INSUMO','MATERIA_PRIMA','Materia prima 18',270,20,'KG','ATIVO',3,18,27),
(39,'MP 19','INSUMO','MATERIA_PRIMA','Materia prima 19',280,20,'KG','ATIVO',4,19,28),
(40,'MP 20','INSUMO','MATERIA_PRIMA','Materia prima 20',290,20,'KG','ATIVO',5,20,29);

-- ========================
-- PRODUCT DETAILS (ids 1–20)
-- ========================
INSERT INTO T_SGI_PRODUCT
(id_item, vl_weight, vl_height, vl_length, vl_depth)
VALUES
(1,1.2,10,20,5),(2,1.3,10,20,5),(3,1.4,10,20,5),
(4,1.5,10,20,5),(5,1.6,10,20,5),(6,1.7,10,20,5),
(7,1.8,10,20,5),(8,1.9,10,20,5),(9,2.0,10,20,5),
(10,2.1,10,20,5),(11,2.2,10,20,5),(12,2.3,10,20,5),
(13,2.4,10,20,5),(14,2.5,10,20,5),(15,2.6,10,20,5),
(16,2.7,10,20,5),(17,2.8,10,20,5),(18,2.9,10,20,5),
(19,3.0,10,20,5),(20,3.1,10,20,5);

-- ========================
-- RAW MATERIAL DETAILS (ids 21–40)
-- ========================
INSERT INTO T_SGI_RAW_MATERIAL
(id_item, nr_batch)
VALUES
(21,1),(22,1),(23,1),(24,1),(25,1),
(26,1),(27,1),(28,1),(29,1),(30,1),
(31,1),(32,1),(33,1),(34,1),(35,1),
(36,1),(37,1),(38,1),(39,1),(40,1);

-- ========================
-- SUPPLIER x MATERIAL RELATION
-- ========================
INSERT INTO T_SUPPLIER_MATERIAL (id_supplier, id_raw_material) VALUES
(1,21),(1,22),(2,23),(2,24),(3,25),(3,26),(4,27),(4,28),(5,29),(5,30),
(1,31),(2,32),(3,33),(4,34),(5,35),(1,36),(2,37),(3,38),(4,39),(5,40);
