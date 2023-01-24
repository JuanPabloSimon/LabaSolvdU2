-- use musichall;



-- insert persons 
insert into person (name, lastname, age) values ("Alex", "Lifeson", 50);
insert into person (name, lastname, age) values ("Gaddy", "Lee", 55);
insert into person (name, lastname, age) values ("Neil", "Peart", 53);
insert into person (name, lastname, age) values ("Kurt", "Cobain", 22);
insert into person (name, lastname, age) values ("Krist", "Novoselic", 24);
insert into person (name, lastname, age) values ("Dave", "Grohl", 22);
insert into person (name, lastname, age) values ("Miley", "Cirus", 25);
insert into person (name, lastname, age) values ("Antony", "Becker", 30);
insert into person (name, lastname, age) values ("Aston", "Kutchet", 24);
insert into person (name, lastname, age) values ("Jennifer", "Aniston", 20);
insert into person (name, lastname, age) values ("Adam", "Sandler", 29);
insert into person (name, lastname, age) values ("Chris", "Pratt", 31);

-- insert band 
insert into bands (name, membersAmount, genre) values ("Nirvana", 3, "Alternative Rock");
insert into bands (name, membersAmount, genre) values ("Rush", 3, "Hard Rock");


-- insert musician
insert into musician (Person_idPerson,Bands_idBands, Role) values (1 , 2, "Guitar, synthesizers");  
insert into musician (Person_idPerson,Bands_idBands, Role) values (2 , 2, "Singer, bassGuitar, keyboard, guitarr");
insert into musician (Person_idPerson,Bands_idBands, Role) values (3 , 2, "Drums, percusion");
insert into musician (Person_idPerson,Bands_idBands, Role) values (4 , 1, "Singer, guitar");
insert into musician (Person_idPerson,Bands_idBands, Role) values (5 , 1, "bass guitar");
insert into musician (Person_idPerson,Bands_idBands, Role) values (6, 1, "drums, chores");

-- insert concertservice 
insert into concertService (serviceName, type) values ("Los Pollos Hermanos", "FoodTruck");
insert into concertService (serviceName, type) values ("TacoBell", "FoodTruck");

-- insert musichall 
insert into musicHall (name, scenariosAmount) values ("Universe MusicHall", 2);

-- insert scenarioRoom
insert into scenarioRoom (name, capability, MusicHall_idMusicHall) values ("Scenario 1", 500, 1), ("Scenario 2", 600, 1);

-- insert concert
insert into concert (durationMin,Bands_idBands, ScenarioRoom_idScenario, date) values (120, 1, 1, STR_TO_DATE('07-25-2023','%m-%d-%Y')), (150, 2, 1, STR_TO_DATE('10-20-2023','%m-%d-%Y'));

-- insert seats 
insert into seats (number, reserved, Scenario_idScenario) values (1,1,1), (2,1,1), (3,1,1), (4,1,1);

-- insert tickets
insert into tickets (value, Concert_idConcert, Person_idPerson, Seats_idSeats) values (10, 1, 7, 1), (10, 1, 8, 2), (15, 2, 9, 3), (15, 2, 10, 4);
                                                                        

-- insert employee
insert into employees (role, Person_idPerson, MusicHall_idMusicHall) values ("receptionist", 11, 1), ("security", 12, 1); 

-- insert cleanservice 
insert into cleanService (name, type, time, price) values ("Cleaning Company", "Seats Cleaning", "60", 200);

-- insert cleaningscenario
insert into cleaningScenario (ScenarioRoom_idScenario,CleanService_idCleanService) values (1,1);

-- insert concert_has_concertservice
insert into  concert_has_ConcertService (Concert_idConcert,ConcertService_idConcertService) values (1,1), (1,1);


-- alter operation 
-- alter table scenarioroom  change `name` `scenarioName` varchar(45);
