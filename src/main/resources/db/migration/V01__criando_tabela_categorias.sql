CREATE TABLE categoria (
    codigo BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
    nome VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

INSERT algamoneyapi.categoria (nome) VALUES ('Lazer');
INSERT algamoneyapi.categoria (nome) VALUES ('Alimentação');
INSERT algamoneyapi.categoria (nome) VALUES ('Supermercados');
INSERT algamoneyapi.categoria (nome) VALUES ('Farmácia');
INSERT algamoneyapi.categoria (nome) VALUES ('Outros');