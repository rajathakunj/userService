CREATE TABLE `user` (
	`USER_ID` VARCHAR(45) NOT NULL COMMENT 'User identifier' COLLATE 'utf8mb3_bin',
	`FIRST_NAME` VARCHAR(80) NOT NULL COMMENT 'First name.' COLLATE 'utf8mb3_bin',
	`LAST_NAME` VARCHAR(80) NOT NULL COMMENT 'Last name.' COLLATE 'utf8mb3_bin',
	`PERSONAL_NUMBER` VARCHAR(45) NOT NULL COMMENT 'Personal identifier' COLLATE 'utf8mb3_bin',
	`EMAIL_ADDRESS` VARCHAR(200) NOT NULL COMMENT 'User email address.' COLLATE 'utf8mb3_bin',
	`PHONE_NUMBER` VARCHAR(80) NULL DEFAULT NULL COLLATE 'utf8mb3_general_ci',
	`GENDER` ENUM('M','F','OTHER') NOT NULL COLLATE 'utf8mb3_general_ci',
	`BIRTH_DATE` DATE NOT NULL COMMENT 'User\'s Date of Birth or Birthday',
	`CREATED_DT` DATETIME NULL DEFAULT CURRENT_TIMESTAMP COMMENT 'Date/time the account is created.',
	PRIMARY KEY (`USER_ID`) USING BTREE,
	UNIQUE INDEX `PERSONAL_NUMBER` (`PERSONAL_NUMBER`) USING BTREE
)
COMMENT='Table to hold the member registration process information'
COLLATE='utf8mb3_general_ci'
ENGINE=InnoDB
;