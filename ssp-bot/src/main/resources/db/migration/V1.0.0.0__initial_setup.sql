CREATE TABLE bot(
    id UUID not null,
    name VARCHAR(128) not null,
    credit INTEGER not null,
    won INTEGER,
    lost INTEGER,
    draw INTEGER,
    PRIMARY KEY(id)
);

CREATE TABLE round(
    id UUID not null,
    bot_id UUID not null,
    opponent VARCHAR(128) not null,
    result VARCHAR(16),
    stake INTEGER not null,
    PRIMARY KEY(id)
);