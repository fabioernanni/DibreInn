-- Script Banco de Dados Completo -- 

-- Acessando o usu�rio master --
USE master
GO

-- Criando o DATABASE --
CREATE DATABASE DB_DIBREINN
GO

-- Selecionando o DATABASE para criar as tabelas -- 
USE DB_DIBREINN
GO

-- Criando a TB_UNIDADE --
CREATE TABLE TB_UNIDADE
(
ID_UNIDADE INT IDENTITY(1,1) NOT NULL,
STATUS BIT NOT NULL,
NOME VARCHAR (25) NOT NULL,
CNPJ VARCHAR (20) NOT NUll,
TIPO BIT NOT NULL,
LOGRADOURO VARCHAR (50),
NUM INT,
CEP VARCHAR (20),
COMPLEMENTO VARCHAR(50),
BAIRRO VARCHAR (30),
CIDADE VARCHAR(20),
ESTADO CHAR(2),

-- Definindo a Chave Primaria --
CONSTRAINT PK_TB_UNIDADE_ID_UNIDADE PRIMARY KEY (ID_UNIDADE),

-- Definindo regra de integridade --
CONSTRAINT UQ_TB_UNIDADE_CNPJ UNIQUE (CNPJ)
)
GO



-- Criando a TB_PESSOA -- 
CREATE TABLE TB_PESSOA
(
ID_PESSOA INT IDENTITY(1,1) NOT NULL,
STATUS BIT NOT NULL,
NOME VARCHAR (15),
SOBRENOME VARCHAR (25),
SEXO CHAR (1),
RG VARCHAR (15),
CPF VARCHAR(15),
DATANASC DATE,
TELEFONE VARCHAR(15),
CEL VARCHAR (16),
EMAIL VARCHAR(35),
NEWSLETTER BIT,

-- Definindo a Chave Primaria --
CONSTRAINT PK_TB_PESSOA_ID_PESSOA PRIMARY KEY (ID_PESSOA),

-- Definindo regras de integridade --
CONSTRAINT UQ_TB_PESSOA_CPF UNIQUE (CPF),
CONSTRAINT CK_CADASTROS_SEXO CHECK (SEXO IN ('F','M'))
)
GO

-- Criando a TB_PRIVILEGIO --
CREATE TABLE TB_PRIVILEGIO
(
ID_PRIVILEGIO INT NOT NULL,
STATUS BIT NOT NULL,
PRIVILEGIO VARCHAR (30) NOT NULL,

-- Definindo a Chave Primaria --
CONSTRAINT PK_TB_PRIVILEGIO_ID_PRIVILEGIO PRIMARY KEY (ID_PRIVILEGIO)
)
GO


-- Criando a TB_FUNCIONARIO --
CREATE TABLE TB_FUNCIONARIO
(
ID_PESSOA INT NOT NULL,
ID_UNIDADE INT,
ID_PRIVILEGIO INT,
SENHA VARCHAR(250),

-- Definindo a Chave Primaria --
CONSTRAINT PK_TB_FUCIONARIO_ID_PESSOA PRIMARY KEY (ID_PESSOA),

-- Definindo as Chaves Estrangeiras --
CONSTRAINT FK_TB_FUNCIONARIO_ID_PESSOA FOREIGN KEY (ID_PESSOA) REFERENCES TB_PESSOA (ID_PESSOA),
CONSTRAINT FK_TB_FUNCIONARIO_ID_UNIDADE FOREIGN KEY (ID_UNIDADE) REFERENCES TB_UNIDADE (ID_UNIDADE),
CONSTRAINT FK_TB_FUNCIONARIO_ID_PRIVILEGIO FOREIGN KEY (ID_PRIVILEGIO) REFERENCES TB_PRIVILEGIO (ID_PRIVILEGIO)
)
GO

-- Criando a TB_HOSPEDE --
CREATE TABLE TB_HOSPEDE
(
ID_PESSOA INT NOT NULL,
N_CARTAO VARCHAR (20)

-- Definindo a Chave Primaria --
CONSTRAINT PK_TB_HOSPEDE_ID_PESSOA PRIMARY KEY (ID_PESSOA)
-- Definindo a Chave Estrangeira --
CONSTRAINT FK_TB_HOSPEDE_ID_PESSOA FOREIGN KEY (ID_PESSOA) REFERENCES TB_PESSOA(ID_PESSOA)
)
GO


-- Criando a TB_QUARTO --
CREATE TABLE TB_QUARTO
(
ID_QUARTO INT IDENTITY(1,1)NOT NULL,
ID_UNIDADE INT NOT NULL,
STATUS BIT NOT NULL,
VALOR_DIARIA DECIMAL(10,2),
NUMERO INT,
ANDAR VARCHAR(10),
RAMAL VARCHAR (10)

-- Definindo a Chave Primaria --
CONSTRAINT PK_TB_QUARTO_ID_QUARTO PRIMARY KEY (ID_QUARTO)
-- Definindo a Chave Estrangeira --
CONSTRAINT FK_TB_QUARTO_ID_UNIDADE FOREIGN KEY (ID_UNIDADE) REFERENCES TB_UNIDADE(ID_UNIDADE)
)
GO

-- Criando a TB_RESERVA --
CREATE TABLE TB_RESERVA
(
ID_RESERVA INT IDENTITY(1,1) NOT NULL,
ID_FUNCIONARIO INT NOT NULL,
ID_HOSPEDE INT NOT NULL,
ID_QUARTO INT NOT NULL,
DT_INICIO DATE,
DT_FIM DATE,
ACOMPANHANTES DECIMAL,
CRIANCAS DECIMAL,
VALOR DECIMAL,

-- Definindo a Chave Primaria --
CONSTRAINT PK_TB_RESERVA_ID_RESERVA PRIMARY KEY (ID_RESERVA),
-- Definindo a Chave Estrangeira --
CONSTRAINT FK_TB_RESERVA_ID_FUNCIONARIO FOREIGN KEY (ID_FUNCIONARIO) REFERENCES TB_PESSOA(ID_PESSOA),
CONSTRAINT FK_TB_RESERVA_ID_HOSPEDE FOREIGN KEY (ID_HOSPEDE) REFERENCES TB_PESSOA(ID_PESSOA),
CONSTRAINT FK_TB_RESERVA_ID_QUARTO FOREIGN KEY (ID_QUARTO) REFERENCES TB_QUARTO(ID_QUARTO),
)
GO


INSERT INTO TB_PRIVILEGIO (ID_PRIVILEGIO, STATUS, PRIVILEGIO) VALUES (1,1,'Vendas')
INSERT INTO TB_PRIVILEGIO (ID_PRIVILEGIO, STATUS, PRIVILEGIO) VALUES (2,1,'Gerência')
INSERT INTO TB_PRIVILEGIO (ID_PRIVILEGIO, STATUS, PRIVILEGIO) VALUES (3,1,'Diretoria')
GO

SELECT * FROM TB_PRIVILEGIO
GO

INSERT INTO TB_UNIDADE (STATUS,NOME,CNPJ,TIPO,LOGRADOURO,NUM,CEP,COMPLEMENTO,BAIRRO,CIDADE,ESTADO) VALUES  (1,'SÃO PAULO I','24655141000/01',1,'Francisco Alvares',37,'04403060','Bloco III','Jardim Luso','São Paulo','SP')
INSERT INTO TB_UNIDADE (STATUS,NOME,CNPJ,TIPO,LOGRADOURO,NUM,CEP,COMPLEMENTO,BAIRRO,CIDADE,ESTADO) VALUES  (0,'SÃO PAULO II','54879651000/45',0,'Kansas',1025,'04253090','N/A','Cupecê','São Paulo','SP')
INSERT INTO TB_UNIDADE (STATUS,NOME,CNPJ,TIPO,LOGRADOURO,NUM,CEP,COMPLEMENTO,BAIRRO,CIDADE,ESTADO) VALUES  (1,'RIO DE JANEIRO I','24875141000/01',0,'Av. Beira Mar',2455,'02203078','Proximo a praia','Resende','Rio de Janeiro','RJ')
GO

SELECT * FROM TB_UNIDADE
GO

BEGIN TRANSACTION 

INSERT INTO TB_PESSOA 
(STATUS, NOME, SOBRENOME, SEXO, RG, CPF, DATANASC, TELEFONE,CEL, EMAIL, NEWSLETTER) 
VALUES
(1, 'Luciano','Lourenço','M','44.552.231-0','401.072.588-53','1988-11-28','(11)6226-9655','(11)96226-9566','lucianolourecoti@gmail.com', 1)

DECLARE @idCliente AS INT = @@IDENTITY

INSERT INTO TB_FUNCIONARIO 
(ID_PESSOA,ID_UNIDADE,ID_PRIVILEGIO,SENHA) 
VALUES 
(@idCliente, 1,1,'quiksilver')

	IF @@ERROR <> 0
		BEGIN
		ROLLBACK 
	END
	ELSE
		COMMIT
GO

BEGIN TRANSACTION 

INSERT INTO TB_PESSOA 
(STATUS, NOME, SOBRENOME, SEXO, RG, CPF, DATANASC, TELEFONE,CEL, EMAIL, NEWSLETTER) 
VALUES
(1, 'Thiago','Melo','M','44.755.854-0','451.365.588-53','1988-11-28','(11)2658-2658','(11)95866-9566','thiagomelo@gmail.com', 1)

DECLARE @idCliente AS INT = @@IDENTITY

INSERT INTO TB_FUNCIONARIO 
(ID_PESSOA,ID_UNIDADE,ID_PRIVILEGIO,SENHA) 
VALUES 
(@idCliente, 2,2,'123456')

	IF @@ERROR <> 0
		BEGIN
		ROLLBACK 
	END
	ELSE
		COMMIT
GO

BEGIN TRANSACTION 

INSERT INTO TB_PESSOA 
(STATUS, NOME, SOBRENOME, SEXO, RG, CPF, DATANASC, TELEFONE,CEL, EMAIL, NEWSLETTER) 
VALUES
(1, 'Udimberto','Jr','M','33.265.887-0','451.385.512-53','1988-11-28','(11)2878-2658','(11)98866-9116','jr@gmail.com', 1)

DECLARE @idCliente AS INT = @@IDENTITY

INSERT INTO TB_FUNCIONARIO 
(ID_PESSOA,ID_UNIDADE,ID_PRIVILEGIO,SENHA) 
VALUES 
(@idCliente, 3,3,'654321')

	IF @@ERROR <> 0
		BEGIN
		ROLLBACK 
	END
	ELSE
		COMMIT
GO

SELECT * FROM TB_PESSOA
SELECT * FROM TB_FUNCIONARIO