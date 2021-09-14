/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  mandy
 * Created: 13 de set de 2021
 */

CREATE TABLE integrante(
    id serial PRIMARY KEY,
    nome VARCHAR(50),
    dataDeNascimento DATE,
    CPF VARCHAR(15)
);

CREATE TABLE banda(
    id SERIAL PRIMARY KEY,
    localDeOrigem VARCHAR(100),
    nomeFantasia VARCHAR(100)
);

CREATE TABLE integrante_banda(
    id_banda int,
    id_integrante int,
    FOREIGN KEY (id_banda) REFERENCES banda(id) ON DELETE RESTRICT,
    FOREIGN KEY (id_integrante) REFERENCES integrante(id) ON DELETE
    RESTRICT,
    PRIMARY KEY(id_banda,id_integrante)
);