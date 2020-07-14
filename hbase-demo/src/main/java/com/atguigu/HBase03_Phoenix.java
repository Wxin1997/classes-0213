package com.atguigu;

import org.apache.phoenix.queryserver.client.ThinClientUtil;

import java.sql.*;

/**
 * @author wx
 * @create 2020-06-23 14:35
 */
public class HBase03_Phoenix {
    public static void main(String[] args) throws SQLException {
        String connectionUrl = ThinClientUtil.getConnectionUrl("hadoop102", 8765);
        Connection connection = DriverManager.getConnection(connectionUrl);

        PreparedStatement preparedStatement = connection.prepareStatement("select * from test");
        ResultSet resultSet = preparedStatement.executeQuery();

        while (resultSet.next()) {
            System.out.println(resultSet.getString(1) + " " + resultSet.getString(2));
        }

        resultSet.close();
        preparedStatement.close();
        connection.close();

    }
}
