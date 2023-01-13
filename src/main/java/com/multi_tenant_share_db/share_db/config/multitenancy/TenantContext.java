package com.multi_tenant_share_db.share_db.config.multitenancy;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public final class TenantContext {

  private static final InheritableThreadLocal<String> CURRENT_TENANT = new InheritableThreadLocal<>();

  private TenantContext() {
  }

  public static String getTenantId() {
    return CURRENT_TENANT.get();
  }

  public static void setTenantId(String tenantId) {
    log.info("Setting tenantId to " + tenantId + " in Tenant Context");
    CURRENT_TENANT.set(tenantId);
    log.info("CURRENT_TENANT: " + CURRENT_TENANT.get());
  }

  public static void clear() {
    CURRENT_TENANT.remove();
  }
}