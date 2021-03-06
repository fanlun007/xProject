package fanlun.online.college.controller;

import fanlun.online.college.core.consts.domain.ConstsCollege;
import fanlun.online.college.core.consts.service.IConstsCollegeService;
import fanlun.online.college.page.TailPage;
import fanlun.online.college.web.JsonView;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 网校管理
 */
@Controller
@RequestMapping("/college")
public class CollegeController {

    @Autowired
    private IConstsCollegeService entityService;

    /**
     * 分页
     *
     * @param queryEntity
     * @param page
     * @return
     */
    @RequestMapping(value = "/queryPageList")
    public ModelAndView queryPage(ConstsCollege queryEntity, TailPage<ConstsCollege> page) {
        ModelAndView mv = new ModelAndView("cms/college/collegePageList");
        mv.addObject("curNav", "college");

        if (StringUtils.isNotEmpty(queryEntity.getName())) {
            queryEntity.setName(queryEntity.getName().trim());
        } else {
            queryEntity.setName(null);
        }

        page.setPageSize(2);
        page = entityService.queryPage(queryEntity, page);
        mv.addObject("page", page);
        mv.addObject("queryEntity", queryEntity);
        return mv;
    }

    @RequestMapping(value = "/getById")
    @ResponseBody
    public String getById(Long id) {
        return JsonView.render(entityService.getById(id));
    }

    @RequestMapping(value = "/doMerge")
    @ResponseBody
    public String doMerge(ConstsCollege entity) {
        if (entity.getId() == null) {
            //添加
            ConstsCollege tmpEntity = entityService.getByCode(entity.getCode());
            if (tmpEntity != null) {
                return JsonView.render(1, "此编码已存在");
            }
            entityService.createSelectivity(entity);
        } else {
            //修改
            ConstsCollege tmpEntity = entityService.getByCode(entity.getCode());
            if (tmpEntity != null && !tmpEntity.getId().equals(entity.getId())) {
                return JsonView.render(1, "此编码已存在");
            }
            entityService.updateSelectivity(entity);
        }
        return new JsonView().toString();
    }

    @RequestMapping(value = "/deleteLogic")
    @ResponseBody
    public String deleteLogic(ConstsCollege entity) {
        entityService.deleteLogic(entity);
        return new JsonView().toString();
    }

}

