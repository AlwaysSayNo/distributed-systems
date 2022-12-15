use `study-groups`;

# Table of students
DROP TABLE IF EXISTS `student`;
DROP TABLE IF EXISTS `group`;

# Table of groups
CREATE TABLE `group` (
                         `id` INT AUTO_INCREMENT UNIQUE NOT NULL,
                         `name` VARCHAR(255) NOT NULL,

                         PRIMARY KEY (`id`)
);

CREATE TABLE `student` (
                           `id` INT AUTO_INCREMENT UNIQUE NOT NULL,
                           `group_id` INT NOT NULL,
                           `first_name` VARCHAR(255) NOT NULL,
                           `last_name` VARCHAR(255) NOT NULL,

                           PRIMARY KEY (`id`),
                           CONSTRAINT `fk_group_id`
                               FOREIGN KEY (`group_id`)
                                   REFERENCES `group`(`id`)
                                     ON DELETE CASCADE
);