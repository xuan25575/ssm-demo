package com.training.service.impl;

import com.training.dto.Content;
import com.training.mapper.ContentMapper;
import com.training.service.IContentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service(value="contentService")
public class ContentServiceImpl implements IContentService {

    @Autowired
    private ContentMapper contentMapper;

    @Override
    public void insertSelective(Content content) {
        contentMapper.insertSelective(content);
    }
}
