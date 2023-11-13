CREATE DATABASE IF NOT EXISTS infnet_project;
USE infnet_project;
-- PROFESSOR --
DROP TABLE IF EXISTS infnet_project.`Professor`;
CREATE TABLE IF NOT EXISTS infnet_project.`Professor` (
  `IdProfessor` INT AUTO_INCREMENT NOT NULL,
  `NomeProfessor` VARCHAR(255) NOT NULL,
  `NumSalaAula` INT NULL,
  `Materia` VARCHAR(255) NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`IdProfessor`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
-- RESPONSAVEL --
DROP TABLE IF EXISTS infnet_project.`Responsavel`;
CREATE TABLE IF NOT EXISTS infnet_project.`Responsavel` (
  `IdResponsavel` INT AUTO_INCREMENT NOT NULL,
  `IdAluno` INT NOT NULL,
  `NomeResponsavel` VARCHAR(255) NOT NULL,
  `Parentesco` VARCHAR(255) NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`IdResponsavel`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
-- ALUNO --
DROP TABLE IF EXISTS infnet_project.`Aluno`;
CREATE TABLE IF NOT EXISTS infnet_project.`Aluno` (
  `IdAluno` INT AUTO_INCREMENT NOT NULL,
  `NomeAluno` VARCHAR(255) NOT NULL,
  `NumMatricula` INT NULL,
  `NumSalaAula` INT NULL,
  `Ativo` BOOLEAN NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`IdAluno`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
-- SALA DE AULA --
DROP TABLE IF EXISTS infnet_project.`SalaAula`;
CREATE TABLE IF NOT EXISTS infnet_project.`SalaAula` (
  `IdSalaAula` INT AUTO_INCREMENT NOT NULL,
  `IdAluno` INT NOT NULL,
  `IdProfessor` INT NOT NULL,
  CONSTRAINT `PRIMARY` PRIMARY KEY (`IdSalaAula`)
) ENGINE = InnoDB DEFAULT CHARSET = utf8;
-- ALTER TABLES - ALUNO --
ALTER TABLE
  infnet_project.`Aluno`
ADD
  FOREIGN KEY (`NumSalaAula`) REFERENCES infnet_project.`SalaAula`(`IdSalaAula`);
-- ALTER TABLES - PROFESSOR --
ALTER TABLE
  infnet_project.`Professor`
ADD
  FOREIGN KEY (`NumSalaAula`) REFERENCES infnet_project.`SalaAula`(`IdSalaAula`);
-- ALTER TABLES - RESPONSAVEL --
ALTER TABLE
  infnet_project.`Responsavel`
ADD
  FOREIGN KEY (`IdAluno`) REFERENCES infnet_project.`Aluno`(`IdAluno`);
-- INSERT INTO SALA DE AULA --
INSERT INTO
  infnet_project.`SalaAula` (`IdAluno`, `IdProfessor`)
VALUES
  (1, 1);
-- INSERT INTO ALUNO --
INSERT INTO
  infnet_project.`Aluno` (
    `NomeAluno`,
    `NumMatricula`,
    `NumSalaAula`,
    `Ativo`
  )
VALUES
  ('Thiago Nunes', 100, 1, 0);
-- INSERT INTO PROFESSOR --
INSERT INTO
  infnet_project.`Professor` (
    `NomeProfessor`,
    `NumSalaAula`,
    `Materia`
  )
VALUES
  ('Professor Teste', 1, 'Português');
-- INSERT INTO RESPONSAVEL --
INSERT INTO
  infnet_project.`Responsavel` (
    `IdAluno`,
    `NomeResponsavel`,
    `Parentesco`
  )
VALUES
  (1, 'Responsável Teste', 'Mãe');