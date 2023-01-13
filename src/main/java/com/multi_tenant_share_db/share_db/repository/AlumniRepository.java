package com.multi_tenant_share_db.share_db.repository;

import com.multi_tenant_share_db.share_db.model.Alumni;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AlumniRepository extends JpaRepository<Alumni, Long> {
}
