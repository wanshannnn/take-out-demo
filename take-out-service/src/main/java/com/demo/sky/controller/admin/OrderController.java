package com.demo.sky.controller.admin;

import com.demo.sky.dto.OrdersCancelDTO;
import com.demo.sky.dto.OrdersPageQueryDTO;
import com.demo.sky.dto.OrdersRejectionDTO;
import com.demo.sky.result.PageResult;
import com.demo.sky.result.Result;
import com.demo.sky.service.OrderService;
import com.demo.sky.vo.OrderStatisticsVO;
import com.demo.sky.vo.OrderVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

/**
 * 订单管理
 */
@RestController("adminOrderController")
@RequestMapping("/admin/order")
@Slf4j
@Tag(name = "订单管理接口")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    /**
     * 订单搜索
     *
     * @param ordersPageQueryDTO
     * @return
     */
    @GetMapping("/conditionSearch")
    @Operation(description = "订单搜索")
    public Result<PageResult> conditionSearch(OrdersPageQueryDTO ordersPageQueryDTO) {
        PageResult pageResult = orderService.conditionSearch(ordersPageQueryDTO);
        return Result.success(pageResult);
    }

    /**
     * 各个状态的订单数量统计
     *
     * @return
     */
    @GetMapping("/statistics")
    @Operation(description = "各个状态的订单数量统计")
    public Result<OrderStatisticsVO> statistics() {
        OrderStatisticsVO orderStatisticsVO = orderService.statistics();
        return Result.success(orderStatisticsVO);
    }

    /**
     * 查询订单详情
     * @param id
     * @return
     */
    @GetMapping("/details/{id}")
    @Operation(description = "查询订单详情")
    public Result<OrderVO> details(Long id) {
        OrderVO details = orderService.details(id);
        return Result.success(details);
    }

    /**
     * 接单
     * @param ordersCancelDTO
     * @return
     */
    @PutMapping("/confirm")
    @Operation(description = "接单")
    public Result confirm(@RequestBody OrdersCancelDTO ordersCancelDTO) {
        orderService.confirm(ordersCancelDTO);
        return Result.success();
    }

    /**
     * 拒单
     * @param ordersRejectionDTO
     * @return
     */
    @PutMapping("/rejection")
    @Operation(description = "拒单")
    public Result rejection(@RequestBody OrdersRejectionDTO ordersRejectionDTO) throws Exception {
        orderService.rejection(ordersRejectionDTO);
        return Result.success();
    }


    /**
     * 取消订单
     * @param ordersCancelDTO
     * @return
     */
    @PutMapping("/cancel")
    @Operation(description = "取消订单")
    public Result cancel(@RequestBody OrdersCancelDTO ordersCancelDTO) throws Exception {
        orderService.cancel(ordersCancelDTO);
        return Result.success();
    }

    /**
     * 派送订单
     *
     * @return
     */
    @PutMapping("/delivery/{id}")
    @Operation(description = "派送订单")
    public Result delivery(@PathVariable("id") Long id) {
        orderService.delivery(id);
        return Result.success();
    }

    /**
     * 完成订单
     * @param id
     * @return
     */
    @PutMapping("/complete/{id}")
    @Operation(description = "完成订单")
    public Result complete(Long id) {
        orderService.complete(id);
        return Result.success();
    }

}
