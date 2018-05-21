package fanlun.online.college.core.consts.service.impl;

import java.util.List;


import fanlun.online.college.core.consts.dao.ConstsSiteCarouselDao;
import fanlun.online.college.core.consts.domain.ConstsSiteCarousel;
import fanlun.online.college.core.consts.service.IConstsSiteCarouselService;
import fanlun.online.college.page.TailPage;
import fanlun.online.college.storage.QiniuStorage;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class ConstsSiteCarouselServiceImpl implements IConstsSiteCarouselService {

    @Autowired
    private ConstsSiteCarouselDao entityDao;

    @Override
    public ConstsSiteCarousel getById(Long id) {
        return entityDao.getById(id);
    }

    @Override
    public List<ConstsSiteCarousel> queryCarousels(Integer count) {
        List<ConstsSiteCarousel> resultList = entityDao.queryCarousels(count);
        //处理为七牛图片链接
        for (ConstsSiteCarousel item : resultList) {
            item.setPicture(QiniuStorage.getUrl(item.getPicture()));
        }
        return resultList;
    }

    @Override
    public TailPage<ConstsSiteCarousel> queryPage(ConstsSiteCarousel queryEntity, TailPage<ConstsSiteCarousel> page) {
        Integer itemsTotalCount = entityDao.getTotalItemsCount(queryEntity);
        List<ConstsSiteCarousel> items = entityDao.queryPage(queryEntity, page);
        if (CollectionUtils.isNotEmpty(items)) {
            for (ConstsSiteCarousel item : items) {
                String pictureUrl = QiniuStorage.getUrl(item.getPicture());//处理图片
                item.setPicture(pictureUrl);
            }
        }
        page.setItemsTotalCount(itemsTotalCount);
        page.setItems(items);
        return page;
    }

    @Override
    public void create(ConstsSiteCarousel entity) {
        entityDao.create(entity);
    }

    /**
     * 创建新记录
     */
    public void createSelectivity(ConstsSiteCarousel entity) {
        this.entityDao.createSelectivity(entity);
    }

    @Override
    public void update(ConstsSiteCarousel entity) {
        entityDao.update(entity);
    }

    @Override
    public void updateSelectivity(ConstsSiteCarousel entity) {
        entityDao.updateSelectivity(entity);
    }

    @Override
    public void delete(ConstsSiteCarousel entity) {
        entityDao.delete(entity);
    }

    @Override
    public void deleteLogic(ConstsSiteCarousel entity) {
        entityDao.deleteLogic(entity);
    }


}

