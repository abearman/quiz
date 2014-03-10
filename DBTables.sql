USE c_cs108_pavitrar;
  
DROP TABLE IF EXISTS quizzes;
 -- remove table if it already exists and start from scratch

CREATE TABLE quizzes (
    quizName CHAR(64),
    description CHAR(64),
    isRandom BOOLEAN,
    isMultiplePage BOOLEAN,
    isImmediateCorrection BOOLEAN,
    canBeTakenInPracticeMode BOOLEAN,
    creatorName CHAR(64),
    creationDate DATETIME, 
    numTimesTaken INT
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
    numQuestionsCorrect INT,
    timeElapsed BIGINT,
    dateValue DATETIME
);


DROP TABLE IF EXISTS messages;
 -- remove table if it already exists and start from scratch

CREATE TABLE messages (
    fromUser CHAR(64),
    toUser CHAR(64),
    messageType CHAR(64),
    message CHAR(64),
    quizName CHAR(64),
    bestScore DOUBLE,
    sendDate DATETIME
);

DROP TABLE IF EXISTS questionResponse;
 -- remove table if it already exists and start from scratch

CREATE TABLE questionResponse (
    quizName CHAR(64),
    question CHAR(64),
    answer CHAR(64),
    qtype INT,
    qNum INT
    
);

DROP TABLE IF EXISTS fillInTheBlank;
 -- remove table if it already exists and start from scratch

CREATE TABLE fillInTheBlank (
    quizName CHAR(64),
    question CHAR(64),
    answer CHAR(64),
    qtype INT,
    qNum INT
);

DROP TABLE IF EXISTS multipleChoice;
 -- remove table if it already exists and start from scratch

CREATE TABLE multipleChoice (
    quizName CHAR(64),
    question CHAR(64),
    answer CHAR(64),
    qtype INT,
    qNum INT,
    options CHAR(64)
);

DROP TABLE IF EXISTS multiAnswerMultipleChoice;
 -- remove table if it already exists and start from scratch

CREATE TABLE multiAnswerMultipleChoice (
    quizName CHAR(64),
    question CHAR(64),
    answer CHAR(64),
    qtype INT,
    qNum INT,
    options CHAR(64)
);

DROP TABLE IF EXISTS pictureResponse;
 -- remove table if it already exists and start from scratch

CREATE TABLE pictureResponse (
    quizName CHAR(64),
    question CHAR(64),
    answer CHAR(64),
    qtype INT,
    qNum INT,
    imageURL CHAR(255)
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
    achievements CHAR(64),
    recentActivity CHAR(64),
    hasNewMessage BOOLEAN
);


DROP TABLE IF EXISTS announcements;
 -- remove table if it already exists and start from scratch

CREATE TABLE announcements (
    announcement CHAR(64)
);

DROP TABLE IF EXISTS achievements;
 -- remove table if it already exists and start from scratch

CREATE TABLE achievements (
	loginName CHAR(64),
	achievement CHAR(64),
	achievementDate DATETIME
);

DROP TABLE IF EXISTS statuses;
 -- remove table if it already exists and start from scratch

CREATE TABLE statuses (
	loginName CHAR(64),
	status CHAR(64),
	statusDate DATETIME
);





