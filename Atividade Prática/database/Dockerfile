FROM postgres
ENV POSTGRES_USER postgres
ENV POSTGRES_PASSWORD 123
ENV POSTGRES_DB projeto1

COPY banco.sql /docker-entrypoint-initdb.d/
COPY insert.sql /docker-entrypoint-initdb.d/