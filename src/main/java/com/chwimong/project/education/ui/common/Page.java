package com.chwimong.project.education.ui.common;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Page {
	
    private int pageStart;
    private int pageEnd;
    private boolean next, prev;
    private int total;
    private String searchValue;
    private Criteria cri;

    
    public Page(Criteria cri, int total) {

        this.cri = cri;
        this.total = total;
        this.pageEnd = (int) (Math.ceil(cri.getPageNum() / 10.0)) * 10;
        this.pageStart = this.pageEnd - 9;

        
        int realEnd = (int) (Math.ceil(total * 1.0 / cri.getAmount()));

        
        if (realEnd < pageEnd) {
            this.pageEnd = realEnd == 0 ? 1 : realEnd;
        }

        this.prev = this.pageStart > 1;
        this.next = this.pageEnd < realEnd;
    }
}
