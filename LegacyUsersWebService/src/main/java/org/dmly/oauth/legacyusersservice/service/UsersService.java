package org.dmly.oauth.legacyusersservice.service;

import org.dmly.oauth.legacyusersservice.response.UserRest;

public interface UsersService {
   UserRest getUserDetails(String userName, String password);
   UserRest getUserDetails(String userName);
}
