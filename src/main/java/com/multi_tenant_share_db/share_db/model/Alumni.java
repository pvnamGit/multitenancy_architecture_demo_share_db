package com.multi_tenant_share_db.share_db.model;

import lombok.*;

import javax.persistence.Entity;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity(name = "alumnus")
public class Alumni extends BaseEntity {
  private String alumniId;
  private String fullName;
  private String email;
  private String phoneNumber;
}
