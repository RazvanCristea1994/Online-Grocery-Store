<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Deleted Orders</title>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 2px solid #dddddd;
            padding: 8px;
            text-align: center;
        }

        th{
            background-color: darkgray;
        }

        .even-row {
            background-color: ivory;
        }

        .odd-row{
            background-color: #cbd6cb;
        }
    </style>
</head>
<body>
<p>
    Hello dear employee,
</p>
<br>
<p>
    We announce that ${orders?size} other old order(s) have been deleted!<br>
    These are:
</p>
<table>
    <tr>
        <th rowspan="2">Order Id</th>
        <th rowspan="2">Total Price</th>
        <th colspan="3">Products</th>
        <th rowspan="2">Customer email</th>
        <th rowspan="2">Creation Time</th>
    </tr>
    <tr>
        <th>Name</th>
        <th>Quantity</th>
        <th>Price/item</th>
    </tr>
    <#list orders as order>
        <#if order?index % 2 == 0>
            <#assign className="even-row">
        <#else>
            <#assign className="odd-row">
        </#if>
        <#assign rowspanValue = order.orderItems?size + 1>
        <tr>
            <td class="${className}" rowspan="${rowspanValue}">${order.id}</td>
            <td class="${className}" rowspan="${rowspanValue}">${order.totalCost}</td>
            <#list order.orderItems as placedOrderItem>
                <tr class="${className}">
                    <td>${placedOrderItem.product.name}</td>
                    <td>${placedOrderItem.quantity}</td>
                    <td>${placedOrderItem.price}</td>
                    <#if placedOrderItem?index == 0>
                        <td rowspan="${rowspanValue}">${order.user.email}</td>
                        <td rowspan="${rowspanValue}">
                            ${order.orderDate?string('dd.MM.yyyy')}<br>
                            ${order.orderDate?string('HH:mm:ss')}
                        </td>
                    </#if>
                </tr>
            </#list>
        </tr>
    </#list>
</table><br>
<p>Our best,</p>
<p><b>Online Grocery Store</b></p>
</body>
</html>