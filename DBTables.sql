USE c_cs108_pavitrar;
  
DROP TABLE IF EXISTS quizzes;
 -- remove table if it already exists and start from scratch

CREATE TABLE quizzes (
    quizName CHAR(64),
    description CHAR(64),
    isRandom BOOLEAN,
    isMultiplePage BOOLEAN,
    isImmediateCorrection BOOLEAN,
    canBeTakenInPracticeMode BOOLEAN
);


DROP TABLE IF EXISTS friends;
 -- remove table if it already exists and start from scratch

CREATE TABLE friends (
    user1 CHAR(64),
    user2 CHAR(64)
);


DROP TABLE IF EXISTS histories;
 -- remove table if it already exists and start from scratch

CREATE TABLE histories (
    loginName CHAR(64),
    quizName CHAR(64),
    score DOUBLE,
    timeElapsed BIGINT,
    date DATE
);


DROP TABLE IF EXISTS messages;
 -- remove table if it already exists and start from scratch

CREATE TABLE messages (
    fromUser CHAR(64),
    toUser CHAR(64),
    type CHAR(64),
    message CHAR(64),
    quizName CHAR(64),
    quizLink CHAR(64),
    bestScore DOUBLE
);


DROP TABLE IF EXISTS qandas;
 -- remove table if it already exists and start from scratch

CREATE TABLE qandas (
    quizName CHAR(64),
    question CHAR(64),
    answer CHAR(64),
    type INT,
    options CHAR(64)
);


DROP TABLE IF EXISTS topscorers;
 -- remove table if it already exists and start from scratch

CREATE TABLE topscorers (
	quizName CHAR(64),
    loginName CHAR(64),
    numCorrectQuestions INT,
    timeTaken DOUBLE
);


DROP TABLE IF EXISTS users;
 -- remove table if it already exists and start from scratch

CREATE TABLE users (
    loginName CHAR(64),
    isAdministrator BOOLEAN,
    password CHAR(64),
    achievements CHAR(64)
);
