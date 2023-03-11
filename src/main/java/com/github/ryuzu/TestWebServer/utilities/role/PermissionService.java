//package com.github.ryuzu.TestWebServer.utilities.role;
//
//import com.github.ryuzu.TestWebServer.redis.database.permission.Permission;
//import com.github.ryuzu.TestWebServer.redis.database.permission.Permission$;
//import com.redis.om.spring.search.stream.EntityStream;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.stream.Collectors;
//
//@Service
//@RequiredArgsConstructor
//public class PermissionService {
//    private final EntityStream entityStream;
//
//    // Find all people
//    public Iterable<Permission> findAllPermission() {
//        return entityStream
//                .of(Permission.class)
//                .filter(Permission$.FLAG.gt(0))
//                .sorted(Permission$.FLAG, SortOrder.ASC) //.sorted(Person$.AGE, SortOrder.ASC) //
//                .collect(Collectors.toList());
//    }
//}
