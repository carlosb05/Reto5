create schema restaurante;
use restaurante;

create table proteina(
prot_id integer not null primary key,
prot_nombre char(20),
prot_porcentaje_proteina integer,
prot_porcentaje_grasa integer
);
insert into proteina values (1, 'Res', 19, 10);
insert into proteina values (2, 'Pollo', 35, 12);
insert into proteina values (3, 'Pescado', 20, 12);
insert into proteina values (4, 'Soya texturizada', 52, 1);
select * from proteina;

