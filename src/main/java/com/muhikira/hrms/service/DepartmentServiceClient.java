package com.muhikira.hrms.service;

import com.muhikira.hrms.dto.DepartmentDto;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor
public class DepartmentServiceClient {

  private final WebClient.Builder webClientBuilder;

  @CircuitBreaker(name = "departmentServiceCircuitBreaker", fallbackMethod = "departmentServiceFallback")
  public Mono<DepartmentDto> getDepartmentById(Long departmentId) {
    String uri = "http://localhost:8080/api/departments/" + departmentId;
    System.out.println("Request URI: " + uri);  // Log the URI to confirm it's correct

    return webClientBuilder.build()
        .get()
        .uri(uri)  // Pass the complete URI string here
        .retrieve()
        .bodyToMono(DepartmentDto.class);
  }

  // Fallback method in case Department Service fails
  public Mono<DepartmentDto> departmentServiceFallback(Long departmentId, Throwable throwable) {
    DepartmentDto defaultDepartment = new DepartmentDto();  // Default fallback response
    defaultDepartment.setDepartmentName("Fallback Department");
    defaultDepartment.setDepartmentCode("N/A");
    return Mono.just(defaultDepartment);
  }
}
