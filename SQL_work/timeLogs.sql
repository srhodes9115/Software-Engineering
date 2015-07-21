USE Employee_Information;

DROP TABLE IF EXISTS timeLog;

CREATE TABLE timeLog
(cdid varchar(255) not null,
projectId int unsigned not null,
workedTime int not null,
workedThisDate date not null,
dateEntered date,
typeOfWork varchar(255) not null,
foreign key (projectId) references ProjectsHistory (projectId))
engine = InnoDB;


INSERT INTO timeLog (cdid,projectId,workedTime,workedThisDate,dateEntered,typeOfWork)
VALUES ("smukhia",1,"7","2013-03-14","2000-06-25","Requirements"), 
("smukhia",1,"4","2013-03-15","1998-07-30","Development"),
("alextimb",2,"10","2014-05-19","2007-08-08","Testing"),
("vasantraj1",3,"6","2013-09-12","2013-04-17","Testing"); 

INSERT INTO timeLog (cdid,projectId,workedTime,workedThisDate,dateEntered,typeOfWork)
VALUES ("bykowskipatch",2,"3","2014-01-20","2012-11-07","Development"); 

select*from timeLog;
