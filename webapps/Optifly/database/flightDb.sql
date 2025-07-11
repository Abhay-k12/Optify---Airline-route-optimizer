CREATE DATABASE IF NOT EXISTS flightdb;

USE flightdb;

CREATE TABLE IF NOT EXISTS flights (
    FlightId INT(11) NOT NULL,
    source VARCHAR(25)  NOT NULL,
    destination VARCHAR(25)  NOT NULL,
    flightDate VARCHAR(20)  NOT NULL,
    landingDate VARCHAR(20)  NOT NULL,
    ecoClCost INT(11) NOT NULL,
    bussiClCost INT(11) NOT NULL,
    firstClCost INT(11) NOT NULL,
    PRIMARY KEY (FlightId)
) ;
