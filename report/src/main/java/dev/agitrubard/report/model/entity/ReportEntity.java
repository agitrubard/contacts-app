package dev.agitrubard.report.model.entity;

import dev.agitrubard.report.model.enums.ReportStatus;
import dev.agitrubard.report.model.enums.ReportType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Entity
@Getter
@Setter
@EqualsAndHashCode(callSuper = true)
@Table(name = "ca_report")
public class ReportEntity extends AbstractEntity {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Enumerated(EnumType.STRING)
    @Column(name = "type")
    private ReportType type;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ReportStatus status;

    @Column(name = "data")
    private String data;

}
