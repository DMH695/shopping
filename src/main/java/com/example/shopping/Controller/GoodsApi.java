package com.example.shopping.Controller;

import com.example.shopping.service.GoodsService;
import com.example.shopping.service.TypeService;
import com.example.shopping.utils.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login/index")
public class GoodsApi {
    @Autowired
    TypeService typeService;
    @Autowired
    GoodsService goodsService;
    /**
     * 获取分类列表
     * @return
     */
    @RequestMapping(value = "",method = RequestMethod.GET)
    public Object getAllTypes(){
        return new ResultBody<>(true,200,typeService.getAllTypes());
    }

    /**
     * 根据分类名称获取对应的商品
     * @param typename
     * @return
     */
    @RequestMapping(value = "/goods",method = RequestMethod.GET)
    public Object getByTypename(@RequestParam String typename){
        if (typename == null){
            return new ResultBody<>(false,500,"error type");
        }
        if(goodsService.getByTypename(typename) == null){
            return new ResultBody<>(false,501,"unknown type");
        }else{
            return new ResultBody<>(true,200,goodsService.getByTypename(typename));
        }
    }
    @RequestMapping(value = "/goods/detail",method = RequestMethod.GET)
    public Object getById(@RequestParam int id){
        if(id<=0){
            return new ResultBody<>(false,500,"error id");
        }
        if(goodsService.getById(id) == null){
            return new ResultBody<>(false,501,"unknown id");
        }else {
            return new ResultBody<>(true,200,goodsService.getById(id));
        }
    }
}
