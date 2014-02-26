USE c_cs108_martinob;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
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
