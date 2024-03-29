package com.ls.adcommon.dump.table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * @author lijiayin
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AdPlanTable {
    
    private Long id;
    
    private Long userId;
    
    private Integer planStatus;
    
    private Date startDate;
    
    private Date endDate;
}
