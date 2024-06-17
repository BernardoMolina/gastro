INSERT INTO usuarios (nome_completo, email, cpf, telefone, senha, permissao, status) VALUES
                                                                                         ('Carlos Alberto', 'usuario1@example.com', '111.111.111-11', '(11) 1111-1111', 'senha1', 'admin', 'ativo'),
                                                                                         ('Bernardo Pires', 'usuario2@example.com', '222.222.222-22', '(22) 2222-2222', 'senha2', 'user', 'ativo'),
                                                                                         ('Anna Clara', 'usuario3@example.com', '333.333.333-33', '(33) 3333-3333', 'senha3', 'admin', 'ativo'),
                                                                                         ('Romario Alberto', 'usuario4@example.com', '444.444.444-44', '(44) 4444-4444', 'senha4', 'user', 'ativo');


INSERT INTO medicos (iduser, registro) VALUES
                                           (1, 'CRM123456'),
                                           (2, 'CRM654321');

INSERT INTO pacientes (iduser, funcao, sangue, plano_de_saude, med_uso_cont, condicao_cronica, doenca_anterior, doenca_infec, cirurgia, data_de_nasc, alergia, historico_familiar, sexo, imunizacao) VALUES
                                                                                                                                                                                                         (3,  'Funcao', 'O+', 'Plano Saude Um', 'Medicamento Contínuo Um', 'Condicao Cronica Um', 'Doenca Anterior Um', 'Doenca Infecciosa Um', 'Cirurgia Um', '01/01/1980', 'Alergia Um', 'Historico Familiar Um', 'Masculino', 'Imunizacao Um'),
                                                                                                                                                                                                         (4,  'Funcao', 'A-', 'Plano Saude Dois', 'Medicamento Contínuo Dois','Condicao Cronica Dois', 'Doenca Anterior Dois', 'Doenca Infecciosa Dois', 'Cirurgia Dois', '02/02/1985', 'Alergia Dois', 'Historico Familiar Dois', 'Feminino', 'Imunizacao Dois');


INSERT INTO consultas (observacao, prescricao, dataa, sintoma, idpaciente, idmedico) VALUES
                                                                                         ('Consulta para paciente 1', 'Prescricao para paciente 1', '02/02/1985', 'Sintoma do paciente 1', 1, 1),
                                                                                         ('Consulta para paciente 2', 'Prescricao para paciente 2', '02/02/1985', 'Sintoma do paciente 2', 2, 2);


INSERT INTO exames (dataa, idpaciente, idmedico) VALUES
                                                     ('02/02/1985', 1, 1),
                                                     ('02/02/1985', 1, 2),
                                                     ('02/02/1985', 2, 1),
                                                     ('02/02/1985', 2, 1),
                                                     ('02/02/1985', 1, 2),
                                                     ('02/02/1985', 2, 2);


INSERT INTO manometria (idexame, sumario, conclusao, resultados) VALUES
                                                                     (1, 'Sumario manometria exame 1', 'Conclusao manometria exame 1', 'Resultados manometria exame 1'),
                                                                     (2, 'Sumario manometria exame 2', 'Conclusao manometria exame 2', 'Resultados manometria exame 2');


INSERT INTO colangioressonancia (idexame, diagnostico, tecnica, observacao) VALUES
                                                                                (3, 'Diagnostico colangioressonancia exame 3', 'Tecnica colangioressonancia exame 3', 'Observacao colangioressonancia exame 3'),
                                                                                (4, 'Diagnostico colangioressonancia exame 4', 'Tecnica colangioressonancia exame 4', 'Observacao colangioressonancia exame 4');


INSERT INTO endoscopia (idexame, duodeno, esofago, conclusao, descricao) VALUES
                                                                             (5, 'Duodeno normal', 'Esofago normal', 'Conclusao endoscopia exame 5', 'Descricao endoscopia exame 5'),
                                                                             (6, 'Duodeno com alteracoes', 'Esofago com alteracoes', 'Conclusao endoscopia exame 6', 'Descricao endoscopia exame 6');