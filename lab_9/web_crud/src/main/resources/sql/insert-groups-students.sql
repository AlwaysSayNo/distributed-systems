USE `study-groups`;

INSERT INTO `group` (`id`, `name`)
VALUES
    (1, 'K-18'),
    (2, 'K-19'),
    (3, 'S-12'),
    (4, 'S-13'),
    (5, 'IPS-31');

INSERT INTO `student` (`first_name`, `last_name`, `group_id`)
VALUES
    ('Alex', 'Belos', 1),
    ('Kirill', 'Maloy', 1),
    ('Nick', 'Savolski', 1),
    ('Nadya', 'Marmur', 1),
    ('George', 'Puch', 1),
    ('Alyona', 'Irsis', 1),

    ('Makey', 'Ostroy', 2),
    ('Vlad', 'Nalivashka', 2),
    ('Polina', 'Nedelko', 2),
    ('Anastasiia', 'Savilia', 2),

    ('Nazar', 'Celosenko', 3),
    ('Lilia', 'Melan', 3),
    ('Katerina', 'Okopna', 3),
    ('Polina', 'Tarkovski', 3),
    ('Veronika', 'Kroks', 3),

    ('Semen', 'Wahov', 4),
    ('Alina', 'Mest', 4),
    ('Alex', 'Shinkaryov', 4),

    ('Olha', 'Dzuigal', 5),
    ('Pavel', 'Kilko', 5),
    ('Andrey', 'Semenistiy', 5),
    ('Nazar', 'Grynko', 5);