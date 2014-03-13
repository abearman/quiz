USE c_cs108_pavitrar;
  
DROP TABLE IF EXISTS quizzes;
 -- remove table if it already exists and start from scratch

CREATE TABLE quizzes (
    quizName CHAR(255),
    description CHAR(255),
    isRandom BOOLEAN,
    isMultiplePage BOOLEAN,
    isImmediateCorrection BOOLEAN,
    canBeTakenInPracticeMode BOOLEAN,
    creatorName CHAR(255),
    creationDate DATETIME, 
    numTimesTaken INT
);


DROP TABLE IF EXISTS friends;
 -- remove table if it already exists and start from scratch

CREATE TABLE friends (
    user1 CHAR(255),
    user2 CHAR(255)
);


DROP TABLE IF EXISTS histories;
 -- remove table if it already exists and start from scratch

CREATE TABLE histories (
    loginName CHAR(255),
    quizName CHAR(255),
    numQuestionsCorrect INT,
    timeElapsed BIGINT,
    dateValue DATETIME
);


DROP TABLE IF EXISTS messages;
 -- remove table if it already exists and start from scratch

CREATE TABLE messages (
    fromUser CHAR(255),
    toUser CHAR(255),
    messageType CHAR(255),
    message CHAR(255),
    quizName CHAR(255),
    bestScore DOUBLE,
    sendDate DATETIME
);

DROP TABLE IF EXISTS questionResponse;
 -- remove table if it already exists and start from scratch

CREATE TABLE questionResponse (
    quizName CHAR(255),
    question CHAR(255),
    answer CHAR(255),
    qtype INT,
    qNum INT
    
);

DROP TABLE IF EXISTS fillInTheBlank;
 -- remove table if it already exists and start from scratch

CREATE TABLE fillInTheBlank (
    quizName CHAR(255),
    question CHAR(255),
    answer CHAR(255),
    qtype INT,
    qNum INT
);

DROP TABLE IF EXISTS multipleChoice;
 -- remove table if it already exists and start from scratch

CREATE TABLE multipleChoice (
    quizName CHAR(255),
    question CHAR(255),
    answer CHAR(255),
    qtype INT,
    qNum INT,
    options CHAR(255)
);

DROP TABLE IF EXISTS multiAnswerMultipleChoice;
 -- remove table if it already exists and start from scratch

CREATE TABLE multiAnswerMultipleChoice (
    quizName CHAR(255),
    question CHAR(255),
    answer CHAR(255),
    qtype INT,
    qNum INT,
    options CHAR(255)
);

DROP TABLE IF EXISTS pictureResponse;
 -- remove table if it already exists and start from scratch

CREATE TABLE pictureResponse (
    quizName CHAR(255),
    question CHAR(255),
    answer CHAR(255),
    qtype INT,
    qNum INT,
    imageURL CHAR(255)
);


DROP TABLE IF EXISTS topscorers;
 -- remove table if it already exists and start from scratch

CREATE TABLE topscorers (
	quizName CHAR(255),
    loginName CHAR(255),
    numCorrectQuestions INT,
    timeTaken DOUBLE
);


DROP TABLE IF EXISTS users;
 -- remove table if it already exists and start from scratch

CREATE TABLE users (
    loginName CHAR(255),
    isAdministrator BOOLEAN,
    password CHAR(255),
    achievements CHAR(255),
    recentActivity CHAR(255),
    hasNewMessage BOOLEAN
);


DROP TABLE IF EXISTS announcements;
 -- remove table if it already exists and start from scratch

CREATE TABLE announcements (
    announcement CHAR(255)
);

DROP TABLE IF EXISTS achievements;
 -- remove table if it already exists and start from scratch

CREATE TABLE achievements (
	loginName CHAR(255),
	achievement CHAR(255),
	achievementDate DATETIME
);

DROP TABLE IF EXISTS statuses;
 -- remove table if it already exists and start from scratch

CREATE TABLE statuses (
	loginName CHAR(255),
	status CHAR(255),
	statusDate DATETIME
);





