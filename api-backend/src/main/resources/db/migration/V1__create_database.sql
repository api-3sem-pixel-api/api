DROP TABLE IF EXISTS `Tipo_Usuario`;
CREATE TABLE `tipo_usuario` (
  `id` int NOT NULL,
  `descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `tipo_usuario` VALUES (1,'Colaborador'),
                                  (2,'Gestor'),
                                  (3,'Administrador');

DROP TABLE IF EXISTS `Modalidade`;
CREATE TABLE `modalidade` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(20) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `modalidade` VALUES (1,'Hora Extra'),
                                (2,'Sobreaviso');

DROP TABLE IF EXISTS `Etapa_Extrato`;
CREATE TABLE `etapa_extrato` (
  `id` int NOT NULL AUTO_INCREMENT,
  `descricao` varchar(40) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
INSERT INTO `etapa_extrato` VALUES (1,'Em Aprovação'),
                                   (2,'Aprovada'),
                                   (3,'Reprovada'),
                                   (4,'Cancelada');
DROP TABLE IF EXISTS `Cliente`;
CREATE TABLE `cliente` (
  `id` int NOT NULL AUTO_INCREMENT,
  `razao_Social` varchar(100) NOT NULL,
  `cnpj` varchar(14) NOT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Cr`;
CREATE TABLE `cr` (
  `id` int NOT NULL AUTO_INCREMENT,
  `nome` varchar(40) NOT NULL,
  `sigla` varchar(50) DEFAULT NULL,
  `codigo_cr` varchar(50) DEFAULT NULL,
  PRIMARY KEY (`Id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Usuario`;
CREATE TABLE `usuario` (
  `id` int NOT NULL AUTO_INCREMENT,
  `id_tipo_usuario` int NOT NULL,
  `cpf_cnpj` varchar(14) NOT NULL,
  `nome` varchar(150) NOT NULL,
  `telefone` int DEFAULT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`Id`),
  KEY `id_tipo_usuario` (`id_tipo_usuario`),
  CONSTRAINT `Usuario_ibfk_1` FOREIGN KEY (`id_tipo_usuario`) REFERENCES `tipo_usuario` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;


DROP TABLE IF EXISTS `cr_usuario`;
CREATE TABLE `cr_usuario` (
  `id_usuario` int NOT NULL,
  `id_cr` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

DROP TABLE IF EXISTS `Extrato_Hora`;
CREATE TABLE `extrato_hora` (
  `id` int NOT NULL AUTO_INCREMENT,
  `projeto` varchar(100) NOT NULL,
  `id_cr` int NOT NULL,
  `id_usuario` int NOT NULL,
  `id_etapa_extrato` int NOT NULL,
  `id_modalidade` int NOT NULL,
  `motivo` varchar(300) DEFAULT NULL,
  `dataHora_inicio` datetime NOT NULL,
  `DataHora_fim` datetime NOT NULL,
  `justificativa` varchar(300) DEFAULT NULL,
  `id_cliente` int DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `id_cr` (`id_cr`),
  KEY `id_usuario` (`id_usuario`),
  KEY `id_etapa_extrato` (`id_etapa_extrato`),
  KEY `id_modalidade` (`id_modalidade`),
  KEY `id_cliente` (`id_cliente`),
  CONSTRAINT `Extrato_Hora_ibfk_1` FOREIGN KEY (`id_cr`) REFERENCES `cr` (`id`),
  CONSTRAINT `Extrato_Hora_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`),
  CONSTRAINT `Extrato_Hora_ibfk_3` FOREIGN KEY (`id_etapa_extrato`) REFERENCES `etapa_extrato` (`id`),
  CONSTRAINT `Extrato_Hora_ibfk_4` FOREIGN KEY (`id_modalidade`) REFERENCES `modalidade` (`id`),
  CONSTRAINT `Extrato_Hora_ibfk_6` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

ALTER TABLE cliente ADD COLUMN ativo bit DEFAULT 1;
ALTER TABLE cr_usuario ADD COLUMN ativo bit DEFAULT 1;
ALTER TABLE usuario ADD COLUMN ativo bit DEFAULT 1;

