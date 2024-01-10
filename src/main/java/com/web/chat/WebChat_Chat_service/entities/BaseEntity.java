package com.web.chat.WebChat_Chat_service.entities;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDateTime;

@Getter
@Setter
@MappedSuperclass
public class BaseEntity {

    @CreatedDate
    @Column(name = "last_updated_date", nullable = false)
    LocalDateTime lastUpdatedDateTime;

    @CreatedDate
    @Column(name = "created_date", nullable = false, updatable = false)
    LocalDateTime createdDateTime;
}