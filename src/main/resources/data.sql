CREATE TABLE `stableford_score`
(
    `id` integer NOT NULL,
    `code` VARCHAR(255) NOT NULL ,
    `length` VARCHAR(11) NULL DEFAULT NULL,
    `par` VARCHAR(2),
    `index` VARCHAR(2),
    `stroke` VARCHAR(2),
    `stablefordScore` VARCHAR(2),
    `hole_analysis_id` integer,
    PRIMARY KEY (`id`)
);

CREATE TABLE `hole_analysis`
(
    `id` integer NOT NULL,
    `teeOffLength` VARCHAR(4),
    `teeOffDirection` VARCHAR(10),
    `putt` VARCHAR(2),
    PRIMARY KEY (`id`)
);