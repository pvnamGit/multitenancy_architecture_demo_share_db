package com.multi_tenant_share_db.share_db.payload.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateAlumniRequest {
  private String fullName;
  private String email;
  private String phoneNumber;
}
