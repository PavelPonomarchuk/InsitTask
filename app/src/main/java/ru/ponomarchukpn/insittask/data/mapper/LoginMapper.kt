package ru.ponomarchukpn.insittask.data.mapper

import ru.ponomarchukpn.insittask.data.network.UserDto
import ru.ponomarchukpn.insittask.domain.entity.UserEntity
import javax.inject.Inject

class LoginMapper @Inject constructor() {

    fun mapUserEntityToDto(userEntity: UserEntity) = UserDto(userEntity.username)
}