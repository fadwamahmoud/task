package com.bookstore.task.dto.rquest;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CreateBookRequest(
    @NotBlank(message="Name is required")
    String name,

    @NotNull(message = "Rating is required")
    @Min(value = 1, message = "rating must be between 1 and 5")
    @Max(value = 5, message = "rating must be between 1 and 5")
    Integer rating,

    @NotBlank(message = "Author is required")
    String author
) {
    
}
