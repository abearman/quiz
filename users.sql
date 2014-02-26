USE c_cs108_martinob;
  -- >>>>>>>>>>>> change this line so it uses your database, not mine <<<<<<<<<<<<<<<
  
DROP TABLE IF EXISTS users;
 -- remove table if it already exists and start from scratch

CREATE TABLE users (
    loginName CHAR(64),
    isAdministrator BOOLEAN,
    password CHAR(64),
    achievements CHAR(64)
);
