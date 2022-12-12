create table products
(	
	codproducto bigint not null auto_increment,
    cod_bar varchar(20) not null,
    articulo varchar(400) not null,
    presentacion varchar(30)null,
    cant_producto  varchar(30)null,
    empaque varchar(30)null,
    marca varchar(30)null,
    vr_base decimal(20) not null,
    vr_unidad varchar(20) null,
    vr_venta decimal(20) null,
    primary key(codproducto)
);