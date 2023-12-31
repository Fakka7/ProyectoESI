-- MySQL Script generated by MySQL Workbench
-- Mon Nov 28 09:34:11 2022
-- Model: New Model    Version: 1.0
-- MySQL Workbench Forward Engineering

SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='ONLY_FULL_GROUP_BY,STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_ENGINE_SUBSTITUTION';

-- -----------------------------------------------------
-- Schema mydb
-- -----------------------------------------------------
-- -----------------------------------------------------
-- Schema bdesi
-- -----------------------------------------------------

-- -----------------------------------------------------
-- Schema bdesi
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `bdesi` DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci ;
USE `bdesi` ;

-- -----------------------------------------------------
-- Table `bdesi`.`categorias`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdesi`.`categorias` (
  `id_Categorias` INT NOT NULL,
  `nombre_categoria` VARCHAR(100) NOT NULL,
  `descripcion` VARCHAR(100) NOT NULL,
  PRIMARY KEY (`id_Categorias`),
  UNIQUE INDEX `nombre_UNIQUE` (`nombre_categoria` ASC) VISIBLE)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdesi`.`jornada`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdesi`.`jornada` (
  `id_jornada` INT NOT NULL AUTO_INCREMENT,
  `referente_jornada` VARCHAR(45) NOT NULL,
  `titulo_jornada` VARCHAR(45) NOT NULL,
  `categoria` VARCHAR(45) NOT NULL,
  PRIMARY KEY (`id_jornada`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdesi`.`material`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdesi`.`material` (
  `id_material` INT NOT NULL AUTO_INCREMENT,
  `titulo_material` VARCHAR(45) NOT NULL,
  `categoria_material` INT NOT NULL,
  `descripcion_material` VARCHAR(500) NOT NULL,
  `fuente_material` VARCHAR(45) NOT NULL,
  `enlace_material` VARCHAR(45) NOT NULL,
  `tratamiento_material` VARCHAR(45) NULL DEFAULT NULL,
  `procedencia_material` VARCHAR(45) NULL DEFAULT NULL,
  PRIMARY KEY (`id_material`))
ENGINE = InnoDB
AUTO_INCREMENT = 4
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdesi`.`propuesta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdesi`.`propuesta` (
  `id_propuesta` INT NOT NULL AUTO_INCREMENT,
  `origen_propuesta` VARCHAR(45) NOT NULL,
  `categoria` INT NOT NULL,
  `autor_propuesta` VARCHAR(45) NULL DEFAULT NULL,
  `fecha_propuesta` DATE NOT NULL,
  `titulo_propuesta` VARCHAR(45) NOT NULL,
  `descripcion_propuesta` VARCHAR(500) NOT NULL,
  `motivacion_propuesta` VARCHAR(45) NOT NULL,
  `estado_propuesta` VARCHAR(45) NULL DEFAULT 'Pendiente',
  `motivoRechazo_propuesta` VARCHAR(200) NULL DEFAULT NULL,
  PRIMARY KEY (`id_propuesta`),
  UNIQUE INDEX `titulo_propuesta_UNIQUE` (`titulo_propuesta` ASC) VISIBLE,
  UNIQUE INDEX `id_propuesta_UNIQUE` (`id_propuesta` ASC) VISIBLE,
  INDEX `categoria_propuesta_idx` (`categoria` ASC) VISIBLE,
  CONSTRAINT `categoria_propuesta`
    FOREIGN KEY (`categoria`)
    REFERENCES `bdesi`.`categorias` (`id_Categorias`))
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdesi`.`matxpropuesta`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdesi`.`matxpropuesta` (
  `id_material` INT NOT NULL,
  `id_propuesta` INT NOT NULL,
  PRIMARY KEY (`id_material`, `id_propuesta`),
  INDEX `id_propuesta_idx` (`id_propuesta` ASC) VISIBLE,
  CONSTRAINT `id_material`
    FOREIGN KEY (`id_material`)
    REFERENCES `bdesi`.`material` (`id_material`),
  CONSTRAINT `id_propuesta`
    FOREIGN KEY (`id_propuesta`)
    REFERENCES `bdesi`.`propuesta` (`id_propuesta`))
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8mb3;


-- -----------------------------------------------------
-- Table `bdesi`.`jorxmaterial`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `bdesi`.`jorxmaterial` (
  `id_jornada` INT NOT NULL,
  `id_material` INT NOT NULL,
  PRIMARY KEY (`id_jornada`, `id_material`),
  INDEX `id_material_idx` (`id_material` ASC) VISIBLE,
  CONSTRAINT `id_jornada`
    FOREIGN KEY (`id_jornada`)
    REFERENCES `bdesi`.`jornada` (`id_jornada`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `id_material`
    FOREIGN KEY (`id_material`)
    REFERENCES `bdesi`.`material` (`id_material`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
