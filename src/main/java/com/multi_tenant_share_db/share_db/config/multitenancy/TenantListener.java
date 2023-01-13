package com.multi_tenant_share_db.share_db.config.multitenancy;

import lombok.extern.slf4j.Slf4j;

import javax.persistence.PrePersist;
import javax.persistence.PreRemove;
import javax.persistence.PreUpdate;

@Slf4j
public class TenantListener {
  @PreUpdate
  @PreRemove
  @PrePersist
  public void setTenant(TenantAware entity) {
    final String tenantId = TenantContext.getTenantId();
    entity.setTenantId(tenantId);
    log.info("entity in TenantListener: " + entity.getTenantId());
  }
}
