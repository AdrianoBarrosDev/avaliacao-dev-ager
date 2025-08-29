CREATE TABLE funcionario (rowid bigint auto_increment, nm_funcionario VARCHAR(255));

CREATE TABLE agenda (rowid bigint auto_increment, nm_agenda VARCHAR(255), periodoDisponivel CHAR(1));

INSERT INTO funcionario (nm_funcionario) VALUES ('João'), ('Maria'), ('José'), ('Joana');
INSERT INTO agenda(nm_agenda, periodoDisponivel) VALUES ('Agenda Clínica Odontológica', '1'), ('Agenda Clínica Cardiológica', '2');