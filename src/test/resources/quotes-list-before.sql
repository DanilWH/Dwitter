DELETE FROM quote;

INSERT INTO quote (id, tag, text, user_id) VALUES
    (1, 'new', 'This is a new quote.', 1),
    (2, 'mikesQuotes', 'This quote was written by Mike.', 2),
    (3, 'new', 'This is one more new quote from admin.', 1),
    (4, 'something', 'Something from admin.', 1);

ALTER SEQUENCE hibernate_sequence RESTART WITH 10;
