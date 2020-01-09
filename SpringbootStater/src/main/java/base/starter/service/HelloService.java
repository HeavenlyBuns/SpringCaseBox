package base.starter.service;


import base.starter.common.DbProperties;

public class HelloService {

    DbProperties dbProperties;

    public HelloService(DbProperties dbProperties) {
        this.dbProperties = dbProperties;

    }

    public void hello() {

        System.out.println("hello 你好........" + dbProperties);

    }

}
