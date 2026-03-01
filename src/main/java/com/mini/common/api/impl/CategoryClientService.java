package com.mini.common.api.impl;

import com.mini.common.api.CategoryClient;
import com.mini.common.api.dot.CategoryBasicDTO;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoryClientService implements CategoryClient {
    @Override
    public List<CategoryBasicDTO> getAllOfOneLevel() {
        return new ArrayList<>();
    }
}
