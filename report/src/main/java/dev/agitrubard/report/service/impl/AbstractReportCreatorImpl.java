package dev.agitrubard.report.service.impl;

import dev.agitrubard.report.config.RabbitMQReportConfiguration;
import dev.agitrubard.report.model.Report;
import dev.agitrubard.report.port.ReportSavePort;
import dev.agitrubard.report.service.ReportCreator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
@RequiredArgsConstructor
abstract class AbstractReportCreatorImpl implements ReportCreator {

    private final ReportSavePort reportSavePort;
    private final AmqpTemplate amqpTemplate;

    @Override
    public void request() {

        Report reportToBeSave = Report.pending(this.getType());
        Report report = reportSavePort.save(reportToBeSave);

        log.info("Report creation pending with id:{}", report.getId());

        try {
            amqpTemplate.convertAndSend(
                    RabbitMQReportConfiguration.EXCHANGE_NAME,
                    RabbitMQReportConfiguration.ROUTING_KEY,
                    report
            );

            log.info("Report added to queue with id:{}", report.getId());

        } catch (Exception exception) {
            this.fail(exception, report);
        }
    }

    @RabbitListener(queues = RabbitMQReportConfiguration.QUEUE_NAME)
    void create(Report report) {

        log.info("Report creation started with id:{} and type:{}", report.getId(), report.getType());

        try {

            report.processing();
            reportSavePort.save(report);

            log.info("Report creation in progress with id:{}", report.getId());

            Thread.sleep(5000);

            String data = this.create();
            report.complete(data);
            reportSavePort.save(report);

            log.info("Report creation completed with id:{}", report.getId());

        } catch (Exception exception) {
            this.fail(exception, report);
        }

    }

    private void fail(Exception exception, Report report) {
        log.error("Report creation failed with id:{}", report.getId(), exception);
        report.fail();
        reportSavePort.save(report);
    }

    abstract String create();

}
