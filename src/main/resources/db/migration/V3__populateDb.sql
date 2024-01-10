INSERT INTO users(username, password) VALUES
    ('user','$2a$12$vv/2vcbjaCBDLh0MeQYJsuPBiQ2t/Ov3Jr5esJsTITGYv0EYDe342'),
    ('admin','$2a$12$m6gba5M1wehZ7SP.3pIu4Oqiit0b5jSBzGtapjytJGsK54028ZQMu'),
    ('superAdmin','$2a$12$AuCUlzSoSu.WBVv8A.Qy2ebPWlSwtonoYxdAfp/mZNRSTo9TgbKNG');

INSERT INTO roles(name) VALUES
    ('ROLE_USER'),
    ('ROLE_ADMIN'),
    ('ROLE_SUPER_ADMIN');

INSERT INTO users_roles(user_id, role_id) VALUES
    (1,1),
    (2,1),
    (2,2),
    (3,1),
    (3,2),
    (3,3)
--user 11
--admin 22
--superAdmin 33