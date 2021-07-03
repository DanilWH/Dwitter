DELETE FROM user_role;
DELETE FROM usr;

INSERT INTO usr (id, active, password, username) VALUES
    (1, true, '$2a$12$sUXicRerWBtuP2yhVfkbZu5jf.6rhAyNFow0kqgkwQWUf37tvwYUa', 'admin'),
    (2, true, '$2y$12$1rB9pMCU0q5yVSCEaahIO.4QxmN6l5oH8FhH61YfsAYy92rMsN8ja', 'mike');

INSERT INTO user_role(user_id, roles) VALUES
    (1, 'ADMIN'), (1, 'USER'),
    (2, 'USER');
