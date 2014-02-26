USE c_cs108_martinob;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS qandas;
 -- remove table if it already exists and start from scratch

CREATE TABLE qandas (
    quizName CHAR(64),
    question CHAR(64),
    answer CHAR(64),
    type INT,
    options CHAR(64)
);
