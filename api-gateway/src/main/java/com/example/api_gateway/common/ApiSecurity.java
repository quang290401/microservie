package com.example.api_gateway.common;

public class ApiSecurity {
    public static final String[] DEFAULT_API_ADMIN = {"/new-path/getAll/**"};
    public static final String[] DEFAULT_API_USER = {"/new-path/hello/**"};

    public static final String[] DEFAULT_API_PUBLIC = {"/login"};
    public static final String DEFAULT_USER_ROLE= "USER";

    public static final String DEFAULT_ADMIN_ROLE= "ADMIN";
}
