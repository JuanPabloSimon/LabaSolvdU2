
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
CREATE TABLE IF NOT EXISTS `musichall`.`bands` (
  `idBands` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NOT NULL,
  `membersAmount` INT NULL,
  `genre` VARCHAR(25) NULL,
  PRIMARY KEY (`idBands`),
  UNIQUE INDEX `idBands_UNIQUE` (`idBands` ASC));


-- -----------------------------------------------------
-- Table `musichall`.`MusicHall`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`musicHall` (
  `idMusicHall` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `scenariosAmount` INT NULL,
  PRIMARY KEY (`idMusicHall`),
  UNIQUE INDEX `idMusicHall_UNIQUE` (`idMusicHall` ASC) );


-- -----------------------------------------------------
-- Table `musichall`.`ScenarioRoom`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`scenarioRoom` (
  `idScenario` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `capability` INT NOT NULL,
  `MusicHall_idMusicHall` INT NOT NULL,
  PRIMARY KEY (`idScenario`),
  UNIQUE INDEX `idScenario_UNIQUE` (`idScenario` ASC) ,
  FOREIGN KEY (`MusicHall_idMusicHall`) REFERENCES `musichall`.`musicHall` (`idMusicHall`));


-- -----------------------------------------------------
-- Table `musichall`.`Concert`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`concert` (
  `idConcert` INT NOT NULL AUTO_INCREMENT,
  `durationMin` FLOAT NOT NULL,
  `Bands_idBands` INT NOT NULL,
  `date` DATE NOT NULL,
  `ScenarioRoom_idScenario` INT NOT NULL,
  PRIMARY KEY (`idConcert`),
  UNIQUE INDEX `idConcert_UNIQUE` (`idConcert` ASC) ,
  FOREIGN KEY (`Bands_idBands`) REFERENCES `musichall`.`bands` (`idBands`),
  FOREIGN KEY (`ScenarioRoom_idScenario`) REFERENCES `musichall`.`scenarioRoom` (`idScenario`));



-- -----------------------------------------------------
-- Table `musichall`.`Seats`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`seats` (
  `idSeats` INT NOT NULL AUTO_INCREMENT,
  `number` INT NOT NULL,
  `reserved` TINYINT NULL DEFAULT 0,
  `Scenario_idScenario` INT NOT NULL,
  PRIMARY KEY (`idSeats`),
  UNIQUE INDEX `idSeats_UNIQUE` (`idSeats` ASC) ,
  UNIQUE INDEX `number_UNIQUE` (`number` ASC) ,
  FOREIGN KEY (`Scenario_idScenario`) REFERENCES `musichall`.`scenarioRoom` (`idScenario`));



-- -----------------------------------------------------
-- Table `musichall`.`Person`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`person` (
  `idPerson` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `lastname` VARCHAR(45) NULL,
  `age` INT NULL,
  PRIMARY KEY (`idPerson`),
  UNIQUE INDEX `idPerson_UNIQUE` (`idPerson` ASC) );


-- -----------------------------------------------------
-- Table `musichall`.`Tickets`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`tickets` (
  `idTickets` INT NOT NULL AUTO_INCREMENT,
  `value` FLOAT NULL,
  `Concert_idConcert` INT NOT NULL,
  `Person_idPerson` INT NOT NULL,
  `Seats_idSeats` INT NOT NULL,
  PRIMARY KEY (`idTickets`, `Concert_idConcert`),
  UNIQUE INDEX `idTickets_UNIQUE` (`idTickets` ASC) ,
  FOREIGN KEY (`Concert_idConcert`) REFERENCES `musichall`.`concert` (`idConcert`),
  FOREIGN KEY (`Person_idPerson`) REFERENCES `musichall`.`person` (`idPerson`),
  FOREIGN KEY (`Seats_idSeats`) REFERENCES `musichall`.`seats` (`idSeats`));

-- -----------------------------------------------------
-- Table `musichall`.`Musician`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`musician` (
  `idMusician` INT NOT NULL AUTO_INCREMENT,
  `Person_idPerson` INT NOT NULL,
  `Bands_idBands` INT NOT NULL,
  `Role` VARCHAR(45) NULL,
  PRIMARY KEY (`idMusician`),
  UNIQUE INDEX `idMusician_UNIQUE` (`idMusician` ASC) ,
  FOREIGN KEY (`Person_idPerson`) REFERENCES `musichall`.`person` (`idPerson`),
  FOREIGN KEY (`Bands_idBands`) REFERENCES `musichall`.`bands` (`idBands`));

-- -----------------------------------------------------
-- Table `musichall`.`CleanService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`cleanService` (
  `idCleanService` INT NOT NULL AUTO_INCREMENT,
  `name` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  `time` FLOAT NULL,
  `price` FLOAT NULL,
  PRIMARY KEY (`idCleanService`),
  UNIQUE INDEX `idCleanService_UNIQUE` (`idCleanService` ASC) );

-- -----------------------------------------------------
-- Table `musichall`.`CleaningScenario`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`cleaningScenario` (
  `idCleaning` INT NOT NULL AUTO_INCREMENT,
  `ScenarioRoom_idScenario` INT NOT NULL,
  `CleanService_idCleanService` INT NOT NULL,
  PRIMARY KEY (`idCleaning`),
  UNIQUE INDEX `CleaningScenariocol_UNIQUE` (`idCleaning` ASC) ,
  FOREIGN KEY (`ScenarioRoom_idScenario`) REFERENCES `musichall`.`scenarioRoom` (`idScenario`),
  FOREIGN KEY (`CleanService_idCleanService`) REFERENCES `musichall`.`cleanService` (`idCleanService`));


-- -----------------------------------------------------
-- Table `musichall`.`ConcertService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`concertService` (
  `idConcertService` INT NOT NULL AUTO_INCREMENT,
  `serviceName` VARCHAR(45) NULL,
  `type` VARCHAR(45) NULL,
  PRIMARY KEY (`idConcertService`),
  UNIQUE INDEX `idConcertService_UNIQUE` (`idConcertService` ASC) );


-- -----------------------------------------------------
-- Table `musichall`.`Concert_has_ConcertService`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`concert_has_ConcertService` (
	  `idConcert_has_ConcertService` INT NOT NULL AUTO_INCREMENT,
	  `Concert_idConcert` INT NOT NULL,
  `ConcertService_idConcertService` INT NOT NULL,
  PRIMARY KEY (`idConcert_has_ConcertService`),
  FOREIGN KEY (`Concert_idConcert`) REFERENCES `musichall`.`concert` (`idConcert`),
  FOREIGN KEY (`ConcertService_idConcertService`) REFERENCES `musichall`.`concertService` (`idConcertService`));


-- -----------------------------------------------------
-- Table `musichall`.`Employees`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `musichall`.`employees` (
  `idEmployees` INT NOT NULL AUTO_INCREMENT,
  `role` VARCHAR(45) NULL,
  `Person_idPerson` INT NOT NULL,
  `MusicHall_idMusicHall` INT NOT NULL,
  PRIMARY KEY (`idEmployees`, `MusicHall_idMusicHall`),
  UNIQUE INDEX `idEmployees_UNIQUE` (`idEmployees` ASC) ,
  FOREIGN KEY (`Person_idPerson`) REFERENCES `musichall`.`person` (`idPerson`),
  FOREIGN KEY (`MusicHall_idMusicHall`) REFERENCES `musichall`.`musicHall` (`idMusicHall`));