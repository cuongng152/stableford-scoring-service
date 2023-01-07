CREATE TABLE `stableford_score`
(
    `id` INTEGER NOT NULL PRIMARY KEY AUTO_INCREMENT,
    `hole_code` VARCHAR(50) NOT NULL ,
    `length` VARCHAR(11) NULL DEFAULT NULL,
    `par` VARCHAR(2),
    `hole_index` VARCHAR(2),
    `stroke` VARCHAR(2),
    `score` VARCHAR(2),
    `hole_analysis_id` INTEGER
);

CREATE TABLE `hole_analysis`
(
    `id` INTEGER NOT NULL PRIMARY KEY,
    `tee_off_length` VARCHAR(4),
    `tee_of_direction` VARCHAR(10),
    `putt` VARCHAR(2)
);


CREATE TABLE `course_score`
(
    `id` INTEGER NOT NULL PRIMARY KEY,
    `stroke` VARCHAR(4),
    `course_name` VARCHAR(10),
    `date_of_play` VARCHAR(255),
    `daily_handicap` VARCHAR(2)
);