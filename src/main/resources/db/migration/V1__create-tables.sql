create table usuarios(

                         idus serial not null primary key ,
                         nome_completo varchar(80) not null ,
                         email varchar(100) not null unique ,
                         cpf varchar(14) not null unique ,
                         telefone varchar(14) not null unique ,
                         senha varchar(50) not null ,
                         permissao varchar(11) not null ,
                         status varchar(9) not null
);

create table pacientes(

                          idpac serial not null primary key ,
                          iduser int not null,
                          funcao varchar(40)  ,
                          sangue varchar(3)  ,
                          plano_de_saude varchar(30) ,
                          med_uso_cont varchar(200)  ,
                          condicao_cronica varchar(150)  ,
                          doenca_anterior varchar(200)  ,
                          doenca_infec varchar(100)  ,
                          cirurgia varchar(200)  ,
                          data_de_nasc varchar(10) not null ,
                          alergia varchar(150)  ,
                          historico_familiar varchar(200)  ,
                          sexo varchar(20) not null ,
                          imunizacao varchar(150) ,
                          foreign key (iduser) references usuarios(idus)
);

create table medicos(

                        idmed serial not null primary key ,
                        iduser int not null ,
                        registro varchar(20) not null ,
                        foreign key (iduser) references usuarios(idus)

);

create table consultas(

                          idcon serial not null primary key ,
                          observacao varchar(300)  ,
                          prescricao varchar(200) not null  ,
                          dataa varchar(14) not null  ,
                          sintoma varchar(200) not null  ,
                          idpaciente int not null,
                          idmedico int not null,
                          foreign key (idpaciente) references pacientes(idpac),
                          foreign key (idmedico) references medicos(idmed)
);

create table exames(

                       idex serial not null primary key ,
                       dataa varchar(14) not null ,
                       idpaciente int not null,
                       idmedico int not null,
                       foreign key (idpaciente) references pacientes(idpac),
                       foreign key (idmedico) references medicos(idmed)
);

create table endoscopia(

                           idend serial not null primary key ,
                           idexame int not null ,
                           duodeno varchar(300)  ,
                           esofago varchar(300)   ,
                           conclusao varchar(300) not null  ,
                           descricao varchar(300) not null  ,
                           foreign key (idexame) references exames(idex)
);

create table manometria(

                           idman serial not null primary key ,
                           idexame int not null ,
                           sumario varchar(300) ,
                           conclusao varchar(300) not null  ,
                           resultados varchar(300) not null  ,
                           foreign key (idexame) references exames(idex)
);

create table colangioressonancia(

                                    idcol serial not null primary key ,
                                    idexame int not null ,
                                    diagnostico varchar(300) not null ,
                                    tecnica varchar(399) not null  ,
                                    observacao varchar(300) ,
                                    foreign key (idexame) references exames(idex)
);
