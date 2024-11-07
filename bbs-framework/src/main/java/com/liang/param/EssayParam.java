package com.liang.param;

import com.liang.pojo.Essay;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EssayParam extends Essay {

    private String type;

}
