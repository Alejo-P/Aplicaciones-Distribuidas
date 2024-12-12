-- Verificar el estado del maestro
SHOW MASTER STATUS;

-- Verificar el estado de la replicación
SHOW SLAVE STATUS;
-- Cambiar el master host, user, password, log file y log pos
CHANGE MASTER TO MASTER_HOST='', MASTER_USER='',MASTER_PASSWORD='',
CHANGE MASTER TO MASTER_LOG_FILE='mysql-bin.000003', MASTER_LOG_POS=477;


-- Iniciar la replicación
START SLAVE;

