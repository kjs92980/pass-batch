FROM mysql:8.0.30
COPY ./db/conf.d /etc/mysql/conf.d
COPY ./db/initdb.d /docker-entrypoint-initdb.d

RUN chmod 644 /etc/mysql/conf.d/my.cnf