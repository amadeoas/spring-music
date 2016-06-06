INSERT INTO instruments VALUES (1, 'Default');
INSERT INTO instruments VALUES (2, 'Kick Drum');
INSERT INTO instruments VALUES (3, 'Rock Male Vocal');

INSERT INTO vocal_compressors VALUES (1, 'Vocal Compressor', 'creative', 20, 10, -10.0, 7, 1, -7.0, 8.0);

INSERT INTO eq_settings VALUES (1, 1, 0, 'LOW',     'PEAK', 0, 0.0, 'Hz');
INSERT INTO eq_settings VALUES (2, 1, 0, 'LOW_MID', 'HI',   0, 0.0, 'Hz');
INSERT INTO eq_settings VALUES (3, 1, 0, 'HI_MID',  'NONE', 0, 0.0, 'Hz');
INSERT INTO eq_settings VALUES (4, 1, 0, 'HI',      'PEAK', 0, 0.0, 'Hz');

INSERT INTO eq_settings VALUES (5, 2, 1, 'LOW',     'PEAK', 4, 108.0, 'Hz');
INSERT INTO eq_settings VALUES (6, 2, 1, 'LOW_MID', 'HI',  -4, 290.0, 'Hz');
INSERT INTO eq_settings VALUES (7, 2, 1, 'HI_MID',  'NONE', 2,   2.4, 'KHz');
INSERT INTO eq_settings VALUES (8, 2, 1, 'HI',      'PEAK', 4,   6.0, 'KHz');

INSERT INTO eq_settings VALUES (9,  3, 1, 'LOW',     'PEAK',  2, 155.0, 'Hz');
INSERT INTO eq_settings VALUES (10, 3, 1, 'LOW_MID', 'HI',   -6, 290.0, 'Hz');
INSERT INTO eq_settings VALUES (11, 3, 1, 'HI_MID',  'NONE', -2,   2.4, 'KHz');
INSERT INTO eq_settings VALUES (12, 3, 1, 'HI',      'SHELF', 4,   7.2, 'KHz');
