package com.example.taskvietsoft.common;

public class ApiSecurity {
    public static final String[] DEFAULT_API_USER = {"api/danh-muc","api/thuong-hieu","api/sp-ct/**"};
    public static final String[] DEFAULT_API_ADMIN = {"/authenticate","api/hinh-anh/**", "https://res.cloudinary.com/dsxma8gfm/image/upload/**"
            ,"/login"   };
    public static final String[] DEFAULT_API_PUBLIC = {"/swagger-ui/**","/swagger-ui-/**",
            "/v3/api-docs/**","z484pp3GRw_AIRKRmrTjKkW1jwo","129679929512287","/login"};
    public static final String DEFAULT_USER_ROLE= "USER";

    public static final String DEFAULT_ADMIN_ROLE= "ADMIN";

}
