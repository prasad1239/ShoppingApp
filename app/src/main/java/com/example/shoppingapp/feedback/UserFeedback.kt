package com.example.shoppingapp.feedback

import androidx.room.Embedded
import androidx.room.Junction
import androidx.room.Relation
import com.example.shoppingapp.data.cartDatabase.Cart
import com.example.shoppingapp.data.product.ProductEntity
import com.example.shoppingapp.data.userDatabase.User

class UserFeedback (
    @Embedded val user: User,
    @Relation(
        parentColumn = "id",
        entityColumn = "feedbackid",
        associateBy = Junction(Feedback::class)
    )

    val feedback: List<Feedback>
)


