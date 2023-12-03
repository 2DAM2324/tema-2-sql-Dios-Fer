DROP TABLE IF EXISTS accesos;
DROP TABLE IF EXISTS partidas;
DROP TABLE IF EXISTS servidores;
DROP TABLE IF EXISTS jugadores;
DROP TABLE IF EXISTS inventarios;



CREATE TABLE servidores (
    id_servidor INTEGER,

    region VARCHAR,
    

    PRIMARY KEY (id_servidor)
);

CREATE TABLE jugadores (
    id_jugador INTEGER,

    nickName VARCHAR,
    nivel INTEGER,
    
    id_partida INTEGER,


    FOREIGN KEY (id_partida) REFERENCES partidas(id_partida),
    PRIMARY KEY (id_jugador)
);

CREATE TABLE inventarios (
    id_inventario INTEGER,

    slotsOcupados INTEGER,
    slotsMaximos INTEGER,
    
    
    PRIMARY KEY (id_inventario)
);

CREATE TABLE partidas (
    id_partida INTEGER,

    num_Espectadores INTEGER,

    id_servidor INTEGER,


    FOREIGN KEY (id_servidor) REFERENCES servidores(id_servidor),
    PRIMARY KEY (id_partida)
);

CREATE TABLE accesos (
    id_jugador INTEGER,
    id_inventario INTEGER,

    

    FOREIGN KEY (id_jugador) REFERENCES jugadores (id_jugador)
    FOREIGN KEY (id_inventario) REFERENCES jugadores (id_inventario)
    PRIMARY KEY (id_inventario, id_jugador)
);



INSERT INTO servidores (region) VALUES ('Europa');
INSERT INTO servidores (region) VALUES ('Espana');
INSERT INTO servidores (region) VALUES ('Asia');
INSERT INTO servidores (region) VALUES ('Asia');
INSERT INTO servidores (region) VALUES ('Europa');


INSERT INTO jugadores (nickName, nivel, id_partida) VALUES ('Fernando', '100', '1');
INSERT INTO jugadores (nickName, nivel, id_partida) VALUES ('Juan', '50', '2');
INSERT INTO jugadores (nickName, nivel, id_partida) VALUES ('DiosFer', '10', '1');
INSERT INTO jugadores (nickName, nivel, id_partida) VALUES ('Fairy', '34', '1');
INSERT INTO jugadores (nickName, nivel, id_partida) VALUES ('Malt', '43', null);


INSERT INTO partidas (num_Espectadores, id_servidor) VALUES ('5', '1');
INSERT INTO partidas (num_Espectadores, id_servidor) VALUES ('20', '2');
INSERT INTO partidas (num_Espectadores, id_servidor) VALUES ('0', '3');


INSERT INTO inventarios (slotsOcupados, slotsMaximos) VALUES ('100', '1000');
INSERT INTO inventarios (slotsOcupados, slotsMaximos) VALUES ('99', '999');
INSERT INTO inventarios (slotsOcupados, slotsMaximos) VALUES ('50', '100');
INSERT INTO inventarios (slotsOcupados, slotsMaximos) VALUES ('10', '100');


INSERT INTO accesos (id_inventario, id_jugador) VALUES ('1', '1');
INSERT INTO accesos (id_inventario, id_jugador) VALUES ('1', '2');
INSERT INTO accesos (id_inventario, id_jugador) VALUES ('1', '3');
INSERT INTO accesos (id_inventario, id_jugador) VALUES ('2', '1');
INSERT INTO accesos (id_inventario, id_jugador) VALUES ('3', '2');



--SELECT * FROM servidores;
--SELECT * FROM jugadores;
--SELECT * FROM partidas;
--SELECT * FROM inventarios;
--SELECT * FROM accesos;
