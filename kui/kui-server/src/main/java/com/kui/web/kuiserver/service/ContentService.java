package com.kui.web.kuiserver.service;

import com.kui.web.kuiserver.entity.ContentEntity;
import com.kui.web.kuiserver.entity.ContentEntityVO;

import java.util.List;

public interface ContentService {

    List<ContentEntity> find(int page,int size,String category);

    void delete(Integer id, String category);

    void add(ContentEntityVO entityVO);

}
