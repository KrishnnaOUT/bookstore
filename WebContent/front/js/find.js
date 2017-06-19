function isSelected(myClass,valueString) {
	
	var isSelected = document.getElementById("isSelectedFactor_class");
	isSelected.id = "";
	
	var myclass = document.getElementsByClassName(myClass);
	myclass[0].id = "isSelectedFactor_class";
	
	var isSelected = document.getElementsByClassName("hidden_input_text_class");
	isSelected[0].value = valueString;
	
	var submitForm = document.getElementById("searchButton");
	submitForm.submit();
};

function isSelectedOfPrice(myPrice) {
	
	var isSelected = document.getElementById("isSelectedFactor_price");
	isSelected.id = "";
	
	var myprice = document.getElementsByClassName(myPrice);
	myprice[0].id = "isSelectedFactor_price";
	
	var isSelected = document.getElementsByClassName("hidden_input_text_price");
	isSelected[0].value = myprice[0].innerHTML;
	
	var submitForm = document.getElementById("searchButton");
	submitForm.submit();
};

function isSelectedOfOrder(myOrder,value) {
	
	var isSelected = document.getElementById("isSelectedFactor_order");
	isSelected.id = "";
	
	var myorder = document.getElementsByClassName(myOrder);
	myorder[0].id = "isSelectedFactor_order";
	
	var isSelected = document.getElementsByClassName("hidden_input_text_order");
	isSelected[0].value = value;
	
	var submitForm = document.getElementById("searchButton");
	submitForm.submit();
};


function OrderBySales(){
	var good_order = document.getElementById("good_order");
	good_order.value = "good_salesNum";
}

function OrderByPrice(){
	var good_order = document.getElementById("good_order");
	good_order.value = "good_price";
}