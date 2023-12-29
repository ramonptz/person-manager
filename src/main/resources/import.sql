/* UTF-8 */
INSERT INTO pessoa (DATA_DE_NASCIMENTO, NOME) VALUES ('1997-05-11', 'Ramon Nascimento Vanderlei');
INSERT INTO pessoa (DATA_DE_NASCIMENTO, NOME) VALUES ('1987-02-15', 'Máuro Silva');
INSERT INTO pessoa (DATA_DE_NASCIMENTO, NOME) VALUES ('1992-09-25', 'Maria Souza');
INSERT INTO pessoa (DATA_DE_NASCIMENTO, NOME) VALUES ('1990-12-03', 'Paulo Santos');
INSERT INTO pessoa (DATA_DE_NASCIMENTO, NOME) VALUES ('1998-04-22', 'Ana Oliveira');

INSERT INTO cep(CEP, RUA, BAIRRO, CIDADE, UF) Values ('28925732','Rua Madre teresa de calcuta','aquarius','cabo frio','RJ');
INSERT INTO cep(CEP, RUA, BAIRRO, CIDADE, UF) Values ('28909490','Rua Madre teresa de calcuta','aquarius','cabo frio','RJ');
INSERT INTO cep(CEP, RUA, BAIRRO, CIDADE, UF) Values ('28907410','Rua Madre teresa de calcuta','aquarius','cabo frio','RJ');
INSERT INTO cep(CEP, RUA, BAIRRO, CIDADE, UF) Values ('28922030','Rua Madre teresa de calcuta','aquarius','cabo frio','RJ');
INSERT INTO cep(CEP, RUA, BAIRRO, CIDADE, UF) Values ('28905020','Rua Madre teresa de calcuta','aquarius','cabo frio','RJ');
INSERT INTO cep(CEP, RUA, BAIRRO, CIDADE, UF) Values ('29025777','Rua Madre teresa de calcuta','aquariús','cabo frio','RJ');


INSERT INTO endereco ( CEP,  ENDERECO_PRINCIPAL, NUMERO, PESSOA_ID) VALUES ('28925732','TRUE','122','1');
INSERT INTO endereco ( CEP,  ENDERECO_PRINCIPAL, NUMERO, PESSOA_ID) VALUES ('28909490','TRUE','98','2');
INSERT INTO endereco ( CEP,  ENDERECO_PRINCIPAL, NUMERO, PESSOA_ID) VALUES ('28907410','TRUE','77','3');
INSERT INTO endereco ( CEP,  ENDERECO_PRINCIPAL, NUMERO, PESSOA_ID) VALUES ('28922030','TRUE','122','4');
INSERT INTO endereco ( CEP,  ENDERECO_PRINCIPAL, NUMERO, PESSOA_ID) VALUES ('28905020','TRUE','122','5');
INSERT INTO endereco ( CEP,  ENDERECO_PRINCIPAL, NUMERO, PESSOA_ID) VALUES ('29025777','FALSE','122','1');

-- INSERT INTO USUARIO_ROLE(ID_ROLE,ID_USUARIO) VALUES ('1','1');