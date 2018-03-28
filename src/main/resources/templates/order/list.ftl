<html>
    <head>
        <meta charset="UTF-8">
        <title>沛煜牛肉后端系统</title>
        <link href="https://cdn.bootcss.com/bootstrap/3.0.1/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
    <div class="container">
        <div class="row clearfix">
            <#-- 表格 -->
            <div class="col-md-12 column">
                <table class="table table-striped table-hover">
                    <thead>
                    <tr class="success">
                        <th>牛肉订单ID</th>
                        <th>姓名</th>
                        <th>手机号</th>
                        <th>地址</th>
                        <th>金额</th>
                        <th>订单状态</th>
                        <th>支付状态</th>
                        <th>创建时间</th>
                        <#-- 2列 -->
                        <th colspan="2">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                <#--遍历表 方法返回的是list-->
                <#list orderDTOPage.content as orderDTO>
                   <tr>
                       <td>${orderDTO.orderId}</td>
                       <td>${orderDTO.buyerName}</td>
                       <td>${orderDTO.buyerPhone}</td>
                       <td>${orderDTO.buyerAddress}</td>
                       <td style="text-align: right">${orderDTO.orderAmount}元</td>
                       <td>${orderDTO.getOrderStatusEnums().getMsg()}</td>
                       <td>${orderDTO.getPayStatusEnums().getMsg()}</td>
                       <td>${orderDTO.createTime}</td>
                       <td>详情</td>
                       <td>取消</td>
                    </tr>
                </#list>
                    </tbody>
                </table>
            </div>
            <#-- 分页导航  -->
            <div class="col-md-12 column">
                    <ul class="pagination pull-right">
                        <li><a href="#">上一页</a></li>

                        <#-- 遍历页数 方法返回的是数字 需要转换成表 e.g. 获得的列表有3页 从1开始计数 1+3 =4页-->
                        <#list 1..orderDTOPage.getTotalPages() as index>
                            <#--index在属性内不是内容不需要引用-->
                            <#if currentPage == index>
                                <#--index是引用-->
                                <li class="disabled"><a href="#">${index}</a></li>
                            <#else>
                                <li><a href="/shop/seller/order/list?page=${index}&size=${currentSize}">${index}</a></li>
                            </#if>
                        </#list>

                        <li><a href="#">下一页</a></li>
                    </ul>
                </div>

        </div>
    </div>
    </body>

</html>