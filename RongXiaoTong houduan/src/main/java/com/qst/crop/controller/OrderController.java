package com.qst.crop.controller;

import com.github.pagehelper.PageInfo;
import com.qst.crop.common.Result;
import com.qst.crop.common.StatusCode;
import com.qst.crop.entity.Order;
import com.qst.crop.entity.PurchaseDetail;
import com.qst.crop.entity.SellPurchase;
import com.qst.crop.model.MyPurchase;
import com.qst.crop.service.OrderService;
import com.qst.crop.service.PurchaseDetailService;
import com.qst.crop.service.PurchaseService;
import com.qst.crop.service.SellPurchaseService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author QST
 * @Description 订单模块
 * @Date 2021-08-24
 */
@Tag(name = "订单模块接口")
@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private PurchaseService purchaseService;

    @Autowired
    private PurchaseDetailService purchaseDetailService;

    @Autowired
    private SellPurchaseService sellPurchaseService;

    //查询所有商品
    @Operation(summary = "查询所有商品")
    @GetMapping("/All/{pageNum}")
    public Result<PageInfo> selectAll(@PathVariable("pageNum") Integer pageNum) {
        PageInfo<Order> orders = orderService.selectAll(pageNum);
//        System.out.println("总记录数："+orders.getTotal());
//        System.out.println("总页数："+orders.getPages());
//        System.out.println("一页的大小："+orders.getSize());
//        System.out.println(orders);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }

    //查询所有商品（货源)商品
    @Operation(summary = "分页查询所有货源(商品)商品")
    @GetMapping("/goods/{pageNum}")
    public Result<PageInfo> selectAllGoods(@PathVariable("pageNum") Integer pageNum) {
        PageInfo<Order> orders = orderService.selectAllGoods(pageNum);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }

    //查询所有需求
    @Operation(summary = "分页查询所有需求")
    @GetMapping("/needs/{pageNum}")
    public Result<PageInfo> selectAllNeeds(@PathVariable("pageNum") Integer pageNum) {
        PageInfo<Order> orders = orderService.selectAllNeeds(pageNum);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }

    //添加订单
    @Operation(summary = "添加商品")
    @PostMapping
    public Result<String> add(@Valid @RequestBody Order order, BindingResult bindingResult) {
        //检查项目
        if (bindingResult.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                stringBuffer.append(objectError.getDefaultMessage()).append("; ");
            }
            String s = stringBuffer.toString();
            System.out.println(s);
            return new Result<String>(false, StatusCode.ERROR, "添加失败",s);
        }
        //获取用户名
        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();
        order.setOwnName(name);
        //设置时间
        order.setCreateTime(new Date());
        order.setUpdateTime(new Date());
        //添加
        orderService.add(order);
        return new Result(true, StatusCode.OK, "添加成功",null);
    }
   // 注意：确保OrderController的类注解（如@RestController、@RequestMapping）已正确添加
   /**public class OrderController {

       @Operation(summary = "添加商品")
       // 支持文件上传格式（consumes指定请求格式）
       @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
       public Result<String> add(
               @RequestParam("title") String title,       // 商品标题
               @RequestParam("description") String description, // 详细介绍
               @RequestParam("price") BigDecimal price,   // 定价
               @RequestParam("images") MultipartFile[] images) { // 商品图片（多图）

           // 1. 校验图片非空
           if (images == null || images.length == 0) {
               return new Result<>(false, StatusCode.ERROR, "添加失败", "商品图片不能为空");
           }

           // 2. 保存图片到服务器（nginx路径）
           List<String> imageUrls = new ArrayList<>();
           // 保存路径（注意：Windows路径用双反斜杠，或用正斜杠）
           String uploadDir = "D:/nginx/html/images/file/order/";
           File dir = new File(uploadDir);
           if (!dir.exists()) {
               dir.mkdirs(); // 创建目录（包括父目录）
           }

           for (MultipartFile image : images) {
               if (image.isEmpty()) {
                   continue;
               }
               // 生成唯一文件名（时间戳+原文件名，避免重复）
               String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
               File dest = new File(uploadDir + fileName);
               try {
                   image.transferTo(dest); // 保存文件到服务器
                   // 图片访问路径（对应nginx的静态资源路径）
                   String imageUrl = "/images/file/order/" + fileName;
                   imageUrls.add(imageUrl);
               } catch (IOException e) {
                   e.printStackTrace();
                   return new Result<>(false, StatusCode.ERROR, "添加失败", "图片上传失败");
               }
           }

           // 3. 构建Order对象
           Order order = new Order();
           order.setTitle(title);
           order.setDescription(description);
           order.setPrice(price);
           // 多图路径用逗号分隔保存
           order.setImageUrls(String.join(",", imageUrls));

           // 4. 设置用户和时间（加非空判断，避免未登录时的空指针）
           var authentication = SecurityContextHolder.getContext().getAuthentication();
           if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
               UserDetails principal = (UserDetails) authentication.getPrincipal();
               order.setOwnName(principal.getUsername());
           } else {
               return new Result<>(false, StatusCode.ERROR, "添加失败", "用户未登录");
           }
           order.setCreateTime(new Date());
           order.setUpdateTime(new Date());

           // 5. 保存订单到数据库
           orderService.add(order);
           return new Result<>(true, StatusCode.OK, "添加成功", null);
       }
   }**/

    //修改id订单
    @Operation(summary = "根据id修改商品")
    @PutMapping("/{id}")
    public Result<String> update(@Validated @RequestBody Order order,BindingResult bindingResult,
                         @PathVariable Integer id) {
        //检查项目
        if (bindingResult.hasErrors()) {
            StringBuffer stringBuffer = new StringBuffer();
            List<ObjectError> allErrors = bindingResult.getAllErrors();
            for (ObjectError objectError : allErrors) {
                stringBuffer.append(objectError.getDefaultMessage()).append("; ");
            }
            String s = stringBuffer.toString();
            System.out.println(s);
            return new Result<String>(false, StatusCode.ERROR, "修改失败",s);
        }
        order.setUpdateTime(new Date());
        order.setOrderId(id);
        orderService.update(order);
        return new Result(true, StatusCode.OK, "修改成功",null);
    }

    //删除订单
    @Operation(summary = "根据id删除商品")
    @DeleteMapping("/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        orderService.delete(id);
        return new Result(true, StatusCode.OK, "删除成功");
    }

    //根据用户名+类型查询商品
    @Operation(summary = "根据用户名+订单类型查询商品")
    @GetMapping("/search/{type}/{pageNum}")
    public Result<PageInfo> selectByType(@PathVariable("type") String type,@PathVariable("pageNum") Integer pageNum) {
        PageInfo<Order> orders = orderService.selectByType(type,pageNum);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }

    //根据id查询商品
    @Operation(summary = "根据id查询商品")
    @GetMapping("/selectById/{id}")
    public Result<Order> selectById(@PathVariable("id") Integer id) {
        Order order = orderService.selectById(id);
        return new Result<Order>(true, StatusCode.OK, "查询成功", order);

    }

    //根据登录用户查询我买的订单
    @Operation(summary = "根据登录用户查询我买的订单")
    @GetMapping("/selectBuys")
    public Result<List<MyPurchase>> selectBuys() {
        List<MyPurchase> purchase = purchaseService.selectByPurchaseType();
        return new Result<List<MyPurchase>>(true, StatusCode.OK, "查询成功", purchase);

    }

    //根据登录用户查询我买的订单详情
    @Operation(summary = "根据登录用户查询我买的订单详情")
    @GetMapping("/selectBuysDetail/{id}")
    public Result<List<PurchaseDetail>> selectBuysDetail(@PathVariable("id") Integer purchaseId) {
        List<PurchaseDetail> purchaseDetail = purchaseDetailService.selectByPurchaseId(purchaseId);
        return new Result<List<PurchaseDetail>>(true, StatusCode.OK, "查询成功", purchaseDetail);
    }

    //根据登录用户查询我卖出的订单
    @Operation(summary = "根据登录用户查询我卖出的订单")
    @GetMapping("/selectSells")
    public Result<List<SellPurchase>> selectSells() {
        List<SellPurchase> purchase = sellPurchaseService.selectByName();
        return new Result<List<SellPurchase>>(true, StatusCode.OK, "查询成功", purchase);

    }

    //分页条件搜索商品（货源）商品
    @Operation(summary = "分页条件搜索商品（货源）订单")
    @GetMapping("/searchGoodsByKeys/{keys}/{pageNum}")
    public Result<PageInfo> searchGoodsByKeys(@PathVariable("keys") String keys,@PathVariable("pageNum") Integer pageNum) {
        PageInfo<Order> orders = orderService.selectGoodsByKeys(keys,pageNum,null);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }

    //分页条件搜索需求商品
    @Operation(summary = "分页条件搜索需求商品")
    @GetMapping("/searchNeedsByKeys/{keys}/{pageNum}")
    public Result<PageInfo> searchNeedsByKeys(@PathVariable("keys") String keys,@PathVariable("pageNum") Integer pageNum) {
            PageInfo<Order> orders = orderService.selectNeedsByKeys(keys,pageNum,null);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }

    //分页条件搜索所有商品
    @Operation(summary = "分页条件搜索所有商品")
    @GetMapping("/searchAllByKeys/{keys}/{pageNum}")
    public Result<PageInfo> searchAllByKeys(@PathVariable("keys") String keys,@PathVariable("pageNum") Integer pageNum) {
        PageInfo<Order> orders = orderService.selectAllByKeys(keys,pageNum);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }

    //分页条件搜索需求商品
    @Operation(summary = "分页条件搜索需求商品")
    @GetMapping("/searchMyNeedsByKeys/{keys}/{pageNum}")
    public Result<PageInfo> searchMyNeedsByKeys(@PathVariable("keys") String keys,@PathVariable("pageNum") Integer pageNum) {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();

        PageInfo<Order> orders = orderService.selectNeedsByKeys(keys,pageNum,name);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }

    //分页条件搜索商品（货源）商品
    @Operation(summary = "分页条件搜索商品（货源）商品")
    @GetMapping("/searchMyGoodsByKeys/{keys}/{pageNum}")
    public Result<PageInfo> searchMyGoodsByKeys(@PathVariable("keys") String keys,@PathVariable("pageNum") Integer pageNum) {

        UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String name = principal.getUsername();

        PageInfo<Order> orders = orderService.selectGoodsByKeys(keys,pageNum,name);
        return new Result<PageInfo>(true, StatusCode.OK, "查询成功", orders);
    }
    //商品下架
    @Operation(summary = "商品下架")
    @PutMapping("/takeDownOrder/{orderId}")
    public Result takeDownOrder(@PathVariable("orderId") String orderId) {
        orderService.takeDown(orderId);
        return new Result<PageInfo>(true, StatusCode.OK, "下架成功");
    }
    //商品上架
    @Operation(summary = "商品上架")
    @PutMapping("/takeUpOrder/{orderId}")
    public Result takeUpOrder(@PathVariable("orderId") String orderId) {
        orderService.takeUp(orderId);
        return new Result<PageInfo>(true, StatusCode.OK, "上架成功");
    }

}
