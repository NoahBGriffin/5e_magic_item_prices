BEGIN TRANSACTION;

DROP TABLE IF EXISTS magic_item, rarities, price CASCADE;

CREATE TABLE rarities(
rarity varchar(15) NOT NULL,
recommended_character_level varchar(30) NOT NULL,
gp_lower_range int,
gp_upper_range int,

CONSTRAINT UQ_rarity UNIQUE (rarity),
CONSTRAINT PK_rarity PRIMARY KEY (rarity),
CONSTRAINT CHK_upper_range_lower_range CHECK (gp_lower_range < gp_upper_range)
);

CREATE TABLE magic_item(
item_id serial,
item_name varchar(100) NOT NULL,
item_type varchar(40) NOT NULL,
rarity varchar(15) NOT NULL,
description varchar(15000) NOT NULL,
attunement varchar(200),

CONSTRAINT PK_item_id PRIMARY KEY (item_id),
CONSTRAINT FK_rarity FOREIGN KEY (rarity) REFERENCES rarities(rarity)
);


CREATE TABLE price(
price_id serial,
price_gp int NOT NULL,
item_id int NOT NULL,
source varchar(200) NOT NULL,
source_notes varchar(1000),
user_id int,

CONSTRAINT PK_price_id PRIMARY KEY (price_id),
CONSTRAINT FK_item_id FOREIGN KEY (item_id) REFERENCES magic_item(item_id)
);


INSERT INTO rarities(rarity, recommended_character_level, gp_lower_range, gp_upper_range)
VALUES('common', '1st or higher', 50, 100);
INSERT INTO rarities(rarity, recommended_character_level, gp_lower_range, gp_upper_range)
VALUES('uncommon', '1st or higher', 101, 500);
INSERT INTO rarities(rarity, recommended_character_level, gp_lower_range, gp_upper_range)
VALUES('rare', '5th or higher', 501, 5000);
INSERT INTO rarities(rarity, recommended_character_level, gp_lower_range, gp_upper_range)
VALUES('very rare', '11th or higher', 5001, 50000);
INSERT INTO rarities(rarity, recommended_character_level, gp_lower_range)
VALUES('legendary', '17th or higher', 50001);
INSERT INTO rarities(rarity, recommended_character_level)
VALUES('artifact', 'N/A, apply with caution');

COMMIT;