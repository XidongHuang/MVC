package tony.project.mvc.dao;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.sql.Connection;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ScalarHandler;

import tony.project.mvc.db.JdbcUtils;
import tony.project.mvc.domain.Customer;

/**
 * 
 * Encapsulate basic CRUD's methods, for subclass inheriting Gaining database
 * connection from current DAO Whole DAO uses DBUtils solving pattern
 * 
 * @param <T>:
 *            the type/class of current DAO dealing with
 */
public class DAO<T> {

	private QueryRunner queryRunner = new QueryRunner();

	private Class<T> clazz;

	public DAO() {
		Type superClass = getClass().getGenericSuperclass();

		if (superClass instanceof ParameterizedType) {
			ParameterizedType parameterizedType = (ParameterizedType) superClass;

			Type[] typeArgs = parameterizedType.getActualTypeArguments();

			if (typeArgs != null && typeArgs.length > 0) {
				if (typeArgs[0] instanceof Class) {
					clazz = (Class<T>) typeArgs[0];
				}
			}

		}

	}

	/**
	 * return a specific record or return how many records in tables
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public <E> E getForValue(String sql, Object... args) {

		Connection connection = null;

		try {
			connection = JdbcUtils.getConnetion();
			return (E) queryRunner.query(connection, sql,new ScalarHandler(), args);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}

		return null;
	}

	/**
	 * 
	 * return corresponding T List
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public List<T> getForList(String sql, Object... args) {

		Connection connection = null;

		try {
			connection = JdbcUtils.getConnetion();
			return queryRunner.query(connection, sql, new BeanListHandler<>(clazz), args);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}

		return null;
	}

	/**
	 * return a corresponding T class instance
	 * 
	 * @param sql
	 * @param args
	 * @return
	 */
	public T get(String sql, Object... args) {
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnetion();
			return queryRunner.query(connection, sql, new BeanHandler<T>(clazz), args);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}
		return null;
	}

	/**
	 * This method encapsulates INSERT, DELETE, UPDATE operation
	 * 
	 * @param sql:
	 *            SQL
	 * @param args:
	 *            placeholder for SQL
	 */
	public void update(String sql, Object... args) {
		Connection connection = null;

		try {
			connection = JdbcUtils.getConnetion();
			queryRunner.update(connection, sql, args);

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			JdbcUtils.releaseConnection(connection);
		}

	}

}
