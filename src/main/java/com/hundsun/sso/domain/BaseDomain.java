package com.hundsun.sso.domain;

import java.time.LocalDateTime;

import com.hundsun.sso.utils.DateUtils;
import com.hundsun.sso.utils.GuidGenerator;

/**
 * @function: 领域模型的基础类
 * @spring-security-oathority :项目名称
 * @com.hundsun.sso.domain.BaseDomain.java 类全路径
 * @2016年12月30日 下午3:20:44
 * @BaseDomain
 *
 */
public class BaseDomain {

	public BaseDomain() {
	}

	/**
	 * Database id
	 */
	protected Long id;

	/**
	 * 用于实现逻辑删除 在真是的项目中不会用到逻辑删除， 只会是物理删除，直接将数据库的记录干掉所以这个字段实际开发不会用到
	 */
	protected boolean archived;

	/**
	 * Domain business guid. 业务Id
	 */
	protected String guid = GuidGenerator.generate();

	/**
	 * The domain create time.
	 */
	protected LocalDateTime createTime = DateUtils.now();

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public boolean getArchived() {
		return archived;
	}

	public void setArchived(boolean archived) {
		this.archived = archived;
	}
	

	public String getGuid() {
		return guid;
	}

	public void setGuid(String guid) {
		this.guid = guid;
	}

	public LocalDateTime getCreateTime() {
		return createTime;
	}

	public void setCreateTime(LocalDateTime createTime) {
		this.createTime = createTime;
	}

	@Override
	public String toString() {
		return "BaseDomain [id=" + id + ", archived=" + archived + ", guid=" + guid + ", createTime=" + createTime
				+ "]";
	}

}
