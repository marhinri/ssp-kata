CREATE TABLE bot(
    id UUID not null,
    name VARCHAR(128) not null,
    credit INTEGER not null,
    won INTEGER not null,
    lost INTEGER not null,
    clientURL VARCHAR(2048) not null,
    PRIMARY KEY(id)
)