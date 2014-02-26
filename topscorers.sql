USE c_cs108_martinob;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS topscorers;
 -- remove table if it already exists and start from scratch

CREATE TABLE topscorers (
	quizName CHAR(64),
    loginName CHAR(64),
    numCorrectQuestions INT,
    timeTaken DOUBLE
);
