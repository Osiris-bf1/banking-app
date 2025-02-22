package com.osiris.banking.service;

import com.osiris.banking.dto.UserDto;

public interface UserService extends AbstractService<UserDto>{

    Long validateAccount(Long id);

    Long unvalidateAccount(Long id);

}
