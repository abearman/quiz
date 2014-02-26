USE c_cs108_martinob;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS histories;
 -- remove table if it already exists and start from scratch

CREATE TABLE histories (
    loginName CHAR(64),
    quizName CHAR(64),
    score DOUBLE,
    timeElapsed BIGINT,
    date DATE
);
