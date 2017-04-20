-- MySQL Script generated by MySQL Workbench
-- 04/20/17 08:17:19
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

-- -----------------------------------------------------
-- Schema controle_estoque
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema controle_estoque
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `controle_estoque` DEFAULT CHARACTER SET utf8 ;
USE `controle_estoque` ;

-- -----------------------------------------------------
-- Table `controle_estoque`.`Fornecedor`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_estoque`.`Fornecedor` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `dataNascimento`  VARCHAR(10) NULL,
  `telefone` VARCHAR(16) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `nomeLoja` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle_estoque`.`Departamento`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_estoque`.`Departamento` (
  `codDepartamento` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codDepartamento`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle_estoque`.`Categoria`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_estoque`.`Categoria` (
  `codCategoria` INT NOT NULL AUTO_INCREMENT,
  `descricao` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`codCategoria`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle_estoque`.`Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_estoque`.`Produto` (
  `codProduto` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `valorUnitario` DOUBLE NOT NULL,
  `codCategoria` INT NOT NULL,
  `quantidade` INT NOT NULL,
  `codFornecedor` INT NOT NULL,
  PRIMARY KEY (`codProduto`, `codFornecedor`),
  INDEX `codCategoria_idx` (`codCategoria` ASC),
  INDEX `fk_Produto_Fornecedor1_idx` (`codFornecedor` ASC),
  CONSTRAINT `codCategoria`
    FOREIGN KEY (`codCategoria`)
    REFERENCES `controle_estoque`.`Categoria` (`codCategoria`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Produto_Fornecedor1`
    FOREIGN KEY (`codFornecedor`)
    REFERENCES `controle_estoque`.`Fornecedor` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle_estoque`.`Funcionario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_estoque`.`Funcionario` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `nome` VARCHAR(45) NOT NULL,
  `cpf` VARCHAR(14) NOT NULL,
  `endereco` VARCHAR(45) NOT NULL,
  `dataNascimento` VARCHAR(16) NULL,
  `telefone` VARCHAR(16) NOT NULL,
  `email` VARCHAR(45) NOT NULL,
  `login` VARCHAR(45) NOT NULL,
  `senha` VARCHAR(20) NOT NULL,
  `idDepartamento` INT NULL,
  PRIMARY KEY (`id`),
  INDEX `idDepartamento_idx` (`idDepartamento` ASC),
  CONSTRAINT `idDepartamento`
    FOREIGN KEY (`idDepartamento`)
    REFERENCES `controle_estoque`.`Departamento` (`codDepartamento`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle_estoque`.`Saida`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_estoque`.`Saida` (
  `codSaida` INT NOT NULL AUTO_INCREMENT,
  `codFuncionario` INT NOT NULL,
  `dataSaida` DATE NOT NULL,
  PRIMARY KEY (`codSaida`),
  INDEX `codFuncionario_idx` (`codFuncionario` ASC),
  CONSTRAINT `codFuncionario`
    FOREIGN KEY (`codFuncionario`)
    REFERENCES `controle_estoque`.`Funcionario` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle_estoque`.`Pedido`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_estoque`.`Pedido` (
  `codPedido` INT NOT NULL AUTO_INCREMENT,
  `dataPedido` DATE NOT NULL,
  PRIMARY KEY (`codPedido`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle_estoque`.`Pedido_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_estoque`.`Pedido_Produto` (
  `idPedido` INT NOT NULL,
  `idProduto` INT NOT NULL,
  PRIMARY KEY (`idPedido`, `idProduto`),
  INDEX `idProduto_idx` (`idProduto` ASC),
  CONSTRAINT `idProduto`
    FOREIGN KEY (`idProduto`)
    REFERENCES `controle_estoque`.`Produto` (`codProduto`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idPedido`
    FOREIGN KEY (`idPedido`)
    REFERENCES `controle_estoque`.`Pedido` (`codPedido`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `controle_estoque`.`Saida_Produto`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `controle_estoque`.`Saida_Produto` (
  `idSaida` INT NOT NULL,
  `idProduto` INT NOT NULL,
  PRIMARY KEY (`idSaida`, `idProduto`),
  CONSTRAINT `idProduto`
    FOREIGN KEY ()
    REFERENCES `controle_estoque`.`Produto` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `idSaida`
    FOREIGN KEY ()
    REFERENCES `controle_estoque`.`Saida` ()
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;


INSERT INTO CATEGORIA (DESCRICAO) VALUES ('ELETRÔNICOS');
INSERT INTO CATEGORIA (DESCRICAO) VALUES ('LIMPEZA');
INSERT INTO CATEGORIA (DESCRICAO) VALUES ('ALIMENTOS');
INSERT INTO CATEGORIA (DESCRICAO) VALUES ('NATURAL');


INSERT INTO DEPARTAMENTO (DESCRICAO) VALUES ('VENDAS');
INSERT INTO DEPARTAMENTO (DESCRICAO) VALUES ('ESTOQUE');
INSERT INTO DEPARTAMENTO (DESCRICAO) VALUES ('CONTABILIDADE');



