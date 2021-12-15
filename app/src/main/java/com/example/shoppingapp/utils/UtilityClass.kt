package com.example.shoppingapp.utils

class UtilityClass {

    var productImages:HashMap<String,String>
    init {
        productImages=HashMap<String,String>()
        productImages.put("Bread","https://images.pexels.com/photos/2067621/pexels-photo-2067621.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        productImages.put("Salt","https://images.pexels.com/photos/6401/food-kitchen-cooking-spices.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        productImages.put("Sugar","https://images.pexels.com/photos/2523650/pexels-photo-2523650.jpeg?auto=compress&cs=tinysrgb&dpr=3&h=750&w=1260")
        productImages.put("Sunflower Oil","https://images.pexels.com/photos/33783/olive-oil-salad-dressing-cooking-olive.jpg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        productImages.put("Rice","https://images.pexels.com/photos/7232901/pexels-photo-7232901.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
        productImages.put("Milk","https://images.pexels.com/photos/3772492/pexels-photo-3772492.jpeg?auto=compress&cs=tinysrgb&dpr=2&h=750&w=1260")
    }

    fun getImage(productName:String): String? {
        return  productImages.get(productName)
    }
}
