/*package com.litt.micro.controller;

*//**
 * 批量提交购物车
 * @param openId
 * @param cartPara
 *//*
@RequestMapping("addToOrder")
@ResponseBody
public BackState addToOrder(@RequestBody String cartPara){
	//根据cartId集合将cart信息查询出来，封装进order订单
	String paralist ="{\"openId\":\"o_1QS0WoXqiTeHge-MzBJ1CnPOL0\","
			+ "\"cartList\":[{\"cartId\":20},{\"cartId\":21},{\"cartId\":23}]}";
	JSONObject json = JSONObject.parseObject(cartPara);
	JSONArray arr = (JSONArray) json.get("cartList");
	String openId = (String) json.get("openId");
	Integer addId = (Integer) json.get("addId");
	//生成jsonArray解析为list
	List<Integer> list = new ArrayList<Integer>();
	for(int i=0;i<arr.size();i++){
		JSONObject jsonObject = arr.getJSONObject(i);
		list.add(jsonObject.getInteger("cartId"));
	}
	//根据cartId的list集合将cart集合查出来
	List<Cart> clist = cartServiceImpl.findCartByList(list);
	//生成订单，保存订单的相关详情，并返回orderId主键，进行保存订单项
	User user = userServiceImpl.findUserByopenId(openId);
	Address address = addressServiceImpl.findAddressById(addId);
	//生成订单，保存
	Order order = new Order();
	BackState state = new BackState();
	//设置当前时间，保存订单
	Date parse = null;
	try {
		Date date = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat();
		String formate = sdf.format(date);
		parse = sdf.parse(formate);
	} catch (ParseException e) {
		System.out.println("当前日期初始化错误");
		e.printStackTrace();
	}
	//处理订单的未打折总价
	double subtotal =0;
	Iterator<Cart> cit = clist.iterator();
	while(cit.hasNext()){
		Cart cart = cit.next();
		subtotal= subtotal+cart.getCartTotalUndiscount();
	}
	//订单和订单项,在订单类中得到订单项，然后保存到数据库
	order.setOrderTime(parse);
	//暂时设定为未支付
	order.setOrderState(1);
	order.setOrderTotalUndiscount(subtotal);
	order.setUser(user);
	order.setAddress(address);
	//将订单关联订单项，保存到数据库,并且将order主键返回
	orderServiceImpl.saveOrder(order);
	int flag =0;
	//遍历clist，一次将订单相关的订单项进行保存
	Iterator<Cart> cit1 = clist.iterator();
	while(cit1.hasNext()){
		Cart cart = cit1.next();
		OrderItem oit = new OrderItem();
		oit.setBook(cart.getBook());
		oit.setOitemSubtotal(cart.getCartTotalUndiscount());
		oit.setOitemUnitPrice(cart.getCartUnitPrice());
		oit.setOitemCount(cart.getCartCount());
		oit.setOrder(order);
		oit.setDistributor(cart.getBook().getDistributor());
		orderServiceImpl.saveOrderItem(oit);
		flag++;
	}
	if(flag==clist.size()){
		state.setStateName("HTTP Status 200");
		return  state;
	}
	state.setStateName("HTTP Status 500");
	return state;
}
*/