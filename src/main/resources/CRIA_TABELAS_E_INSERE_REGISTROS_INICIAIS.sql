CREATE TABLE funcionario (
	rowid bigint auto_increment PRIMARY KEY, 
	nm_funcionario VARCHAR(255)
);

CREATE TABLE agenda (
	rowid bigint auto_increment PRIMARY KEY, 
	nm_agenda VARCHAR(255), 
	periodoDisponivel CHAR(1)
);

CREATE TABLE compromisso (
	rowid bigint auto_increment,
	codigoFuncionario bigint,
	codigoAgenda bigint,
	data DATE,
	horario TIME,
	CONSTRAINT fk_codigoFuncionario FOREIGN KEY(codigoFuncionario) REFERENCES funcionario(rowid),
	CONSTRAINT fk_codigoAgenda FOREIGN KEY(codigoAgenda) REFERENCES agenda(rowid)
);

INSERT INTO funcionario (nm_funcionario) VALUES ('João'), ('Maria'), ('José'), ('Joana');
INSERT INTO agenda(nm_agenda, periodoDisponivel) VALUES ('Agenda Clínica Odontológica', '1'), ('Agenda Clínica Cardiológica', '2');
INSERT INTO compromisso(codigoFuncionario, codigoAgenda, data, horario) VALUES (1, 1, '2025-05-03', '13:42');