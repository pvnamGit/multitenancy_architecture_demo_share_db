package com.multi_tenant_share_db.share_db.config.multitenancy;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.ui.ModelMap;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.WebRequestInterceptor;

import java.nio.file.AccessDeniedException;

import static com.multi_tenant_share_db.share_db.config.multitenancy.TenantConstants.X_TENANT_ID;
import static com.multi_tenant_share_db.share_db.config.multitenancy.TenantConstants.ZERO;

@Component
@Slf4j
public class TenantInterceptor implements WebRequestInterceptor {

  private final String defaultTenant;

  @Autowired
  public TenantInterceptor(
      @Value("${multitenancy.tenant.default-tenant:#{null}}") String defaultTenant) {
    this.defaultTenant = defaultTenant;
  }

  @Override
  public void preHandle(WebRequest request) throws AccessDeniedException {
    String tenantId = ZERO;
    if (request.getHeader(X_TENANT_ID) != null) {
      tenantId = request.getHeader(X_TENANT_ID);
    }
    if (tenantId.equals(ZERO)) {
      throw new AccessDeniedException("No tenant identify");
    }
    log.info("Getting tenant id from TenantInterceptor: tenant id " + tenantId);
    TenantContext.setTenantId(tenantId);
  }

  @Override
  public void postHandle(@NonNull WebRequest request, ModelMap model) {
    TenantContext.clear();
  }

  @Override
  public void afterCompletion(@NonNull WebRequest request, Exception ex) {
    // NOOP
  }
}