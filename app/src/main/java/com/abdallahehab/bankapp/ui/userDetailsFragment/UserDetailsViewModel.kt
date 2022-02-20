package com.abdallahehab.bankapp.ui.userDetailsFragment

import androidx.lifecycle.*
import com.abdallahehab.bankapp.database.Transaction
import com.abdallahehab.bankapp.database.User
import com.abdallahehab.bankapp.database.dataBaseMapper
import com.abdallahehab.bankapp.domain.TransactionData
import com.abdallahehab.bankapp.domain.UserData
import com.abdallahehab.bankapp.repository.Repository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(val repo: Repository): ViewModel() {

    var listOfUserNames = Transformations.map(repo.getAllUserNames()) {
        it.toMutableList().filter { email-> email!=(currentUser.value?.email) }.toList()
    }

    var currentUser = MutableLiveData<UserData>()

    var receiverUserEmail = MutableLiveData<String>()
    var receiverErrorMessage = MutableLiveData<String?>(null)

    var transferredAmount = MutableLiveData<String>()
    var transferredAmountErrorMessage = MutableLiveData<String?>(null)

    val transferViewsVisibility = MutableLiveData<Boolean>()

    val successfulMessageVisiblity = MutableLiveData<Boolean>(false)
    val loadingEnable = MutableLiveData<Boolean>(false)

    fun onTransferClicked(){
        enableTransactionViews()

    }

    fun onConfirmTransactionClicked(){
        disableErrorMessages()
        validateReceiverEmail()
        validateTransferecAmount()
        if(transferredAmountErrorMessage.value == null && receiverErrorMessage.value == null){
            val transferedAmountInt =  transferredAmount.value?.toInt()
            if (transferedAmountInt!! >currentUser.value?.balance!! || transferedAmountInt == 0){
                transferredAmountErrorMessage.value = "the amount exceeded the balance or equal zero"
            }else{
                enableLoading()
                // if it gets in here both feilds should not be empty
                viewModelScope.launch{
                    val receiverUser = repo.getUserByEmail(receiverUserEmail.value!!)
                    repo.updateUser(User(receiverUser.userName,(receiverUser.userEmail),(receiverUser.balance +transferedAmountInt)))
                    repo.updateUser(User(currentUser.value?.userName!!,(currentUser.value?.email!!),(currentUser.value?.balance!! -transferedAmountInt)))
                    val updatedCurrentUser = repo.getUserByEmail(currentUser.value?.email!!)
//                    delay(500)
                    currentUser.value = dataBaseMapper.userToUserData(updatedCurrentUser)
                    repo.insertTransaction(Transaction(updatedCurrentUser.userEmail,receiverUser.userEmail,transferedAmountInt))
                    delay(400)
                    disableTransactionViews()
                    enableSuccessfulMessageVisiblity()
                    disableLoading()

                }
                
            }
        }
    }

     fun validateReceiverEmail(){
        if (receiverUserEmail.value.isNullOrEmpty()){
            receiverErrorMessage.value ="this field can't be empty"
        }
    }
     fun validateTransferecAmount(){
        if (transferredAmount.value.isNullOrEmpty()){
            transferredAmountErrorMessage.value ="this field can't be empty"
        }
    }
    private fun disableErrorMessages(){
        receiverErrorMessage.value =null
        transferredAmountErrorMessage.value =null
    }

    private fun enableTransactionViews(){
        transferViewsVisibility.value = true
    }
    private fun disableTransactionViews(){
        transferViewsVisibility.value = false
    }
    private fun enableLoading(){
        loadingEnable.value = true
    }
    private fun disableLoading(){
        loadingEnable.value = false
    }
    private fun enableSuccessfulMessageVisiblity(){
        successfulMessageVisiblity.value = true
    }
     fun disableSuccessfulMessageVisiblity(){
         successfulMessageVisiblity.value = false
    }


}