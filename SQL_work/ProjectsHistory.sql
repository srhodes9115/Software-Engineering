USE Employee_Information;

DROP TABLE IF EXISTS ProjectsHistory;

CREATE TABLE ProjectsHistory
(projectId int unsigned auto_increment Primary Key NOT NULL, #unique id
projectName varchar(255) NOT NULL,
projectManagerCdid varchar(255) not null
);


INSERT INTO ProjectsHistory (projectName, projectManagerCdid)
VALUES ("Acquire Google","agarg76"),
("World Domination","agarg76"),
("Fix Computers","erebao"),
("Program Ad Market","tchesley13");

select*from ProjectsHistory;


