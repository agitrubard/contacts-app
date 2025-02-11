package dev.agitrubard.report.config;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = "dev.agitrubard.*")
class FeignClientConfiguration {
}
