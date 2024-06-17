ALTER TABLE manometria
    ADD CONSTRAINT unique_idexame_manometria UNIQUE (idexame);

ALTER TABLE endoscopia
    ADD CONSTRAINT unique_idexame_endoscopia UNIQUE (idexame);

ALTER TABLE colangioressonancia
    ADD CONSTRAINT unique_idexame_colangioressonancia UNIQUE (idexame);

CREATE FUNCTION check_exame() RETURNS TRIGGER AS $$
BEGIN

	  IF EXISTS (SELECT 1 FROM manometria WHERE idexame = NEW.idexame) THEN
	    RAISE EXCEPTION 'Um exame so faz referencia um procedimento.';
END IF;

	  IF EXISTS (SELECT 1 FROM endoscopia WHERE idexame = NEW.idexame) THEN
	    RAISE EXCEPTION 'Um exame so faz referencia um procedimento.';
END IF;

	IF EXISTS (SELECT 1 FROM colangioressonancia WHERE idexame = NEW.idexame) THEN
	    RAISE EXCEPTION 'Um exame so faz referencia um procedimento.';
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;


CREATE TRIGGER check_exame_before_insert_manometria
    BEFORE INSERT ON manometria
    FOR EACH ROW EXECUTE FUNCTION check_exame();


CREATE TRIGGER check_exame_before_insert_colangioressonancia
    BEFORE INSERT ON colangioressonancia
    FOR EACH ROW EXECUTE FUNCTION check_exame();

CREATE TRIGGER check_exame_before_insert_endoscopia
    BEFORE INSERT ON endoscopia
    FOR EACH ROW EXECUTE FUNCTION check_exame();
