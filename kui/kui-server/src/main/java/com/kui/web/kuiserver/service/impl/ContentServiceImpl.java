package com.kui.web.kuiserver.service.impl;

import com.kui.web.kuiserver.entity.ContentEntity;
import com.kui.web.kuiserver.entity.ContentEntityVO;
import com.kui.web.kuiserver.service.ContentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Service
public class ContentServiceImpl implements ContentService {

    private static ConcurrentHashMap<String, List<ContentEntity>> data = new ConcurrentHashMap<>();

    static {
        List<ContentEntity> base = new ArrayList<>();
        ContentEntity contentEntity = new ContentEntity(1, "测试标题1", "",
                "\n" +
                        "百度（纳斯达克：BIDU）是全球最大的中文搜索引擎，中国最大的以信息和知识为核心的互联网综合服务公司" +
                        "，全球领先的人工智能平台型公司。百度愿景是：成为最懂用户，并能帮助人们成长的全球顶级高科技公司。“百度”二字",
                LocalDate.now(ZoneId.of("GMT")), LocalDate.now(ZoneId.of("GMT")));
        ContentEntity c1 = new ContentEntity(2, "测试标题2", "",
                "百度知道是由全球最大的中文搜索引擎百度自主研发、基于搜索的互动式知识..",
                LocalDate.now(ZoneId.of("GMT")), LocalDate.now(ZoneId.of("GMT")));
        ContentEntity c2 = new ContentEntity(3, "测试标题3", "",
                "百度图片搜索引擎是中文图片搜索引擎，百度从8亿中文网页中提取各类图片...",
                LocalDate.now(ZoneId.of("GMT")), LocalDate.now(ZoneId.of("GMT")));
        ContentEntity c3 = new ContentEntity(4, "测试标题4", "",
                "百度视频搜索是全球最大的中文视频搜索引擎,拥有最多的中文视频资源",
                LocalDate.now(ZoneId.of("GMT")), LocalDate.now(ZoneId.of("GMT")));
        base.add(contentEntity);
        base.add(c1);
        base.add(c2);
        base.add(c3);
        data.put("base", base);

    }

    @Override
    public List<ContentEntity> find(int page, int size, String category) {
        List<ContentEntity> contentEntities = data.get(category);
        log.info("从本地缓存查询数据,page= [{}],size = [{}],category = [{}],data = [{}]", page, size, category, contentEntities);
        if (CollectionUtils.isEmpty(contentEntities))
            return new ArrayList<>(0);

        int total = contentEntities.size();

        if (page > 0) {
            page = (page - 1) * size;
            page = Math.min(total, page);
        }
        size = Math.min(total, size);
        List<ContentEntity> result = contentEntities.subList(page, size);
        return result;
    }

    @Override
    public void delete(Integer id, String category) {
        List<ContentEntity> contentEntities = data.get(category);
        if (CollectionUtils.isEmpty(contentEntities)) {
            log.info("查询数据为空! id = [{}],category = [{}]", id, category);
        } else {
            remove(contentEntities, id);

        }
    }

    @Override
    public void add(ContentEntityVO entityVO) {
        String category = entityVO.getCategory();
        List<ContentEntity> contentEntities = data.get(category);
        log.info("从本地缓存查询数据,category = [{}],查询出 {} 条!", category, contentEntities.size());
        if (CollectionUtils.isEmpty(contentEntities)) {
            addIfInit(entityVO.toContentEntity(), category);
        } else {
            addIfPresent(contentEntities, entityVO.toContentEntity());

        }

    }

    public void addIfInit(ContentEntity entity, String category) {
        entity.setId(1);
        List<ContentEntity> lt = new ArrayList<>();
        lt.add(entity);
        log.info("本地缓存新增数据,entity = [{}]", entity);
        data.put(category, lt);
    }

    public void addIfPresent(List<ContentEntity> contentEntities, ContentEntity entity) {
        ContentEntity contentEntity = contentEntities.get(contentEntities.size() - 1);
        entity.setId(contentEntity.getId() + 1);
        contentEntities.add(entity);
        log.info("本地缓存新增数据,entity = [{}]", entity);
    }

    public void remove(List<ContentEntity> contentEntities, Integer id) {
        Iterator<ContentEntity> iterator = contentEntities.iterator();
        while (iterator.hasNext()) {
            ContentEntity next = iterator.next();
            if (next.getId() != id)
                continue;

            iterator.remove();
            log.info("移除该内容,id = [{}], contentEntity = [{}]", id, next);
        }

    }
}
