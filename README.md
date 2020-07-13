# InvoicePrint(发票打印)
整体采用JavaSwing + JSON编写

## 说明
家里一亲戚一直为发票打印而烦恼，买的打印软件又要过期了，正好看我在做软件就让我给他做了个。
因为一直做java开发，对swing多少有些了解，加上用户需求主功能，所以就选用了成本较低的swing。
这个软件最大麻烦就在swing页面上，要布局排序还要对齐，我用了netbeans的图形界面画了几个主要的界面。

### 准备做几个功能：

	1.发票打印
	2.内容编辑
	3.打印预览
	4.公司信息维护（其他基本信息维护）
	5.发票模版的编辑
	6.历史打印记录（预览模式查看，点击可以进入编辑模式）
	7.按发票日期统计收入（各种图表展示）
	8.管理密码
	9.信息加密 
	
	* 前四项为基本功能（必须实现），后面为加颜项。

因为急着用，目前就实现了前四个功能。

### 功能介绍

#### 1.发票编辑页面

票面内容编辑，包括发票代码区、买方卖方信息区、商品信息区、金额区及开票信息区
![主页](./resource/md/invoice-main.jpg)
#### 2.销方、购方信息编辑页面
* 销方

![销方](./resource/md/invoice-buy.jpg)

* 购方

![购方](./resource/md/invoice-sale.jpg)

#### 3.商品信息编辑页面
![商品信息](./resource/md/invoice-goods.jpg)

#### 4.发票预览页面
![发票预览](./resource/md/invoice-print.jpg)
#### 5.发票打印
点击打印预览页面上的打印发票按钮即可打印发票。