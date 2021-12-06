package com.example.shopping.Controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.example.shopping.entity.Goods;
import com.example.shopping.service.CartService;
import com.example.shopping.service.UserService;
import com.example.shopping.utils.ResultBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class CartsApi {
    @Autowired
    CartService cartService;
    @Autowired
    UserService userService;

    /**
     * 向购物车中加入商品或者更新商品
     * @param gid 商品的id
     * @param count  商品的数量
     * @param httpSession
     * @return
     */
    @RequestMapping(value = "/insert",method = RequestMethod.GET)
    public Object insert(@RequestParam int gid, @RequestParam int count, HttpSession httpSession){
        int uid = (int) httpSession.getAttribute("uid");
        //根据货物的id去查找price
        Double price = cartService.getPriceById(gid);
        String name = cartService.getNameById(gid);
        String picture = cartService.getPictureById(gid);
        List<JSONObject> res = cartService.findId();
        for(JSONObject ids : res){
            if(ids.get("uid") == (Object) uid && ids.get("gid") == (Object)gid ){
                cartService.update(uid,gid,count);
                return new ResultBody<>(true,200,"update success");
            }else if(cartService.insertCart(uid,gid,name,picture,count,price)){
                return new ResultBody<>(true,200,"insert success");
            }
        }
        return null;
    }

    /**
     * 根据uid和gid删除购物车中的商品
     * @param uid
     * @param gid
     * @return
     */
    @RequestMapping(value = "carts/delete")
    public Object deleteCart(@RequestParam int uid,@RequestParam int gid){
        cartService.deleteCart(uid,gid);
        return new ResultBody<>(true,200,null);
    }

    /**
     * 获取购物车中的所有内容
     * @return
     */
    @RequestMapping(value = "/carts",method = RequestMethod.GET)
    public Object getCarts(){
        return new ResultBody<>(true,200,cartService.findAll());
    }

    /**
     * 更新数据库中商品的数量
     * @param gid 商品的id
     * @param count 商品的数量
     * @param session
     * @return
     */
    @RequestMapping(value = "count",method = RequestMethod.GET)
    public Object changeCount(@RequestParam int gid,@RequestParam int count,HttpSession session){
        int uid = (int) session.getAttribute("uid");
        List<JSONObject> res = cartService.findId();
        for(JSONObject ids : res){
            if(ids.get("uid") == (Object) uid && ids.get("gid") == (Object)gid ){
                cartService.updateCount(uid,gid,count);
                return new ResultBody<>(true,200,"update success");
            }else {
                return new ResultBody<>(false,501,"unknown cart ");
            }
        }
        return null;
    }

    /**
     * 对前端进行勾选的商品进行计算合计
     * @param gids  勾选的商品的id
     * @param session
     * @return
     * 未测试
     */
    @RequestMapping(value = "totalMoney",method = RequestMethod.GET)
    public Object countMoney(@RequestParam List<Integer> gids, HttpSession session){
        double total = total(session,gids);
        session.setAttribute("total",total);
        return new ResultBody<>(true,200,null);
    }

    /**
     * 返回收货地址和订单信息以及合计（前端可以以从session中获取）
     * @param gids
     * @param session
     * @return
     * 未测试
     */
    @RequestMapping("/carts/order")
    public Object getOrder(@RequestParam List<Integer> gids,HttpSession session){
        JSONObject res = new JSONObject();
        //确认收货地址
        int uid = (int) session.getAttribute("uid");
        JSONObject res1 = userService.findUser(uid);
        //确认订单信息
        List<JSONObject> res2 = cartService.findOrder(uid,gids);
        res.put("user",res1);
        res.put("order",res2);
        //显示合计,前端可以从session中获取
        return new ResultBody<>(true,200,res);
    }

    /**
     * 计算合计
     * @param session
     * @return
     */
    public double total(HttpSession session,List<Integer> gids){
        int uid = (int) session.getAttribute("uid");
        double total = 0;
        List<JSONObject> res = cartService.findCountAndPrice(uid,gids);
        for(JSONObject jsonObject : res){
            int count = (int) jsonObject.get("count");
            double price = (double) jsonObject.get("price");
            total = total + price * count;
        }
        return total;
    }
}
