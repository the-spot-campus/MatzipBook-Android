package com.matzip.data.base

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.matzip.domain.base.ApiState
import kotlinx.coroutines.flow.*
import retrofit2.Response
import java.io.Reader

abstract class BaseRepository {

     inline fun <reified D, M> apiLaunch(
         crossinline apiCall: suspend () -> Response<ApiResponse<D>>,
         responseMapper: Mapper.ResponseMapper<D, M>,
    ): Flow<ApiState<M>> = flow {

        val response = apiCall()
        when (response.isSuccessful) {
            true -> {
                // API 받아오는데 성공했을 때 status : 200
                val apiResponse = response.body() as ApiResponse
                val data = responseMapper.mapDtoToModel(apiResponse.result)
                val apiSuccess = ApiState.Success(data)
                emit(apiSuccess)
            }
            false -> {
                // API 실패했을 때 status : 200이 아닌 경우
                val apiResponse: ApiResponse<D> = fromGson(response.errorBody()?.charStream())
                val data = responseMapper.mapDtoToModel(apiResponse.result)
                val code = apiResponse.code.ifEmpty { response.code().toString() }
                // TODO 아래 에러는 공통적으로 처리 예정 404, 400 등
                val apiError = ApiState.Error(data, code)

                emit(apiError)
            }
        }
    }.onStart { emit(ApiState.Loading) }.catch { e: Throwable ->
        // API 통신을 시작하면 우선 Loading 상태로 변경
        e.printStackTrace()
         // TODO 아래 하드코딩된 에러 메시지는 추후 Status Code를 정의하면서 수정
        emit(ApiState.Error(null, e.message ?: "Unknown error"))
    }

    inline fun <reified T> fromGson(json: Reader?): ApiResponse<T> {
        return Gson().fromJson(json, object: TypeToken<ApiResponse<T>>() {}.type) ?: ApiResponse()
    }
}