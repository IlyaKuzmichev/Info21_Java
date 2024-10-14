-- Создание таблицы Peers
CREATE TABLE IF NOT EXISTS Peers(
Nickname text CHECK (LENGTH(Nickname) <= 255) PRIMARY KEY NOT NULL,
Birthday date NOT NULL,
UNIQUE (Nickname)
);

-- Создание таблицы Task
CREATE TABLE IF NOT EXISTS Task(
Title text CHECK (LENGTH(Title) <= 255) PRIMARY KEY NOT NULL,
ParrentTask text CHECK (LENGTH(ParrentTask) <= 255),
MaxXP bigint NOT NULL
);

-- Создание таблицы Checks
CREATE TABLE IF NOT EXISTS Checks(
ID bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
PeerID text REFERENCES Peers(Nickname) NOT NULL,
Task text REFERENCES Task(Title) NOT NULL,
Date date NOT NULL
);

-- Создание перечисления "Статус P2P проверки", если его еще не существует
CREATE TYPE CheckStatus AS ENUM ('Start', 'Success', 'Failure');

-- Создание таблицы P2P
CREATE TABLE IF NOT EXISTS P2P(
ID bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
CheckID bigint REFERENCES Checks(ID) NOT NULL,
CheckingPeer text REFERENCES Peers(Nickname) NOT NULL,
State CheckStatus NOT NULL,
time TIMETZ DEFAULT CURRENT_TIME NOT NULL
);

-- Создание таблицы Verter
CREATE TABLE IF NOT EXISTS Verter(
ID bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
CheckID bigint REFERENCES Checks(ID) NOT NULL,
State CheckStatus NOT NULL,
time TIMETZ DEFAULT CURRENT_TIME NOT NULL
);

-- Создание таблицы TransferredPoints
CREATE TABLE IF NOT EXISTS TransferredPoints(
ID bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
CheckingPeer text REFERENCES Peers(Nickname) NOT NULL,
CheckedPeer text REFERENCES Peers(Nickname) NOT NULL,
PointsAmount bigint NOT NULL CHECK (PointsAmount >= 0),
UNIQUE (CheckingPeer, CheckedPeer)
);

-- Создание таблицы Friends
CREATE TABLE IF NOT EXISTS Friends(
ID bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Peer1 text REFERENCES Peers(Nickname) NOT NULL,
Peer2 text REFERENCES Peers(Nickname) NOT NULL,
CHECK (Peer1 != Peer2),
UNIQUE (Peer1, Peer2),
UNIQUE (Peer2, Peer1)
);

-- Создание таблицы Recommendations
CREATE TABLE IF NOT EXISTS Recommendations(
ID bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Peer text REFERENCES Peers(Nickname) NOT NULL,
RecommendedPeer text REFERENCES Peers(Nickname) NOT NULL,
CHECK (Peer != RecommendedPeer),
UNIQUE (Peer, RecommendedPeer)
);

-- Создание таблицы XP
CREATE TABLE IF NOT EXISTS XP(
ID bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
CheckID bigint REFERENCES Checks(ID) NOT NULL,
XPAmount bigint NOT NULL,
FOREIGN KEY (CheckID) REFERENCES Checks(ID) MATCH SIMPLE ON DELETE CASCADE ON UPDATE CASCADE
);

-- Создание таблицы TimeTracking
CREATE TABLE IF NOT EXISTS TimeTracking(
ID bigint GENERATED ALWAYS AS IDENTITY PRIMARY KEY,
Peer text REFERENCES Peers(Nickname) NOT NULL,
Date date NOT NULL,
Time time NOT NULL,
State bigint CHECK (State IN (1, 2)) NOT NULL
);

