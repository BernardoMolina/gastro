ALTER TABLE usuarios
ALTER COLUMN senha TYPE VARCHAR(255);

UPDATE usuarios SET senha = '$2b$10$isrAO.mvNlSPRMGV1Xvl/ulWfELs1fsoK2WLePKs8WL7aIiym9qN6', permissao = 'MEDICO'   WHERE email = 'usuario1@example.com';
UPDATE usuarios SET senha = '$2b$10$8Es7uIeorSkHrTxtF6e5/u.q.lA6bwJwRzvDeuI/YQsIyKqkvO31a', permissao = 'MEDICO'   WHERE email = 'usuario2@example.com';
UPDATE usuarios SET senha = '$2b$10$q9oXhTCJeowPwyQwiDlVCeHUEUzYCg5GLo75mxL91HMRGkxFz4QPy', permissao = 'PACIENTE' WHERE email = 'usuario3@example.com';
UPDATE usuarios SET senha = '$2b$10$iWA6TULSXa9QPRSfGrL4beKkzmNtl5QZb6Sl89lao1F3v7v9r/.Vq', permissao = 'PACIENTE' WHERE email = 'usuario4@example.com';