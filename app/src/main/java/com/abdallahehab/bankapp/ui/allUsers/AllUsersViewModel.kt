package com.abdallahehab.bankapp.ui.allUsers

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.abdallahehab.bankapp.database.dataBaseMapper
import com.abdallahehab.bankapp.domain.UserData
import com.abdallahehab.bankapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AllUsersViewModel @Inject constructor(val repo: UserRepository) : ViewModel() {

    var usersDataList = Transformations.map(repo.getAllUsers()) { userList->
        userList.map { user -> dataBaseMapper.userToUserData(user) }
    }

//    init {
//        viewModelScope.launch{
//            if (repo.getAllUsers().value  == null) repo.insertUsers()
//        }
//    }

    fun updateUsers() {
        usersDataList = Transformations.map(repo.getAllUsers()) { userList ->
            userList.map { user -> dataBaseMapper.userToUserData(user) }
        }
    }
}
