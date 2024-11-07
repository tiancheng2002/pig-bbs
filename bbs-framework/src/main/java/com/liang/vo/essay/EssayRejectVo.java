package com.liang.vo.essay;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EssayRejectVo {

    private Integer eid;

    private String rejectInfo;

}
