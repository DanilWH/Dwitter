CREATE TABLE quote_likes (
    user_id bigint not null references usr,
    quote_id bigint not null references quote,
    primary key (user_id, quote_id)
);