package com.matzip.data.base

interface Mapper {
    interface ResponseMapper<RESPONSE, DOMAIN_MODEL>: Mapper {
        fun mapDtoToModel(type: RESPONSE?):DOMAIN_MODEL
    }
}