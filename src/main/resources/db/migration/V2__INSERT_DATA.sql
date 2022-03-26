insert into public.gym (direccion, codigo_postal, pais, ciudad, nombre,provincia) values ( 'Passeig de pere III 92', 08241, 'España', 'Manresa','Manresa1','Barcelona');
insert into public.cliente ( id_gimnasio, dni, nombre, apellidos, fecha_nacimiento, fecha_inscripcion, email, calle, codigo_postal, ciudad, provincia ) values (1,'39123852Y','admin','admin','27/11/1996','02/05/2020','neifi@gmail.es','Calle cos 11 1-1',08241,'Manresa','Barcelona');
insert into public.cliente ( id_gimnasio, dni, nombre, apellidos, fecha_nacimiento, fecha_inscripcion, email, calle, codigo_postal, ciudad, provincia) values (1,'39783162V','user','user','01/02/1996','02/05/2020','juan@gmail.es','Calle mallorca 36 4-1',08241,'Manresa','Barcelona');
insert into public.cliente ( id_gimnasio, dni, nombre, apellidos, fecha_nacimiento, fecha_inscripcion, email, calle, codigo_postal, ciudad, provincia) values (1,'Y3051657P','unverified','unverified','11/04/1992','02/05/2020','maria@gmail.es','Carretera de vic 134 2-2 ',08241,'Manresa','Barcelona');

insert into public.usuario (username,password,fecha_creacion,ultima_mod_password,verificado) values ('admin','$2y$12$tBb8IOZhtQkleN0MNTE1y.pqoCl8EVkTtPEGuG/ZPNFFBYe3YsyoC','2020-05-02 15:44:20.852+02','2020-05-02 15:44:20.774+02',true);
insert into public.usuario (username,password,fecha_creacion,ultima_mod_password,verificado) values ('user','$2y$12$7V1Mo0FujQvoIPS28r7P1e9yTSghzqKR7ej0iL0R0P5ll/xODVW0W','2020-05-02 15:44:20.852+02','2020-05-02 15:44:20.774+02',true);
insert into public.usuario (username,password,fecha_creacion,ultima_mod_password,verificado) values ('unverified','$2y$12$6WFMUF0jGW/9aTGcyjxn2eCrC51p/nj2UZ6m1vhBSPXVPFJCI1a/a','2020-05-02 15:44:20.852+02','2020-05-02 15:44:20.774+02',false);

insert into public.registrohorario(id_usuario,horaentrada,horasalida,fecha) values (1,'11:00:00','14:00:00','04-05-2020');
insert into public.registrohorario(id_usuario,horaentrada,horasalida,fecha) values (1,'11:00:00','14:00:00','05-05-2020');
insert into public.registrohorario(id_usuario,horaentrada,horasalida,fecha) values (1,'11:00:00','14:00:00','06-05-2020');
insert into public.registrohorario(id_usuario,horaentrada,horasalida,fecha) values (1,'11:00:00','14:00:00','07-05-2020');
insert into public.registrohorario(id_usuario,horaentrada,horasalida,fecha) values (1,'11:00:00','14:00:00','08-05-2020');
insert into public.registrohorario(id_usuario,horaentrada,horasalida,fecha) values (2,'11:00:00','14:00:00','04-05-2020');
insert into public.registrohorario(id_usuario,horaentrada,horasalida,fecha) values (2,'11:00:00','14:00:00','05-05-2020');
insert into public.registrohorario(id_usuario,horaentrada,horasalida,fecha) values (2,'11:00:00','14:00:00','06-05-2020');
insert into public.registrohorario(id_usuario,horaentrada,horasalida,fecha) values (2,'11:00:00','14:00:00','07-05-2020');
insert into public.registrohorario(id_usuario,horaentrada,horasalida,fecha) values (2,'11:00:00','14:00:00','08-05-2020');
insert into public.registrohorario(id_usuario,horaentrada,horasalida,fecha) values (3,'8:00:00','14:00:00','08-05-2020');

insert into public.usuario_rol(usuario_id_usuario,rol) values (1,'ADMIN');
insert into public.usuario_rol(usuario_id_usuario,rol) values (2,'USER');
insert into public.usuario_rol(usuario_id_usuario,rol) values (2,'UNVERIFIED');

ALTER TABLE cliente
    ADD FOREIGN KEY (id) REFERENCES usuario (id_usuario);