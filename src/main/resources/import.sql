-- Dados iniciais para popular o banco H2 ao iniciar a aplicação
-- (o arquivo import.sql é executado automaticamente pelo Spring Boot
--  quando o ddl-auto gera a estrutura das tabelas)

-- Pacientes (o id é gerado pelo autoincremento do H2)
--insert into Paciente (nome, telefone) values ('Maria Silva', '(63) 99911-2233'); -- id 1
--insert into Paciente (nome, telefone) values ('João Souza', '(63) 99822-3344');  -- id 2

INSERT INTO pessoa (dtype, nome, crm) VALUES ('Medico', 'Dr. Carlos Lima', 'CRM-12345'); -- id 3
INSERT INTO pessoaFisica (dtype, nome, crm) VALUES ('Medico','Dra. Ana Costa', 'CRM-54321');

-- Consultas (id auto; FKs paciente_id/medico_id apontam para os ids acima)
-- OBS: cada comando deve ficar em uma ÚNICA linha, pois o Hibernate lê o
-- import.sql linha por linha.
insert into Consulta (data, valor, observacao, paciente_id, medico_id) values (TIMESTAMP '2026-09-01 09:00:00', 250.00, 'Retorno de exame', 1, 1);
insert into Consulta (data, valor, observacao, paciente_id, medico_id) values (TIMESTAMP '2026-09-02 14:30:00', 350.00, 'Primeira consulta', 1, 2);
insert into Consulta (data, valor, observacao, paciente_id, medico_id) values (TIMESTAMP '2026-09-03 10:15:00', 150.00, 'Consulta de rotina', 2, 2);
