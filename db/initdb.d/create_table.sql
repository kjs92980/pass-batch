/*
 id varchar(20)
 status, event varchar(10) - enum
 그 외 varchar(50)
 */

CREATE TABLE `package`
(
    `package_seq`  int         NOT NULL AUTO_INCREMENT COMMENT '패키지 순번',
    `package_name` varchar(50) NOT NULL COMMENT '패키지 이름',
    `count`        int                  DEFAULT NULL COMMENT '이용권 수, NULL인 경우 무제한',
    `period`       int                  DEFAULT NULL COMMENT '기간(일), NULL인 경우 무제한',
    `created_at`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`  timestamp            DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`package_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='패키지';

CREATE TABLE `pass`
(
    `pass_seq`        int         NOT NULL AUTO_INCREMENT COMMENT '이용권 순번',
    `package_seq`     int         NOT NULL COMMENT '패키지 순번',
    `user_id`         varchar(20) NOT NULL COMMENT '사용자 ID',
    `status`          varchar(10) NOT NULL COMMENT '상태',
    `remaining_count` int                  DEFAULT NULL COMMENT '잔여 이용권 수, NULL인 경우 무제한',
    `started_at`      timestamp   NOT NULL COMMENT '시작 일시',
    `ended_at`        timestamp            DEFAULT NULL COMMENT '종료 일시, NULL인 경우 무제한',
    `expired_at`      timestamp            DEFAULT NULL COMMENT '만료 일시',
    `created_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`     timestamp            DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`pass_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='이용권';

CREATE TABLE `bulk_pass`
(
    `bulk_pass_seq`   int         NOT NULL AUTO_INCREMENT COMMENT '대량 이용권 순번',
    `package_seq`     int         NOT NULL COMMENT '패키지 순번',
    `user_group_id`   varchar(20) NOT NULL COMMENT '사용자 그룹 ID',
    `status`          varchar(10) NOT NULL COMMENT '상태',
    `count`           int                  DEFAULT NULL COMMENT '이용권 수, NULL인 경우 무제한',
    `started_at`      timestamp   NOT NULL COMMENT '시작 일시',
    `ended_at`        timestamp            DEFAULT NULL COMMENT '종료 일시, NULL인 경우 무제한',
    `created_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`     timestamp            DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`bulk_pass_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='대량 이용권, 다수의 이용자에게 이용권을 지급하기 위함';

CREATE TABLE `booking`
(
    `booking_seq`  int         NOT NULL AUTO_INCREMENT COMMENT '예약 순번',
    `pass_seq`     int         NOT NULL COMMENT '이용권 순번',
    `user_id`      varchar(20) NOT NULL COMMENT '사용자 ID',
    `status`       varchar(10) NOT NULL COMMENT '상태',
    `used_pass`    tinyint(1) NOT NULL DEFAULT '0' COMMENT '이용권 사용 여부',
    `attended`     tinyint(1) NOT NULL DEFAULT '0' COMMENT '출석 여부',
    `started_at`   timestamp   NOT NULL COMMENT '시작 일시',
    `ended_at`     timestamp   NOT NULL COMMENT '종료 일시',
    `cancelled_at` timestamp            DEFAULT NULL COMMENT '취소 일시',
    `created_at`   timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`  timestamp            DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`booking_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='예약';

CREATE TABLE `user`
(
    `user_id`     varchar(20) NOT NULL COMMENT '사용자 ID',
    `user_name`   varchar(50) NOT NULL COMMENT '사용자 이름',
    `status`      varchar(10) NOT NULL COMMENT '상태',
    `phone`       varchar(50)          DEFAULT NULL COMMENT '연락처',
    `meta`        TEXT                 DEFAULT NULL COMMENT '메타 정보, JSON',
    `created_at`  timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at` timestamp            DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자';

CREATE TABLE `user_group_mapping`
(
    `user_group_id`   varchar(20) NOT NULL COMMENT '사용자 그룹 ID',
    `user_id`         varchar(20) NOT NULL COMMENT '사용자 ID',
    `user_group_name` varchar(50) NOT NULL COMMENT '사용자 그룹 이름',
    `description`     varchar(50) NOT NULL COMMENT '설명',
    `created_at`      timestamp   NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`     timestamp            DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`user_group_id`, `user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='사용자 그룹 매핑';

CREATE TABLE `notification`
(
    `notification_seq` int           NOT NULL AUTO_INCREMENT COMMENT '알람 순번',
    `uuid`             varchar(20)   NOT NULL COMMENT '사용자 uuid (카카오톡)',
    `event`            varchar(10)   NOT NULL COMMENT '이벤트',
    `text`             varchar(1000) NOT NULL COMMENT '알람 내용',
    `sent`             tinyint(1)    NOT NULL DEFAULT '0' COMMENT '발송 여부',
    `sent_at`          timestamp              DEFAULT NULL COMMENT '발송 일시',
    `created_at`       timestamp     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '생성 일시',
    `modified_at`      timestamp              DEFAULT NULL COMMENT '수정 일시',
    PRIMARY KEY (`notification_seq`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='알람';

CREATE TABLE `statistics`
(
    `statistics_seq`      int       NOT NULL AUTO_INCREMENT COMMENT '통계 순번',
    `statistics_at`       timestamp NOT NULL COMMENT '통계 일시',
    `all_count`           int       NOT NULL DEFAULT 0 COMMENT '전체 횟수',
    `attended_count`      int       NOT NULL DEFAULT 0 COMMENT '출석 횟수',
    `cancelled_count`     int       NOT NULL DEFAULT 0 COMMENT '취소 횟수',
    PRIMARY KEY (`statistics_seq`),
    INDEX idx_statistics_at (`statistics_at`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='통계';