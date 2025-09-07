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
	CONSTRAINT fk_codigoFuncionario FOREIGN KEY(codigoFuncionario) REFERENCES funcionario(rowid) ON DELETE CASCADE,
	CONSTRAINT fk_codigoAgenda FOREIGN KEY(codigoAgenda) REFERENCES agenda(rowid) ON DELETE RESTRICT
);

INSERT INTO funcionario (nm_funcionario) VALUES ('João Pedro Alves Pereira'), ('Maria'), ('José'), ('Joana'), ('João'), ('Maria'), ('José'), ('Joana'), ('João'), ('Maria'), ('José'), ('Joana');
INSERT INTO agenda(nm_agenda, periodoDisponivel) VALUES ('Agenda Clínica Odontológica', '1'), ('Agenda Clínica Cardiológica', '2'), ('Agenda Clínica Hospitalar', '3');
INSERT INTO compromisso(codigoFuncionario, codigoAgenda, data, horario) VALUES 
(1, 1, '2025-09-12', '09:30'), 
(2, 1, '2025-09-12', '10:00'),
(3, 1, '2025-09-12', '11:00'), 
(4, 2, '2025-09-20', '12:30'),
(1, 2, '2025-09-20', '13:00'),
(3, 2, '2025-09-20', '14:00'),
(2, 2, '2025-09-20', '16:00'),
(6, 2, '2025-09-25', '11:00'),
(9, 2, '2025-09-25', '12:00'),
(7, 2, '2025-09-25', '14:00'),
(8, 2, '2025-09-25', '15:30');