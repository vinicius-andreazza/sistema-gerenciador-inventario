

drop table if exists T_SGI_USERS;
drop table if exists T_SGI_LOCAL;
drop table if exists T_SGI_SUPPLIER;
drop table if exists T_SGI_ITEM;
drop table if exists T_SGI_PRODUCT;
drop table if exists T_SGI_RAW_MATERIAL;
drop table if exists T_SUPPLIER_MATERIAL;

create table T_SGI_USERS(
	id_user bigint not null,
    nm_user varchar(45) not null,
    ds_password varchar(255) not null,
    ds_role varchar(20) not null
);

create table T_SGI_LOCAL(
	id_local bigint not null,
    nm_sector varchar(45) not null,
    nr_position int not null,
    ds_shelf varchar(2) not null
);

create table T_SGI_SUPPLIER(
	id_supplier bigint not null,
    nm_supplier varchar(60) not null,
    ds_phone varchar(14) null,
    ds_email varchar(100) not null
);

create table T_SGI_ITEM(
	id_item bigint not null,
    nm_item varchar(60) not null,
    ds_category varchar(20) not null,
    tp_item varchar(17) not null,
	ds_item text not null,
    nr_quantity int not null,
    nr_minimium_quantity int not null,
    ds_measure char(2) not null,
    st_item varchar(15) not null,
    id_user bigint not null,
    id_local bigint not null
);

create table T_SGI_PRODUCT(
	id_item bigint not null,
	vl_value decimal(8,2) not null,
	vl_weight decimal(7,3) not null,
    vl_height decimal(5,2) not null,
    vl_length decimal(5,2) not null,
    vl_depth decimal(5,2) not null
);

create table T_SGI_RAW_MATERIAL(
	id_item bigint not null,
	nr_batch int not null,
	vl_unit decimal(7,3) not null
);

create table T_SUPPLIER_MATERIAL(
	id_supplier bigint not null,
    id_raw_material bigint not null
);

alter table T_SGI_USERS
modify id_user bigint not null auto_increment,
add constraint PK_USERS primary key (id_user),
add constraint UN_USERNAME unique (nm_user);

alter table T_SGI_LOCAL
modify id_local bigint not null auto_increment,
add constraint PK_LOCAL primary key (id_local);

alter table T_SGI_SUPPLIER
modify id_supplier bigint not null auto_increment,
add constraint PK_SUPPLIER primary key (id_supplier);

alter table T_SGI_ITEM
modify id_item bigint not null auto_increment,
add constraint PK_ITEM primary key (id_item),
add constraint FK_ITEM_USER foreign key (id_user) references T_SGI_USERS(id_user),
add constraint FK_ITEM_LOCAL foreign key (id_local) references T_SGI_LOCAL(id_local);

alter table T_SGI_PRODUCT
add constraint PK_PRODUCT primary key (id_item),
add constraint FK_PRODUCT_ITEM foreign key (id_item) references T_SGI_ITEM(id_item);

alter table T_SGI_RAW_MATERIAL
add constraint PK_RAW_MATERIAL primary key (id_item),
add constraint FK_RAW_MATERIAL_ITEM foreign key (id_item) references T_SGI_ITEM(id_item);

alter table T_SUPPLIER_MATERIAL
add constraint PK_SUPPLIER_MATERIAL primary key (id_supplier, id_raw_material),
add constraint FK_SUPPLIER_MATERIAL foreign key (id_raw_material) references T_SGI_RAW_MATERIAL(id_item),
add constraint FK_MATERIAL_SUPPLIER foreign key (id_supplier) references T_SGI_SUPPLIER(id_supplier);

DELIMITER $$

CREATE TRIGGER ARC_ITEM_T_SGI_PRODUCT
BEFORE INSERT ON T_SGI_PRODUCT
FOR EACH ROW
BEGIN
    DECLARE d VARCHAR(17);
    SELECT tp_item INTO d FROM T_SGI_ITEM WHERE id_item = NEW.id_item LIMIT 1;
    IF d IS NULL OR d <> 'PRODUTO' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'FK_PRODUCT_ITEM violated restriction: tp_item need to be ''PRODUTO''';
    END IF;
END$$

CREATE TRIGGER ARC_ITEM_T_SGI_RAW_MATERIAL
BEFORE INSERT ON T_SGI_RAW_MATERIAL
FOR EACH ROW
BEGIN
    DECLARE d VARCHAR(17);
    SELECT tp_item INTO d FROM T_SGI_ITEM WHERE id_item = NEW.id_item LIMIT 1;
    IF d IS NULL OR d <> 'MATERIA_PRIMA' THEN
        SIGNAL SQLSTATE '45000'
        SET MESSAGE_TEXT = 'FK_RAW_MATERIAL_ITEM violated restriction: tp_item need to be ''RAW_MATERIAL''';
    END IF;
END$$

DELIMITER ;