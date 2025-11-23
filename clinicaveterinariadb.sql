DROP TABLE IF EXISTS Consultas;
DROP TABLE IF EXISTS Animais;
DROP TABLE IF EXISTS Veterinarios;
DROP TABLE IF EXISTS Proprietario;

CREATE TABLE Proprietario (
    id_proprietario SERIAL PRIMARY KEY,
    nome_proprietarios VARCHAR(50) NOT NULL,
    cpf CHAR(11) UNIQUE NOT NULL,
    telefone VARCHAR(15),
    endereco VARCHAR(100),
    email VARCHAR(50)
);

CREATE TABLE Animais (
    id_animal SERIAL PRIMARY KEY,
    nome_animal VARCHAR(50) NOT NULL,
    especie VARCHAR(30),
    raca VARCHAR(30),
    data_nascimento DATE,
    peso NUMERIC(6,2),
    id_proprietario INT NOT NULL,
    FOREIGN KEY (id_proprietario) REFERENCES Proprietario(id_proprietario)
        ON DELETE CASCADE   
);

CREATE TABLE Veterinarios (
    id_veterinario SERIAL PRIMARY KEY,
    nome_veterinario VARCHAR(50) NOT NULL,
    crmv VARCHAR(10) UNIQUE NOT NULL,
    especialidade VARCHAR(30),
    telefone VARCHAR(15)
);

CREATE TABLE Consultas (
    id_consulta SERIAL PRIMARY KEY,
    data_hora TIMESTAMP NOT NULL,
    diagnostico VARCHAR(200),
    valor NUMERIC(10,2),
    id_animal INT,
    id_veterinario INT,
    FOREIGN KEY (id_animal) REFERENCES Animais(id_animal)
        ON DELETE SET NULL,        
    FOREIGN KEY (id_veterinario) REFERENCES Veterinarios(id_veterinario)
        ON DELETE SET NULL         
);


/*SELECT * FROM Proprietario;
SELECT * FROM Veterinarios;
SELECT * FROM Animais;
SELECT * FROM Consultas;*/



