package com.pbank.loan.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)

public class BaseEntity {

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createdAt;

    @CreatedBy
    @Column(updatable = false)
    private String createdBy;

    @LastModifiedDate
    @Column(insertable = false)
    private LocalDateTime updatedAt;

    @LastModifiedBy
    @Column(insertable = false)
    private String updatedBy;

    public BaseEntity() {

    }

    public BaseEntity( LocalDateTime createdAt, String createdBy, LocalDateTime updatedAt, String updatedBy) {

        this.createdAt = createdAt;
        this.createdBy = createdBy;
        this.updatedAt = updatedAt;
        this.updatedBy = updatedBy;
    }




    public LocalDateTime getCreatedAt() {
        Logger logger = LoggerFactory.getLogger(BaseEntity.class);
        logger.error("Base entity getCreatedAt");
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        Logger logger = LoggerFactory.getLogger(BaseEntity.class);
        logger.error("Base entity setCreatedAt");
        this.createdAt = createdAt;
    }

    public String getCreatedBy() {
        Logger logger = LoggerFactory.getLogger(BaseEntity.class);
        logger.error("Base entity getCreatedBy");

        return createdBy;
    }

    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
        Logger logger = LoggerFactory.getLogger(BaseEntity.class);
        logger.error("Base entity setCreatedBy");
    }

    public LocalDateTime getUpdatedAt() {
        Logger logger = LoggerFactory.getLogger(BaseEntity.class);
        logger.error("Base entity getUpdatedAt");
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        Logger logger = LoggerFactory.getLogger(BaseEntity.class);
        logger.error("Base entity setUpdatedAt");
    }

    public String getUpdatedBy() {
        Logger logger = LoggerFactory.getLogger(BaseEntity.class);
        logger.error("Base entity getUpdatedBy");
        return updatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        this.updatedBy = updatedBy;
        Logger logger = LoggerFactory.getLogger(BaseEntity.class);
        logger.error("Base entity setUpdatedBy");
    }

    @Override
    public String toString() {
        return "BaseEntity\n{" +
                " createdAt=" + createdAt +
                ", createdBy='" + createdBy + '\'' +
                ", updatedAt=" + updatedAt +
                ", updatedBy='" + updatedBy + '\'' +
                '}';
    }
}
