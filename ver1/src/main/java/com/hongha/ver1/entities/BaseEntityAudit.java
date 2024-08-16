package com.hongha.ver1.entities;

import java.time.LocalDateTime;
import java.util.Objects;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntityAudit extends BaseEntity {

	@CreatedBy
	@Column(name = "created_by", updatable = false)
	private String createdBy;
	@LastModifiedBy
	@Column(name = "updated_by")
	private String updatedBy;

	@CreationTimestamp
	@Column(name = "created_at", updatable = false)
	private LocalDateTime createdAt;

	@UpdateTimestamp
	@Column(name = "updated_at")
	private LocalDateTime updatedAt;

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}
		if (!(o instanceof BaseEntityAudit) || !super.equals(o)) {
			return false;
		}
		BaseEntityAudit that = (BaseEntityAudit) o;
		return createdBy.equals(that.createdBy) && updatedBy.equals(that.updatedBy) && createdAt.equals(that.createdAt)
				&& updatedAt.equals(that.updatedAt);
	}

	@Override
	public int hashCode() {
		return Objects.hash(super.hashCode(), createdBy, updatedBy, createdAt, updatedAt);
	}

	@Override
	public String toString() {
		return "BaseEntityAudit{" + "createdBy='" + createdBy + '\\' + ", updatedBy='" + updatedBy + '\\'
				+ ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + "}" + super.toString();
	}
}
