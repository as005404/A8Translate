package com.foxrider.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ContextResults {
    private Boolean rudeWords;
    private Boolean colloquialisms;
    private Boolean riskyWords;
    private List<Result> results;
    private Integer totalContextCallsMade;
    private Integer timeTakenContext;
}
