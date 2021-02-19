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
    Hello dear ${placedOrder.user.firstName} ${placedOrder.user.lastName},
</p>
<br>
<p>
    We announce that your order has been successfully!<br>
    Your order is:
</p>
<table>
    <tr>
        <th>Order number</th>
        <th>Total Price</th>
        <th>Email</th>
        <th>Date</th>
    </tr>
    <tr>
        <td>${placedOrder.id}</td>
        <td>${placedOrder.totalCost}</td>
        <td>${placedOrder.user.email}</td>
        <td>
            ${placedOrder.orderDate?string('dd.MM.yyyy')}<br>
            ${placedOrder.orderDate?string('HH:mm:ss')}
        </td>
    </tr>
</table><br>
<p>Our best,</p>
<p><b>Online Grocery Store</b></p>
</body>
</html>