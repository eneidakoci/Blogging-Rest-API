CREATE TABLE categories (
                            deleted bit(1) NOT NULL,
                            id int NOT NULL AUTO_INCREMENT,
                            name varchar(255) DEFAULT NULL,
                            PRIMARY KEY (id)
) ;


CREATE TABLE player_profiles (
                                 deleted bit(1) NOT NULL,
                                 id int NOT NULL AUTO_INCREMENT,
                                 twitter varchar(255) DEFAULT NULL,
                                 PRIMARY KEY (id)
) ;

CREATE TABLE players (
                         birth_date date DEFAULT NULL,
                         deleted bit(1) NOT NULL,
                         id int NOT NULL AUTO_INCREMENT,
                         profile_id int DEFAULT NULL,
                         titles int DEFAULT NULL,
                         name varchar(255) DEFAULT NULL,
                         nationality varchar(255) DEFAULT NULL,
                         PRIMARY KEY (id),
                         UNIQUE KEY UK_r8b77i75m5q6wjhb0cpcd7rw8 (profile_id),
                         CONSTRAINT FKl7ioneluyr0jbgg54sjkpnvja FOREIGN KEY (profile_id) REFERENCES player_profiles (id)
);

CREATE TABLE tournaments (
                             deleted bit(1) NOT NULL,
                             id int NOT NULL AUTO_INCREMENT,
                             location varchar(255) DEFAULT NULL,
                             name varchar(255) DEFAULT NULL,
                             PRIMARY KEY (id)
) ;

CREATE TABLE tournament_category (
                                     category_id int NOT NULL,
                                     tournament_id int NOT NULL,
                                     KEY FK7cvfh0lrmr2tuiabqqwru2afl (tournament_id),
                                     KEY FKot59kf0sxpvr0585tyov0h37d (category_id),
                                     CONSTRAINT FK7cvfh0lrmr2tuiabqqwru2afl FOREIGN KEY (tournament_id) REFERENCES tournaments (id),
                                     CONSTRAINT FKot59kf0sxpvr0585tyov0h37d FOREIGN KEY (category_id) REFERENCES categories (id)
) ;

CREATE TABLE registrations (
                               deleted bit(1) NOT NULL,
                               id int NOT NULL AUTO_INCREMENT,
                               player_id int DEFAULT NULL,
                               tournament_id int DEFAULT NULL,
                               PRIMARY KEY (id),
                               KEY FKsafnoy6nui7k31ii0u098h0aa (player_id),
                               KEY FKt7v16jbn0b1ox6xc3vla3g365 (tournament_id),
                               CONSTRAINT FKsafnoy6nui7k31ii0u098h0aa FOREIGN KEY (player_id) REFERENCES players (id),
                               CONSTRAINT FKt7v16jbn0b1ox6xc3vla3g365 FOREIGN KEY (tournament_id) REFERENCES tournaments (id)
) ;
