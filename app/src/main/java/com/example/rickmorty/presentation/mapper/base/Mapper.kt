package com.example.rickmorty.presentation.mapper.base

abstract class Mapper<From, To> {
    abstract fun map(from: From): To
}