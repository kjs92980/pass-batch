package com.fastcampus.pass.repository.user;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class UserGroupMappingId implements Serializable {
    private String userGroupId;
    private String userId;
}
