DROP TABLE IF EXISTS public."transaction";

DROP TABLE IF EXISTS public."car";

DROP TABLE IF EXISTS public."customer";

CREATE TABLE IF NOT EXISTS public.customer
(
	 id_customer SERIAL PRIMARY KEY, 
	 login VARCHAR(50) UNIQUE NOT NULL,
	 password VARCHAR(50) NOT NULL,
	 dowod VARCHAR(50) UNIQUE NOT NULL,
	 saldo INTEGER DEFAULT 0,
	 root boolean DEFAULT false
);

CREATE TABLE IF NOT EXISTS public.car
(
	 id_car SERIAL PRIMARY KEY, 
	 customer_id INTEGER DEFAULT NULL,
	 marka VARCHAR(50) NOT NULL,
	 model VARCHAR(50) NOT NULL,
	 cena INTEGER NOT NULL,
	 kaucja INTEGER NOT NULL,
	 dataWypozyczenia DATE DEFAULT NULL,
	 dlugoscWypozyczenia INTEGER DEFAULT 0,
	 CONSTRAINT car_customer_id_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id_customer) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

CREATE TABLE IF NOT EXISTS public.transaction
(
	 id_transaction SERIAL PRIMARY KEY, 
	 customer_id INTEGER NOT NULL,
	 car_id INTEGER NOT NULL,
	 dataOddania DATE DEFAULT NULL,
	 zatwierdzona BOOLEAN DEFAULT FALSE,
	 CONSTRAINT transaction_car_id_fkey FOREIGN KEY (car_id)
        REFERENCES public.car (id_car) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
	 CONSTRAINT transaction_customer_id_fkey FOREIGN KEY (customer_id)
        REFERENCES public.customer (id_customer) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
);

INSERT INTO customer (login, password, dowod, saldo, root)
	 VALUES ('mHm_MaXi', 'M@ciek69', 'ASR711013', 1000000, true),
		 ('Fryderyk11', 'M@ciek69', 'ACI837428', 40000, false),
		 ('KarzelDom', 'M@ciek69', 'AXS321139', 20000, false),
		 ('Spanish', 'M@ciek69', 'ARQ784071', 5000, false),
		 ('CookMaster', 'M@ciek69', 'AFJ592439', 1600, false),
		 ('ETG0H0M3', 'M@ciek69', 'AHQ237838', 620, false),
		 ('Twojamatka', 'M@ciek69', 'AKI548795', -180, false),
		 ('Bananek11', 'M@ciek69', 'ADK836974', 99900, false),
		 ('Zygmunt65', 'M@ciek69', 'AAD321517', 11500, false),
		 ('pazdzioch', 'M@ciek69', 'ACE327914', 75000, false),
		 ('Avicii', 'M@ciek69', 'AVS664041', 4700, false),
		 ('kwiatuszek', 'M@ciek69', 'AUT341296', 666, false),
		 ('CesarZ13', 'M@ciek69', 'AVN925189', 6660, false),
		 ('Opaqer', 'M@ciek69', 'ANI666014', 66600, false),
		 ('piotrolot1', 'M@ciek69', 'AKF208056', 11, false),
		 ('kaBallMane', 'M@ciek69', 'ACY562559', 35000, false),
		 ('SlimShady', 'M@ciek69', 'AXI904479', 47000, false),
		 ('Eminem_rihanna', 'M@ciek69', 'AXC130405', 14300, false),
		 ('rozkurwiator666', 'M@ciek69', 'ATY407657', -4700, false),
		 ('janekjmf', 'M@ciek69', 'FBI405457', 1000000, true);

INSERT INTO car (marka, model, cena, kaucja)
		 VALUES ('Mercedes', 'Vito', 25, 5000),
			 ('Mercedes', 'Sprinter', 30, 5000),
			 ('Toyota', 'Yaris', 15, 2500),
			 ('Toyota', 'Auris', 15, 3000),
			 ('Skoda', 'Fabia', 15, 3000),
			 ('Ford', 'Ka', 15, 2500),
			 ('Renault', 'Twingo', 15, 2500),
			 ('Citroen', 'DS3', 15, 3000),
			 ('Mercedes', 'Klassa C', 30, 7000),
			 ('Bentley', 'Continental', 70, 25000),
			 ('BMW', '7 Series', 50, 20000),
			 ('Aston Martin', 'BD9', 70, 35000),
			 ('Maserati', 'Ghibli', 70, 30000),
			 ('Ford', 'Mustang', 50, 15000),
			 ('Mercedes', 'SLK', 50, 20000),
			 ('Lamborghini', 'Huracan', 80, 45000),
			 ('Chevrolet', 'Corvette C7', 60, 23000),
			 ('SRT', 'Viper', 60, 28000),
			 ('BMW', 'M3', 40, 13000);

UPDATE car SET customer_id=null, dataWypozyczenia=null, dlugoscWypozyczenia=0;

UPDATE car SET customer_id=10, dataWypozyczenia='2020-04-23', dlugoscWypozyczenia=3 WHERE id_car=11;
UPDATE car SET customer_id=10, dataWypozyczenia='2020-04-20', dlugoscWypozyczenia=5 WHERE id_car=12;
UPDATE car SET customer_id=11, dataWypozyczenia='2020-04-23', dlugoscWypozyczenia=7 WHERE id_car=13;
UPDATE car SET customer_id=11, dataWypozyczenia='2020-04-20', dlugoscWypozyczenia=3 WHERE id_car=14;
UPDATE car SET customer_id=12, dataWypozyczenia='2020-04-27', dlugoscWypozyczenia=3 WHERE id_car=15;
UPDATE car SET customer_id=12, dataWypozyczenia='2020-04-25', dlugoscWypozyczenia=1 WHERE id_car=16;
UPDATE car SET customer_id=13, dataWypozyczenia='2020-04-21', dlugoscWypozyczenia=13 WHERE id_car=17;
UPDATE car SET customer_id=13, dataWypozyczenia='2020-04-24', dlugoscWypozyczenia=2 WHERE id_car=18;
UPDATE car SET customer_id=15, dataWypozyczenia='2020-02-27', dlugoscWypozyczenia=3 WHERE id_car=19;
UPDATE car SET customer_id=15, dataWypozyczenia='2020-04-25', dlugoscWypozyczenia=2 WHERE id_car=10;

INSERT INTO transaction (customer_id, car_id)
				 VALUES (10, 11);
INSERT INTO transaction (customer_id, car_id)
				 VALUES (10, 12);
INSERT INTO transaction (customer_id, car_id)
				 VALUES (11, 13);
INSERT INTO transaction (customer_id, car_id)
				 VALUES (11, 14);				 
INSERT INTO transaction (customer_id, car_id)
				 VALUES (12, 15);
INSERT INTO transaction (customer_id, car_id)
				 VALUES (12, 16);
INSERT INTO transaction (customer_id, car_id)
				 VALUES (13, 17);
INSERT INTO transaction (customer_id, car_id)
				 VALUES (13, 18);
INSERT INTO transaction (customer_id, car_id)
				 VALUES (15, 19);
INSERT INTO transaction (customer_id, car_id)
				 VALUES (15, 10);
				 
/* NOW IT'S A BLOCK FOR DEVELOPERS TESTING COMMANDS, STOP COPYING *
 * NOW IT'S A BLOCK FOR DEVELOPERS TESTING COMMANDS, STOP COPYING *
 * NOW IT'S A BLOCK FOR DEVELOPERS TESTING COMMANDS, STOP COPYING *
 * NOW IT'S A BLOCK FOR DEVELOPERS TESTING COMMANDS, STOP COPYING *
 * NOW IT'S A BLOCK FOR DEVELOPERS TESTING COMMANDS, STOP COPYING *
 * NOW IT'S A BLOCK FOR DEVELOPERS TESTING COMMANDS, STOP COPYING *
 * NOW IT'S A BLOCK FOR DEVELOPERS TESTING COMMANDS, STOP COPYING *
 * NOW IT'S A BLOCK FOR DEVELOPERS TESTING COMMANDS, STOP COPYING *
 * NOW IT'S A BLOCK FOR DEVELOPERS TESTING COMMANDS, STOP COPYING *
 */

SELECT NOW()::date;

SELECT CURRENT_DATE;

SELECT * FROM car;

SELECT * FROM customer;

SELECT * FROM transaction;

SELECT * FROM customer;

SELECT * FROM car ORDER BY id_car;

DELETE FROM customer;

DELETE FROM car;

DELETE FROM transaction;

SELECT dowod, saldo, root FROM customer WHERE (login='mHm_MaXi' AND password='M@ciek69');

DELETE FROM customer WHERE login='Fryderyk11' AND root IS NOT TRUE;

INSERT INTO customer (login, password, dowod, saldo, root)
	 VALUES ('Fryderyk11', 'M@ciek69', 'ASR733013', 70000, true);