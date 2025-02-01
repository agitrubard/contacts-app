package dev.agitrubard.report.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
@EqualsAndHashCode
public abstract class AbstractDomainModel {

    protected LocalDateTime createdAt;
    protected LocalDateTime updatedAt;

}
