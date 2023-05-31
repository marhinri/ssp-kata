CREATE TABLE bot(
    id UUID not null,
    name VARCHAR(128) not null,
    credit INTEGER not null,
    won INTEGER,
    lost INTEGER,
    draw INTEGER,
    PRIMARY KEY(id)
)