package com.rahul.zenithra.data.model

import com.rahul.zenithra.domain.model.User
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val name: String,
    val email: String,
    val password: String
)

// Mapper: Entity ➝ Domain
fun UserEntity.toDomainModel(): User {
    return User(
        id = id,
        name = name,
        email = email,
        password = password
    )
}

// Mapper: Domain ➝ Entity
fun User.toEntity(): UserEntity {
    return UserEntity(
        id = id,
        name = name,
        email = email,
        password = password
    )
}
