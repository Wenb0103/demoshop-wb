package com.example.demodanei.controller;

import com.example.demodanei.entity.District;
import com.example.demodanei.service.IDistrictService;
import com.example.demodanei.util.ResponseResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/districts")
public class DistrictController {
    @Autowired
    private IDistrictService districtService;

    @GetMapping("/")
    public ResponseResult<List<District>>
    getByParent(@RequestParam("parent") String parent) {
        List<District> data
                = districtService.getByParent(parent);
        ResponseResult<List<District>> rr
                = new ResponseResult<List<District>>();
        rr.setState(200);
        rr.setData(data);
        return rr;
    }
}
