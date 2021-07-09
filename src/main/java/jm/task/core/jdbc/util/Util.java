package jm.task.core.jdbc.util;

import jm.task.core.jdbc.model.User;
import org.hibernate.Hibernate;
import org.hibernate.SessionFactory;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.ServiceRegistry;

import javax.xml.ws.Service;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class Util {
    private static final String URL = "jdbc:mysql://localhost:3306/task?autoReconnect=true&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";


    public static Connection getConnection() throws SQLException {
        Connection connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        connection.setAutoCommit(false);

        return connection ;
    }
    public static SessionFactory factory () {
        SessionFactory sessionFactory;
        Properties properties = new Properties();
        properties.setProperty("hibernate.connection.url",URL);
        properties.setProperty("hibernate.connection.username",USERNAME);
        properties.setProperty("hibernate.connection.password",PASSWORD);
        properties.setProperty("hibernate.connection.driver_class","com.mysql.jdbc.Driver");
        properties.setProperty("show_sql","true");
        properties.setProperty("hibernate.format_sql","true");
        properties.setProperty("default_schema","task");
        properties.setProperty("hibernate.current_session_context_class","thread");
        properties.setProperty("hibernate.connection.CharSet","utf8");
        properties.setProperty("hibernate.connection.characterEncoding","utf8");
        properties.setProperty("hibernate.connection.useUnicode","true");
        ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(properties).build();
        MetadataSources sources = new MetadataSources(serviceRegistry).addAnnotatedClass(User.class);
         sessionFactory = sources.buildMetadata().buildSessionFactory();
         return sessionFactory;
    }

    public Util() throws SQLException {
    }
}
