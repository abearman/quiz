USE c_cs108_martinob;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS quizzes;
 -- remove table if it already exists and start from scratch

CREATE TABLE quizzes (
    quizName CHAR(64),
    quizLink CHAR(64),
    description CHAR(64),
    isRandom BOOLEAN,
    isMultiplePage BOOLEAN,
    isImmediateCorrection BOOLEAN,
    canBeTakenInPracticeMode BOOLEAN
);
