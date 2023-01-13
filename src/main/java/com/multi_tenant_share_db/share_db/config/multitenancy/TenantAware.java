package com.multi_tenant_share_db.share_db.config.multitenancy;

public interface TenantAware {
  String getTenantId();

  void setTenantId(String tenantId);
}
