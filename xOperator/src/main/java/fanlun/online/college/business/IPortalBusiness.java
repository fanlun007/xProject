package fanlun.online.college.business;

import fanlun.online.college.vo.ConstsClassifyVO;
import fanlun.online.college.vo.CourseSectionVO;

import java.util.List;
import java.util.Map;


public interface IPortalBusiness {

    /**
     * 获取所有，包括一级分类&二级分类
     */
    List<ConstsClassifyVO> queryAllClassify();

    /**
     * 获取所有分类
     */
    Map<String, ConstsClassifyVO> queryAllClassifyMap();

    /**
     * 获取课程章节
     */
    List<CourseSectionVO> queryCourseSection(Long courseId);

    /**
     * 为分类设置课程推荐
     */
    void prepareRecomdCourses(List<ConstsClassifyVO> classifyVoList);
}
