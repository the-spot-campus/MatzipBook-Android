package com.matzip.feature.base

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.matzip.domain.base.ApiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

abstract class BaseViewModel<STATE: PageState, EVENT>(
    initialPageState : STATE,
) : ViewModel() {

    private val _uiState = MutableStateFlow(initialPageState)
    val uiState = _uiState.asStateFlow()

    private val _eventFlow = EventFlow<EVENT>()
    val eventFlow = _eventFlow.events

    private val _commonError = MutableLiveData<String>()
    val commonError: LiveData<String> = _commonError

    protected fun updateState(state: STATE) {
        viewModelScope.launch {
            _uiState.update { state }
        }
    }
    protected fun emitEventFlow(event: EVENT) {
        viewModelScope.launch {
            _eventFlow.emit(event)
        }
    }

    protected fun<D> resultResponse(
        response: ApiState<D>,
        successCallback : (D) -> Unit,
        errorCallback : ((String) -> Unit)? = null,
        needLoading : Boolean = true)
    {
        when(response){
            is ApiState.Error -> {
                //TODO 에러 코드를 다룰 예정입니다.
//                if(response.errorCode == StatusCode.ERROR_404 ||
//                    response.errorCode == StatusCode.ERROR ||
//                    response.errorCode == StatusCode.NETWORK_ERROR) {
//                    showCommonError(response.errorCode)
//                }
//                else errorCallback?.invoke(response.errorCode)
//                endLoading()
            }
            // TODO Loading 상태일때 화면을 처리할 예정입니다.
            ApiState.Loading -> if(needLoading) {}
            is ApiState.Success -> {
                successCallback.invoke(response.data)
                //endLoading()
            }
        }
    }
}