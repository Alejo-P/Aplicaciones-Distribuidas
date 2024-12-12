-- Verificar el estado del maestro
SHOW MASTER STATUS;

-- Cambiar el master host, user, password, log file y log pos
CHANGE MASTER TO
    MASTER_HOST='',
    MASTER_USER='',
    MASTER_PASSWORD='',
    MASTER_LOG_FILE='',
    MASTER_LOG_POS=0;

-- Iniciar la replicación
START SLAVE;

-- Verificar el estado de la replicación
SHOW SLAVE STATUS;