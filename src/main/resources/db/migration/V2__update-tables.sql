ALTER TABLE pacientes
    ADD CONSTRAINT unique_iduser_pacientes UNIQUE (iduser);

ALTER TABLE medicos
    ADD CONSTRAINT unique_iduser_medicos UNIQUE (iduser);


CREATE FUNCTION check_user_role() RETURNS TRIGGER AS $$
BEGIN
  -- Verifica se o iduser já está presente na tabela medicos
  IF EXISTS (SELECT 1 FROM medicos WHERE iduser = NEW.iduser) THEN
    RAISE EXCEPTION 'Um usuário não pode ser médico e paciente ao mesmo tempo.';
END IF;

  -- Verifica se o iduser já está presente na tabela pacientes
  IF EXISTS (SELECT 1 FROM pacientes WHERE iduser = NEW.iduser) THEN
    RAISE EXCEPTION 'Um usuário não pode ser médico e paciente ao mesmo tempo.';
END IF;

RETURN NEW;
END;
$$ LANGUAGE plpgsql;

-- Gatilho para a tabela pacientes
CREATE TRIGGER check_user_before_insert_pacientes
    BEFORE INSERT ON pacientes
    FOR EACH ROW EXECUTE FUNCTION check_user_role();

-- Gatilho para a tabela medicos
CREATE TRIGGER check_user_before_insert_medicos
    BEFORE INSERT ON medicos
    FOR EACH ROW EXECUTE FUNCTION check_user_role();