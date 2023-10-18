
INSERT INTO user(id,name,nationality,birth_date,titles,profile_id)
VALUES (9,'Robert Abdesselam','Albania','1990-1-20',5,1,0),
       (10,'Jose Acasuso','Albania','1990-02-02',3,2,0),
       (11,'Wilmer Allison','Albania','1985-02-12',6,3,0),
       (12,'Nicolas Almagro','Albania','1980-01-15',0,4,0),
       (13,'Manuel Alonso','Albania','1989-07-20',0,5,0),
       (14,'Felicisimo Ampon','Albania','1983-10-20',1,6,0),
       (15,'John Andrews','Albania','1984-10-21',2,7,0),
       (16,'Jimmy Arias','Albania','1990-10-21',2,8,0);

INSERT INTO category(id,name)
VALUES (17,'MALE',0),
       (18,'Female',0);

INSERT INTO post(id,name,location,deleted)
VALUES (19,'ASB Classic','New Zeland',0),
       (20,'Nitto ATP Finals','New Zeland',0);

INSERT INTO post_category(category_id,post_id)
VALUES (17,19),
       (17,20),
       (18,19),
       (18,20);

