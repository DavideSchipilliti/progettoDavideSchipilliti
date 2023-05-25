
CREATE TABLE schedule (
	job serial PRIMARY KEY,
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
