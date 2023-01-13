package com.multi_tenant_share_db.share_db.model;

import com.multi_tenant_share_db.share_db.config.multitenancy.TenantAware;
import com.multi_tenant_share_db.share_db.config.multitenancy.TenantListener;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

import static java.time.LocalDateTime.now;

@Getter
@Setter
@NoArgsConstructor
@MappedSuperclass
@EntityListeners(TenantListener.class)
public abstract class BaseEntity implements TenantAware, Serializable {
  @Column(name = "tenant_id")
  private String tenantId;

  @Id
  @Column(name = "id")
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  @Column(name = "created_at")
  private LocalDateTime createdAt;

  @Column(name = "updated_at")
  private LocalDateTime updatedAt;

  public BaseEntity(String tenantId) {
    this.tenantId = tenantId;
  }

  @Override
  public boolean equals(Object o) {
    throw new UnsupportedOperationException("Should be implemented by subclass.");
  }

  @Override
  public int hashCode() {
    throw new UnsupportedOperationException("Should be implemented by subclass.");
  }

  @PrePersist
  private void onPrePersist() {
    createdAt = now();
    updatedAt = createdAt;
  }

  @PreUpdate
  private void onPreUpdate() {
    updatedAt = now();
  }
}
