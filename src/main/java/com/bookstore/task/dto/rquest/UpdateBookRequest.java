package com.bookstore.task.dto.rquest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record UpdateBookRequest(
    @NotBlank(message = "Name is required")
    String name,
    
    @NotNull(message = "rating is required")
    @Min(value= 1) @Max(value=5)
    Integer rating,

    @NotBlank(message = "Author is required")
    String author,

    @NotNull(message = "Version is required")
    Long version
) {
    
}
