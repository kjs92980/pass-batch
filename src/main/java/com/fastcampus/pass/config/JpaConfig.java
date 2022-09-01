package com.fastcampus.pass.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;


@EnableJpaAuditing // JPA auditing을 활성화합니다. 본 프로젝트에서는 entity의 생성일시와 수정일시를 자동화하는 용도로 사용합니다.
@Configuration
public class JpaConfig {
}
