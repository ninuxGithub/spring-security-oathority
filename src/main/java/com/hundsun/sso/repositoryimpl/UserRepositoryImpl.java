package com.hundsun.sso.repositoryimpl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.hundsun.sso.constant.CacheConstants;
import com.hundsun.sso.domain.Privilege;
import com.hundsun.sso.domain.User;
import com.hundsun.sso.repository.UserRepository;
import com.hundsun.sso.rowmapper.UserRowMapper;

@Repository("userRepositoryImpl")
public class UserRepositoryImpl implements UserRepository {

	private static UserRowMapper userRowMapper = new UserRowMapper();

	@Autowired
	private JdbcTemplate jdbcTemplate;

	@Override
	public User findByGuid(String guid) {
		final String sql = "select * from user_ where guid = ?";
		List<User> list = jdbcTemplate.query(sql, new Object[] { guid }, userRowMapper);
		User user = null;
		if (null != list && !list.isEmpty()) {
			user = list.get(0);
			user.getPriviliges().addAll(findPrivilege(user.getId()));
		}
		return user;
	}

	/**
	 * 更加用户id获取权限的集合
	 * 
	 * @param userId
	 *            用户ID --Long 类型
	 * @return 该用户的权限集合
	 */
	private Collection<Privilege> findPrivilege(Long userId) {
		String sql = "select privilege from user_privilege where user_id = ?";
		List<String> priviStrs = jdbcTemplate.queryForList(sql, new Object[] { userId }, String.class);
		List<Privilege> privileges = new ArrayList<>(priviStrs.size());
		privileges.addAll(priviStrs.stream().map(Privilege::valueOf).collect(Collectors.toList()));
		return privileges;
	}

	@Override
	public void saveUser(User user) {
		String sql = "insert into user_ (guid, archived, create_time, email, password, username, phone) "
				+ "values(?,?,?,?,?,?,?)";
		jdbcTemplate.update(sql, ps -> {
			ps.setString(1, user.getGuid());
			ps.setBoolean(2, user.getArchived());
			ps.setTimestamp(3, Timestamp.valueOf(user.getCreateTime()));
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getPassword());
			ps.setString(6, user.getUsername());
			ps.setString(7, user.getPhone());
		});

		Long userId = jdbcTemplate.queryForObject("select id from user_ where guid = ?",
				new Object[] { user.getGuid() }, Long.class);

		// 新添加的用户本身是没有id的 ，先从数据库查出添加之后产生的ID 然后赋值给 原来的用户对象
		user.setId(userId);

		// insert privileges
		addPrivileges(user);
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_CACHE, key = "#user.username()")
	public void updateUser(User user) {
		String sql = "update user_ set username=?, password=? , phone=?, email = ? where guid = ?";
		jdbcTemplate.update(sql, ps -> {
			ps.setString(1, user.getUsername());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getPhone());
			ps.setString(4, user.getEmail());
			ps.setString(5, user.getGuid());
		});

		// update privilege

		// ①删除改用户的所以权限
		jdbcTemplate.update("delete * from user_privilege where user_id = ?", ps -> {
			ps.setLong(1, user.getId());
		});

		// ②添加新的权限
		addPrivileges(user);
	}

	/**
	 * 
	 * 添加对应用户的权限
	 * 
	 * @param user
	 */
	private void addPrivileges(User user) {
		if (null != user.getPriviliges() && !user.getPriviliges().isEmpty()) {
			for (Privilege p : user.getPriviliges()) {
				jdbcTemplate.update("insert into user_privilege (user_id, privilege) values (?,?) ", ps -> {
					ps.setLong(1, user.getId());
					ps.setString(2, p.name());
				});

			}
		}
	}

	@Override
	@CacheEvict(value = CacheConstants.USER_CACHE, key = "#user.username()")
	public User findByUsername(String username) {
		String sql = "select * from user_ where username = ? and archived =0 ";
		List<User> list = jdbcTemplate.query(sql, new Object[] { username }, userRowMapper);
		User user = null;
		if (null != list && !list.isEmpty()) {
			user = list.get(0);
			user.getPriviliges().addAll(findPrivilege(user.getId()));
		}
		return user;
	}

	@Override
	public List<User> findUsersByUsername(String username) {
		
		return null;
	}

}
