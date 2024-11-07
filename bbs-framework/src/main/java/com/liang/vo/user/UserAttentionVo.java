package com.liang.vo.user;

import com.liang.pojo.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserAttentionVo extends User {

    private Set<Integer> attentions;

    private Set<Integer> userFans;

}
