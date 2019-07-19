package com.training.mapper;

import com.training.dto.Content;

public interface ContentMapper {
    void insertSelective(Content content);
}
