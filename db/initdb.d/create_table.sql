-- pass_local.pass definition

CREATE TABLE `pass` 
(
    `pass_seq` int NOT NULL AUTO_INCREMENT COMMENT '이용권 순번',
    `gym_id` varchar(20) NOT NULL COMMENT '체육관 ID',
    `pass_id` varchar(20) NOT NULL COMMENT '이용권 ID',
    `user_id` varchar(20) NOT NULL COMMENT '사용자 ID',
    `status` varchar(10) NOT NULL COMMENT '상태',
    `num` int NOT NULL DEFAULT '0' COMMENT '이용권 수',
    `started_at` timestamp NULL DEFAULT NULL COMMENT '시작 일시',
    `ended_at` timestamp NULL DEFAULT NULL COMMENT '종료 일시',
    `expired_at` timestamp NULL DEFAULT NULL COMMENT '만료 일시',
    `created_at` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `deleted` tinyint(1) NOT NULL DEFAULT '0' COMMENT '삭제 여부',
    PRIMARY KEY (`pass_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='이용권';