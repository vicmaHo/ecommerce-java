package com.vicma.microservices.customer_microservice.customer;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class CustomerRequest {
    private String id;

    @NotNull(message = "First name is required")
    @NotBlank(message = "First name cannot be empty")
    private String firstName;

    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @NotNull(message = "Email is required")
    @Email(message = "Email is not valid")
    @NotBlank(message = "Email cannot be empty")
    private String email;

    private String phone;
    private String address;
    private String city;
}
