package com.multi_tenant_share_db.share_db.controller;

import com.multi_tenant_share_db.share_db.model.Alumni;
import com.multi_tenant_share_db.share_db.payload.request.CreateAlumniRequest;
import com.multi_tenant_share_db.share_db.service.AlumniService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class AlumniController {

  private final AlumniService alumniService;


  @PostMapping("/alumni")
  public ResponseEntity createAlumni(@RequestBody CreateAlumniRequest createAlumniRequest, UriComponentsBuilder uriBuilder)
      throws Exception {
    Alumni newAlumni = alumniService.saveAlumni(createAlumniRequest);
    URI location = uriBuilder
        .path("/alumni/{alumniId}")
        .buildAndExpand(newAlumni.getAlumniId())
        .toUri();
    return ResponseEntity.created(location)
        .contentType(MediaType.valueOf(MediaType.APPLICATION_JSON_VALUE))
        .body(Alumni.builder()
            .alumniId(newAlumni.getAlumniId())
            .fullName(newAlumni.getFullName())
            .email(newAlumni.getEmail())
            .phoneNumber(newAlumni.getPhoneNumber())
            .build());
  }

  @GetMapping("/alumnus")
  public ResponseEntity<Map> getCustomers() {
    Map data = new HashMap();
    data.put("data", alumniService.getListAlumnus());
    return ResponseEntity.ok(data);
  }
}

