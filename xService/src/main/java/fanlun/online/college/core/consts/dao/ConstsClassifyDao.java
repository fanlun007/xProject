package fanlun.online.college.core.consts.dao;

import fanlun.online.college.core.consts.domain.ConstsClassify;
import fanlun.online.college.core.consts.domain.ConstsCollege;
import fanlun.online.college.page.TailPage;

import java.util.List;


public interface ConstsClassifyDao {

	/**
	*根据id获取
	**/
	public ConstsClassify getById(Long id);
	
	/**
	 * 根据code获取
	 */
	public ConstsCollege getByCode(String code);

	/**
	*获取所有
	**/
	public List<ConstsClassify> queryAll();
	
	/**
	 * 根据条件动态获取
	 * @param queryEntity
	 * @return
	 */
	public List<ConstsClassify> queryByCondition(ConstsClassify queryEntity);

	/**
	*获取总数量
	**/
	public Integer getTotalItemsCount(ConstsClassify queryEntity);

	/**
	*分页获取
	**/
	public List<ConstsClassify> queryPage(ConstsClassify queryEntity, TailPage<ConstsClassify> page);

	/**
	*创建新记录
	**/
	public void create(ConstsClassify entity);
	
	/**
	 * 创建新记录
	 */
	public void createSelectivity(ConstsClassify entity);

	/**
	*根据id更新
	**/
	public void update(ConstsClassify entity);

	/**
	*根据id选择性更新自动
	**/
	public void updateSelectivity(ConstsClassify entity);

	/**
	*物理删除
	**/
	public void delete(ConstsClassify entity);

	/**
	*逻辑删除
	**/
	public void deleteLogic(ConstsClassify entity);



}

