CREATE TABLE schedule (
	id serial PRIMARY KEY,
	creation TIMESTAMP NOT NULL,
	type_of_request varchar(15) NOT NULL,
	date1 TIMESTAMP,
	date2 TIMESTAMP,
	timespan varchar(15),
	multiplier integer,
	forex_ticker char(8),
	cron_expression varchar(25),
	state varchar(10)
);

ALTER TABLE schedule ADD CONSTRAINT Ck_type_of_request
	CHECK (type_of_request IN ('Aggregates', 'GroupedDaily', 'PreviousClose'));

ALTER TABLE schedule ADD CONSTRAINT Ck_timespan
	CHECK (timespan IN ('minute', 'hour', 'day', 'week', 'month', 'quarter', 'year'));
	
ALTER TABLE schedule ADD CONSTRAINT Ck_state
	CHECK (state IN ('pending', 'succes', 'error', 'stopped'));

CREATE TABLE run (
	id serial PRIMARY KEY,
	started TIMESTAMP NOT NULL,
	finished TIMESTAMP NOT NULL,
	status varchar NOT NULL,
	schedule_id integer NOT NULL REFERENCES schedule(id)
);

select * from schedule;
select * from run;

SELECT R.id AS job, S.id AS schedule, S.creation, R.started, R.status, S.type_of_Request, S.cron_expression
from run AS R Join schedule AS S ON R.schedule_id=S.id
	   
INSERT INTO schedule (creation, type_of_request, date1, date2, timespan, multiplier, forex_ticker, cron_expression, state)
VALUES ('2023-06-20 18:24:32', 'Aggregates', '2023-06-01 18:30:00', '2023-06-01 19:30:00', 'minute', 10, 'C:EURUSD', '0 0/5 * ? * *', 'pending');
INSERT INTO schedule (creation, type_of_request, date1, cron_expression, state)
VALUES ('2023-06-20 18:24:44', 'GroupedDaily', '2023-06-01', '0 0/7 * ? * *', 'pending');
INSERT INTO schedule (creation, type_of_request, forex_ticker, cron_expression, state)
VALUES ('2023-06-20 18:24:55', 'PreviousClose', 'C:EURUSD', '0 0/2 * ? * *', 'pending');



delete from run;
delete from schedule;
