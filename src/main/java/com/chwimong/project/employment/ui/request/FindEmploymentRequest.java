package com.chwimong.project.employment.ui.request;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class FindEmploymentRequest {

	private String id;
    private String job;
    private String region;
    private String sort;
    private String offset;
    private String searchValue;
    
    public String getOffset() {
        return (offset == null || offset.isEmpty()) ? "1" : offset;
    }
    
    public String getSort() {
        return (sort == null || sort.isEmpty()) ? "latest_order" : sort;
    }
    
}
