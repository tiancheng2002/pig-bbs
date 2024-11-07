package com.liang.vo.essay;

import com.liang.pojo.Essay;
import com.liang.pojo.Type;
import com.liang.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class EssayVo extends Essay {

    private User user;

    private List<Type> types;

}
