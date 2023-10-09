package jm.task.core.jdbc.dao;

import jm.task.core.jdbc.Constants;
import jm.task.core.jdbc.model.User;
import jm.task.core.jdbc.util.Util;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserDaoJDBCImpl implements UserDao {

    public UserDaoJDBCImpl() {
    }

    public void createUsersTable() {
        Connection conn = null;
        try {
            conn = Util.getConnection();
            conn.setAutoCommit(false);
            conn.createStatement().execute(Constants.CREATE_TABLE_USER);
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void dropUsersTable() {
        Connection conn = null;
        try {
            conn = Util.getConnection();
            conn.setAutoCommit(false);
            conn.createStatement().execute(Constants.DROP_TABLE_USER);
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void saveUser(String name, String lastName, byte age) {
        Connection conn = null;
        try {
            conn = Util.getConnection();
            conn.setAutoCommit(false);
            conn.createStatement().execute(String.format(Constants.INSERT_INTO_USER, name, lastName, age));
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void removeUserById(long id) {
        Connection conn = null;
        try {
            conn = Util.getConnection();
            conn.setAutoCommit(false);
            conn.createStatement().execute(String.format(Constants.DELETE_FROM_USER, id));
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public List<User> getAllUsers() {
        Connection conn = null;
        try {
            conn = Util.getConnection();
            ResultSet resultSet = conn.createStatement().executeQuery(Constants.GET_ALL_USERS);
            List<User> result = new ArrayList<>();
            while (resultSet.next()) {
                result.add(new User(
                        resultSet.getLong(1),
                        resultSet.getString(2),
                        resultSet.getString(3),
                        resultSet.getByte(4))
                );
            }
            return result;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }

    public void cleanUsersTable() {
        Connection conn = null;
        try {
            conn = Util.getConnection();
            conn.setAutoCommit(false);
            conn.createStatement().execute(String.format(Constants.DELETE_TABLE_USER));
            conn.commit();
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                throw new RuntimeException(ex);
            }
            throw new RuntimeException(e);
        }
        finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
