-- Verificar el estado del maestro
SHOW MASTER STATUS;

-- Cambiar el master host, user, password, log file y log pos
CHANGE MASTER TO
    MASTER_HOST='',
    MASTER_USER='',
    MASTER_PASSWORD='',
    MASTER_LOG_FILE='',
    MASTER_LOG_POS=0;

-- Ejemplo
CHANGE MASTER TO
    MASTER_HOST='mysql-master',
    MASTER_USER='root',
    MASTER_PASSWORD='root',
    MASTER_LOG_FILE='mysql-bin.000003',
    MASTER_LOG_POS=530;

-- Iniciar la replicación
START SLAVE;

-- Verificar el estado de la replicación
SHOW SLAVE STATUS;