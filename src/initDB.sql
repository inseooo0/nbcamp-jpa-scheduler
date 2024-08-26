-- user
CREATE TABLE `user` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `create_at` DATETIME(6) NULL DEFAULT NULL,
    `email` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `name` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `password` VARCHAR(255) NOT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `update_at` DATETIME(6) NULL DEFAULT NULL,
    `user_role` ENUM('ADMIN','USER') NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    PRIMARY KEY (`id`) USING BTREE,
    UNIQUE INDEX `UKob8kqyqqgmefl0aco34akdtpe` (`email`) USING BTREE,
    UNIQUE INDEX `UKgj2fy3dcix7ph7k8684gka40c` (`name`) USING BTREE
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=6
;

-- schedule
CREATE TABLE `schedule` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `create_at` DATETIME(6) NULL DEFAULT NULL,
    `title` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `update_at` DATETIME(6) NULL DEFAULT NULL,
    `weather` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    PRIMARY KEY (`id`) USING BTREE
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=5
;

-- comment
CREATE TABLE `comment` (
    `id` BIGINT NOT NULL AUTO_INCREMENT,
    `content` VARCHAR(255) NULL DEFAULT NULL COLLATE 'utf8mb4_0900_ai_ci',
    `create_at` DATETIME(6) NULL DEFAULT NULL,
    `update_at` DATETIME(6) NULL DEFAULT NULL,
    `schedule_id` BIGINT NULL DEFAULT NULL,
    `user_id` BIGINT NULL DEFAULT NULL,
    PRIMARY KEY (`id`) USING BTREE,
    INDEX `FKsy51iks4dgapu66gfj3mnykch` (`schedule_id`) USING BTREE,
    INDEX `FK8kcum44fvpupyw6f5baccx25c` (`user_id`) USING BTREE,
    CONSTRAINT `FK8kcum44fvpupyw6f5baccx25c` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
    CONSTRAINT `FKsy51iks4dgapu66gfj3mnykch` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
;

-- schedule_management
CREATE TABLE `schedule_management` (
   `id` BIGINT NOT NULL AUTO_INCREMENT,
   `schedule_id` BIGINT NULL DEFAULT NULL,
   `user_id` BIGINT NULL DEFAULT NULL,
   PRIMARY KEY (`id`) USING BTREE,
   INDEX `FK532ge9p89oc5mp401ak64os0y` (`schedule_id`) USING BTREE,
   INDEX `FKgy8h3dospq7d5cv9t2habkdu9` (`user_id`) USING BTREE,
   CONSTRAINT `FK532ge9p89oc5mp401ak64os0y` FOREIGN KEY (`schedule_id`) REFERENCES `schedule` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION,
   CONSTRAINT `FKgy8h3dospq7d5cv9t2habkdu9` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`) ON UPDATE NO ACTION ON DELETE NO ACTION
)
    COLLATE='utf8mb4_0900_ai_ci'
ENGINE=InnoDB
AUTO_INCREMENT=8
;


