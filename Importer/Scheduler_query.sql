CREATE TABLE schedule (
	job serial PRIMARY KEY,
	creation TIMESTAMP NOT NULL,
	type_of_request varchar(15),
	start TIMESTAMP NOT NULL,
	stop TIMESTAMP,
	timespan varchar(15),
	multiplier integer,
	forex_ticker char(8),
	state varchar(10)
);

ALTER TABLE schedule ADD CONSTRAINT Ck_type_of_request
	CHECK (type_of_request IN ('Aggregates', 'GroupedDaily', 'PreviousClose'));

ALTER TABLE schedule ADD CONSTRAINT Ck_timespan
	CHECK (timespan IN ('minute', 'hour', 'day', 'week', 'month', 'quarter', 'year'));
	
ALTER TABLE schedule ADD CONSTRAINT Ck_state
	CHECK (state IN ('pending', 'succes', 'error', 'stopped'));

CREATE TABLE run (
	id varchar(50) PRIMARY KEY,
	started TIMESTAMP NOT NULL,
	finished TIMESTAMP NOT NULL,
	status varchar NOT NULL,
	job_id integer NOT NULL REFERENCES schedule(job)
);

select * from schedule;
select * from run;

INSERT INTO run (id, started, finished, status, job_id)
VALUES ('run01', '2023-06-01 17:30:10', '2023-06-01 17:30:15', 'ok', 1),
       ('run02', '2023-06-01 17:31:10', '2023-06-01 17:31:15', 'ok', 1),
       ('run03', '2023-06-01 17:32:10', '2023-06-01 17:32:15', 'ok', 1),
       ('run04', '2023-06-01 17:33:10', '2023-06-01 17:33:15', 'ok', 1),
       ('run05', '2023-06-01 17:34:10', '2023-06-01 17:34:15', 'ok', 1);
	   
INSERT INTO schedule (creation, type_of_request, start, stop, timespan, multiplier, forex_ticker, state)
VALUES ('2023-06-15 09:23:14', 'Aggregates', '2023-06-01 18:30:00', '2023-06-01 19:30:00', 'minute', 10, 'C:EURUSD', 'pending');
INSERT INTO schedule (creation, type_of_request, start, stop, timespan, multiplier, state)
VALUES ('2023-06-15 09:23:14', 'GroupedDaily', '2023-06-01', '2023-07-01', 'day', 2, 'pending');
INSERT INTO schedule (creation, type_of_request, start, stop, timespan, multiplier, forex_ticker, state)
VALUES ('2023-06-15 09:23:14', 'PreviousClose', '2023-06-01', '2023-07-01', 'day', 1, 'C:EURUSD', 'pending');
