package com.example.taskvietsoft;
;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

import javax.crypto.SecretKey;

@SpringBootApplication
@EnableEurekaServer
public class TaskVietSoftApplication {

    public static void main(String[] args) {

        SpringApplication.run(TaskVietSoftApplication.class, args);
        SecretKey key = Keys.hmacShaKeyFor(Decoders.BASE64.decode("Uw3jiJZDZJXETVBZBCvJg0pJUMlhV2lrxAUDMu9GHoA="));
        System.out.printf("FFFF"+key.getAlgorithm());
//        SecretKey key = Jwts.SIG.HS256.key().build();
//        String encode = Encoders.BASE64.encode(key.getEncoded());
//        System.out.println(encode);
    }

}
