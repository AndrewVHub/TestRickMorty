package com.example.rickmorty.domain.mapper

abstract class Mapper<From, To> {
    abstract fun map(from: From): To
}