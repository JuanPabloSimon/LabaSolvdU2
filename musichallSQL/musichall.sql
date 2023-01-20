
-- -----------------------------------------------------
-- Schema musichall
-- -----------------------------------------------------
DROP SCHEMA IF EXISTS `musichall` ;

-- -----------------------------------------------------
-- Schema musichall
-- -----------------------------------------------------
CREATE SCHEMA IF NOT EXISTS `musichall` DEFAULT CHARACTER SET utf8 ;
USE `musichall` ;

-- -----------------------------------------------------
-- Table `musichall`.`Bands`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`Bands` (
  `idBands` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `membersAmount` INT NULL,
  `genre` VARCHAR(25) NULL,
  PRIMARY KEY (`idBands`),
  UNIQUE INDEX `idBands_UNIQUE` (`idBands` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `musichall`.`MusicHall`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`MusicHall` (
  `idMusicHall` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `scenariosAmount` INT NULL,
  PRIMARY KEY (`idMusicHall`),
  UNIQUE INDEX `idMusicHall_UNIQUE` (`idMusicHall` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `musichall`.`ScenarioRoom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`ScenarioRoom` (
  `idScenario` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `capability` INT NOT NULL,
  `MusicHall_idMusicHall` INT NOT NULL,
  PRIMARY KEY (`idScenario`),
  UNIQUE INDEX `idScenario_UNIQUE` (`idScenario` ASC) VISIBLE,
  FOREIGN KEY (`MusicHall_idMusicHall`) REFERENCES `musichall`.`MusicHall` (`idMusicHall`));


-- -----------------------------------------------------
-- Table `musichall`.`Concert`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`Concert` (
  `idConcert` INT NOT NULL AUTO_INCREMENT,
  `durationMin` FLOAT NOT NULL,
  `Bands_idBands` INT NOT NULL,
  `ScenarioRoom_idScenario` INT NOT NULL,
  PRIMARY KEY (`idConcert`),
  UNIQUE INDEX `idConcert_UNIQUE` (`idConcert` ASC) VISIBLE,
  FOREIGN KEY (`Bands_idBands`) REFERENCES `musichall`.`Bands` (`idBands`),
  FOREIGN KEY (`ScenarioRoom_idScenario`) REFERENCES `musichall`.`ScenarioRoom` (`idScenario`));



-- -----------------------------------------------------
-- Table `musichall`.`Seats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`Seats` (
  `idSeats` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `reserved` TINYINT NULL DEFAULT 0,
  `Scenario_idScenario` INT NOT NULL,
  PRIMARY KEY (`idSeats`),
  UNIQUE INDEX `idSeats_UNIQUE` (`idSeats` ASC) VISIBLE,
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) VISIBLE,
  FOREIGN KEY (`Scenario_idScenario`) REFERENCES `musichall`.`ScenarioRoom` (`idScenario`));



-- -----------------------------------------------------
-- Table `musichall`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`Person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `age` INT NULL,
  PRIMARY KEY (`idPerson`),
  UNIQUE INDEX `idPerson_UNIQUE` (`idPerson` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `musichall`.`Tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`Tickets` (
  `idTickets` INT NOT NULL AUTO_INCREMENT,
  `value` FLOAT NULL,
  `Concert_idConcert` INT NOT NULL,
  `Person_idPerson` INT NOT NULL,
  `Seats_idSeats` INT NOT NULL,
  PRIMARY KEY (`idTickets`, `Concert_idConcert`),
  UNIQUE INDEX `idTickets_UNIQUE` (`idTickets` ASC) VISIBLE,
  FOREIGN KEY (`Concert_idConcert`) REFERENCES `musichall`.`Concert` (`idConcert`),
  FOREIGN KEY (`Person_idPerson`) REFERENCES `musichall`.`Person` (`idPerson`),
  FOREIGN KEY (`Seats_idSeats`) REFERENCES `musichall`.`Seats` (`idSeats`));

-- -----------------------------------------------------
-- Table `musichall`.`Musician`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`Musician` (
  `idMusician` INT NOT NULL AUTO_INCREMENT,
  `Person_idPerson` INT NOT NULL,
  `Bands_idBands` INT NOT NULL,
  `Role` VARCHAR(45) NULL,
  PRIMARY KEY (`idMusician`),
  UNIQUE INDEX `idMusician_UNIQUE` (`idMusician` ASC) VISIBLE,
  FOREIGN KEY (`Person_idPerson`) REFERENCES `musichall`.`Person` (`idPerson`),
  FOREIGN KEY (`Bands_idBands`) REFERENCES `musichall`.`Bands` (`idBands`));

-- -----------------------------------------------------
-- Table `musichall`.`CleanService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`CleanService` (
  `idCleanService` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `time` FLOAT NULL,
  `price` FLOAT NULL,
  PRIMARY KEY (`idCleanService`),
  UNIQUE INDEX `idCleanService_UNIQUE` (`idCleanService` ASC) VISIBLE);

-- -----------------------------------------------------
-- Table `musichall`.`CleaningScenario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`CleaningScenario` (
  `idCleaning` INT NOT NULL AUTO_INCREMENT,
  `ScenarioRoom_idScenario` INT NOT NULL,
  `CleanService_idCleanService` INT NOT NULL,
  PRIMARY KEY (`idCleaning`),
  UNIQUE INDEX `CleaningScenariocol_UNIQUE` (`idCleaning` ASC) VISIBLE,
  FOREIGN KEY (`ScenarioRoom_idScenario`) REFERENCES `musichall`.`ScenarioRoom` (`idScenario`),
  FOREIGN KEY (`CleanService_idCleanService`) REFERENCES `musichall`.`CleanService` (`idCleanService`));


-- -----------------------------------------------------
-- Table `musichall`.`ConcertService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`ConcertService` (
  `idConcertService` INT NOT NULL AUTO_INCREMENT,
  `serviceName` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`idConcertService`),
  UNIQUE INDEX `idConcertService_UNIQUE` (`idConcertService` ASC) VISIBLE);


-- -----------------------------------------------------
-- Table `musichall`.`Concert_has_ConcertService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`Concert_has_ConcertService` (
  `Concert_idConcert` INT NOT NULL,
  `ConcertService_idConcertService` INT NOT NULL,
  PRIMARY KEY (`Concert_idConcert`, `ConcertService_idConcertService`),
  FOREIGN KEY (`Concert_idConcert`) REFERENCES `musichall`.`Concert` (`idConcert`),
  FOREIGN KEY (`ConcertService_idConcertService`) REFERENCES `musichall`.`ConcertService` (`idConcertService`));


-- -----------------------------------------------------
-- Table `musichall`.`Employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`Employees` (
  `idEmployees` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NULL,
  `Person_idPerson` INT NOT NULL,
  `MusicHall_idMusicHall` INT NOT NULL,
  PRIMARY KEY (`idEmployees`, `MusicHall_idMusicHall`),
  UNIQUE INDEX `idEmployees_UNIQUE` (`idEmployees` ASC) VISIBLE,
  FOREIGN KEY (`Person_idPerson`) REFERENCES `musichall`.`Person` (`idPerson`),
  FOREIGN KEY (`MusicHall_idMusicHall`) REFERENCES `musichall`.`MusicHall` (`idMusicHall`));