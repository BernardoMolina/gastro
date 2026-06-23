ALTER TABLE consultas
ALTER COLUMN dataa TYPE TIMESTAMP
USING dataa::timestamp;