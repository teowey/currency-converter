/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  teowey
 * Created: Sep 27, 2016
 */
CREATE TABLE RATES (
    ID INTEGER NOT NULL,
    CONVERSION VARCHAR(255),    
    RATE DOUBLE,    
    PRIMARY KEY (ID)
);

INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (1, 'BRLEUR', 0.27);
INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (2, 'BRLSEK', 2.63);
INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (3, 'BRLUSD', 0.3);
INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (4, 'EURBRL', 3.64);
INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (5, 'EURSEK', 9.58);
INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (6, 'EURUSD', 1.12);
INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (7, 'SEKBRL', 0.37);
INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (8, 'SEKEUR', 0.1);
INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (9, 'SEKUSD', 0.11);
INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (10, 'USDBRL', 3.24);
INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (11, 'USDEUR', 0.89);
INSERT INTO ROOT.RATES (ID, CONVERSION, RATE) 
	VALUES (12, 'USDSEK', 8.54);

