DROP TABLE Proprietario;
CREATE TABLE Proprietario (
    id_proprietario SERIAL PRIMARY KEY,
    nome_proprietarios VARCHAR(50) NOT NULL,
    cpf CHAR(11) UNIQUE NOT NULL,
    telefone VARCHAR(15),
    endereco VARCHAR(100),
    email VARCHAR(50)
);
DROP TABLE Animais
CREATE TABLE Animais (
    id_animal SERIAL PRIMARY KEY,
    nome_animal VARCHAR(50) NOT NULL,
    especie VARCHAR(30),
    raca VARCHAR(30),
    data_nascimento DATE,
    peso NUMERIC(6,2),
    id_proprietario INT NOT NULL,
    FOREIGN KEY (id_proprietario) REFERENCES Proprietario(id_proprietario)
);
DROP TABLE Veterinarios
CREATE TABLE Veterinarios (
    id_veterinario SERIAL PRIMARY KEY,
    nome_veterinario VARCHAR(50) NOT NULL,
    crmv VARCHAR(10) UNIQUE NOT NULL,
    especialidade VARCHAR(30),
    telefone VARCHAR(15)
);
DROP TABLE Consultas
CREATE TABLE Consultas (
    id_consulta SERIAL PRIMARY KEY,
    data_hora TIMESTAMP NOT NULL,
    diagnostico VARCHAR(200),
    valor NUMERIC(10,2),
    id_animal INT NOT NULL,
    id_veterinario INT NOT NULL,
    FOREIGN KEY (id_animal) REFERENCES Animais(id_animal),
    FOREIGN KEY (id_veterinario) REFERENCES Veterinarios(id_veterinario)
);

ALTER TABLE Animais
DROP CONSTRAINT animais_id_proprietario_fkey;

ALTER TABLE Animais
ADD CONSTRAINT animais_id_proprietario_fkey
FOREIGN KEY (id_proprietario)
REFERENCES Proprietario(id_proprietario)
ON DELETE CASCADE;

SELECT * FROM Proprietario;
SELECT * FROM Animais;

/*
INSERT INTO Proprietario (nome_proprietarios, cpf, telefone, endereco, email)
VALUES
('Guilherme Dias', '04715436590', '71997300040', 'Rua Edgar B. Franco', 'guidias1905@gmail.com'),
('Mariana Silva', '12345678901', '71999990001', 'Av. Oceânica, 120', 'mariana.silva@email.com'),
('Matheus Extase', '23456789012', '71988880002', 'Rua das Flores, 45', 'Matheus.extase@email.com'),
('Carla Souza', '34567890123', '71977770003', 'Travessa Central, 300', 'carla.souza@email.com'),
('Felipe geihirsch', '45678901234', '71966660004', 'Rua Bahia, 88', 'felipe.gei@email.com'),
('joao cunha', '56789012345', '71955550005', 'Rua Santo Antônio, 200', 'joao.cunha@email.com'),
('Juan', '67890123456', '71944440006', 'Av. Sete de Setembro, 901', 'so.juan@email.com');

INSERT INTO Veterinarios (nome_veterinario, crmv, especialidade, telefone)
VALUES
('Dra. Ana Souza', 'CRMV1234', 'Clínica Geral', '71999990001'),
('Dr. Carlos Lima', 'CRMV5678', 'Dermatologia', '71999990002'),
('Dr. Enzo Badogh', 'CRMV9876', 'Cardiologista', '71999990003');


INSERT INTO Animais (nome_animal, especie, raca, data_nascimento, peso, id_proprietario)
VALUES
('Rex', 'Cachorro', 'Labrador', '2020-05-10', 25.5, 1),
('Mel', 'Gato', 'Siamês', '2021-02-15', 4.2, 1),
('Thor', 'Cachorro', 'Husky', '2019-09-01', 30.0, 1);

INSERT INTO Consultas (data_hora, diagnostico, valor, id_animal, id_veterinario)
VALUES
('2025-11-10 10:30:00', 'Consulta Vacinação', 120.00, 1, 1),
('2025-11-11 14:00:00', 'Verme', 90.00, 2, 1),
('2025-11-12 09:00:00', 'Hemorragia', 150.00, 3, 2),
('2025-11-12 11:30:00', 'Otite', 180.00, 1, 2),
('2025-11-13 15:45:00', 'Consulta dermatológica', 200.00, 2, 1);
*/
SELECT
    c.id_consulta,
    v.nome_veterinario,
    a.nome_animal,
    a.especie,
    a.raca,
    c.diagnostico,
    c.valor
FROM Consultas c
JOIN Animais a ON c.id_animal = a.id_animal
JOIN Veterinarios v ON c.id_veterinario = v.id_veterinario;
