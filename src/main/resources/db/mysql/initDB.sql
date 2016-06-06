CREATE DATABASE IF NOT EXISTS music;

ALTER DATABASE music
  DEFAULT CHARACTER SET utf8
  DEFAULT COLLATE utf8_general_ci;

GRANT ALL PRIVILEGES ON music.* TO cp@localhost IDENTIFIED BY 'music';

USE music;

CREATE TABLE IF NOT EXISTS instruments (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  name VARCHAR(30) NOT NULL UNIQUE,
  INDEX(name)
) engine=InnoDB;

CREATE TABLE IF NOT EXISTS eq_settings (
  id INT(4) UNSIGNED NOT NULL AUTO_INCREMENT PRIMARY KEY,
  instrument_id INT(4) UNSIGNED NOT NULL,
  active	BOOL,
  band_type VARCHAR(10),
  band_set	VARCHAR(10),
  gain	INT(4),
  freq	DECIMAL,
  freq_units VARCHAR(10),
  FOREIGN KEY (instrument_id) REFERENCES instruments(id)
) engine=InnoDB;
