package jm.task.core.jdbc;

import jm.task.core.jdbc.dao.UserDaoJDBCImpl;

public class Main {
    public static void main(String[] args) {
        UserDaoJDBCImpl userDaoJDBC = new UserDaoJDBCImpl();
        userDaoJDBC.createUsersTable();
        userDaoJDBC.saveUser("Bob","Malkov",(byte) 12);
        System.out.println("User с именем: Bob добавлен в базу данных");
        userDaoJDBC.saveUser("John","Vyazkov",(byte) 22);
        System.out.println("User с именем: John добавлен в базу данных");
        userDaoJDBC.saveUser("Bill","Junior", (byte) 23);
        System.out.println("User с именем: Bill добавлен в базу данных");
        userDaoJDBC.saveUser("Petr","Sidorenko",(byte) 45);
    }
}
