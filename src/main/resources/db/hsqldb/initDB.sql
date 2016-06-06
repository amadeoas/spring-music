DROP TABLE eq_settings IF EXISTS;
DROP TABLE instruments IF EXISTS;
DROP TABLE vocal_compressors IF EXISTS;


CREATE TABLE instruments (
  id    INTEGER IDENTITY PRIMARY KEY,
  name 	VARCHAR(30) NOT NULL UNIQUE
);
CREATE INDEX instruments_name ON instruments (name);

CREATE TABLE eq_settings (
  id    		INTEGER IDENTITY PRIMARY KEY,
  instrument_id	INTEGER NOT NULL,
  active		BIT,
  band_type		VARCHAR(10),
  band_set		VARCHAR(10),
  gain			INTEGER,
  freq			DECIMAL,
  freq_units	VARCHAR(10)
);
ALTER TABLE eq_settings ADD CONSTRAINT fk_eq_settings_instruments FOREIGN KEY (instrument_id) REFERENCES instruments (id) ON DELETE CASCADE;

CREATE TABLE vocal_compressors (
  id    		INTEGER IDENTITY PRIMARY KEY,
  name 			VARCHAR(30) NOT NULL UNIQUE,
  mode 			VARCHAR(20),
  attack 		INTEGER,
  release		INTEGER,
  threshold		DECIMAL,
  ratio			INTEGER,
  ratio_of		INTEGER,
  presence		DECIMAL,
  make_up		DECIMAL
);
CREATE INDEX vocal_compressors_name ON vocal_compressors (name);
