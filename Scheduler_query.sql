
CREATE TABLE schedule (
	job serial PRIMARY KEY,
	creation integer NOT NULL,
	start integer NOT NULL,
	stop integer,
	timespan varchar(15),
	multiplier integer,
	forex_ticker char(8)
);

CREATE TABLE run (
	id varchar(50) PRIMARY KEY,
	started integer NOT NULL,
	finished integer NOT NULL,
	status varchar NOT NULL,
	job_id integer NOT NULL REFERENCES schedule(job)
);

ALTER TABLE schedule
ADD COLUMN creation integer NOT NULL;

select * from schedule;
select * from run;

insert into schedule (creation, start, forex_ticker)
values (01, 01, 'c:EURUSD')
