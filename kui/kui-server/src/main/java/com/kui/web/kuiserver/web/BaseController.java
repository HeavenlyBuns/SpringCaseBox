package com.kui.web.kuiserver.web;


import com.kui.web.kuiserver.entity.ContentEntity;
import com.kui.web.kuiserver.entity.ContentEntityVO;
import com.kui.web.kuiserver.service.ContentService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Api(value = "基础接口",tags = "基础服务")
@RequestMapping("/base")
@RestController
public class BaseController {

    @Autowired
    private ContentService contentService;

    @ApiOperation("查询")
    @GetMapping("/find")
    public ResponseEntity<List<ContentEntity>> find(@RequestParam(defaultValue = "1") Integer page,
                                                    @RequestParam(defaultValue = "20") Integer size,
                                                    @RequestParam(defaultValue = "base") String category) {
        List<ContentEntity> contentEntities =  contentService.find(page, size, category);
        return ResponseEntity.ok(contentEntities);

    }

    @ApiOperation("根据id删除")
    @DeleteMapping("/{id}")
    public ResponseEntity find(@PathVariable Integer id,
                                                    @RequestParam(defaultValue = "base") String category) {
        contentService.delete(id, category);
        return ResponseEntity.ok().build();

    }

    @ApiOperation("新增")
    @PostMapping
    public ResponseEntity add(@RequestBody @Valid ContentEntityVO entityVO) {
        contentService.add(entityVO);
        return ResponseEntity.ok().build();

    }

}
