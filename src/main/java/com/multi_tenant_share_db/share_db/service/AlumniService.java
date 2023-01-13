package com.multi_tenant_share_db.share_db.service;

import com.multi_tenant_share_db.share_db.model.Alumni;
import com.multi_tenant_share_db.share_db.payload.request.CreateAlumniRequest;
import com.multi_tenant_share_db.share_db.repository.AlumniRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

import static java.util.stream.Collectors.toList;

@RequiredArgsConstructor
@Component
public class AlumniService {

  private final AlumniRepository alumniRepository;

  @Transactional
  public Alumni saveAlumni(CreateAlumniRequest request) throws Exception {
    String alumniId = UUID.randomUUID().toString();
    Alumni alumni = Alumni.builder()
        .alumniId(alumniId)
        .email(request.getEmail())
        .fullName(request.getFullName())
        .phoneNumber(request.getPhoneNumber()).build();
    alumniRepository.save(alumni);
    return alumni;
  }

  public List<Alumni> getListAlumnus() {
    List<Alumni> listAlumnus = alumniRepository.findAll();
    return listAlumnus.stream()
        .map(alumni -> Alumni.builder()
            .alumniId(alumni.getAlumniId())
            .email(alumni.getEmail())
            .fullName(alumni.getFullName())
            .phoneNumber(alumni.getPhoneNumber())
            .build()).collect(toList());
  }
}