ALTER TABLE usuario ADD COLUMN id_autenticacao_usuario int NOT NULL;
ALTER TABLE usuario ADD CONSTRAINT Usuario_Autenticacao_FK FOREIGN KEY (id_autenticacao_usuario) REFERENCES autenticacao_usuario (id);