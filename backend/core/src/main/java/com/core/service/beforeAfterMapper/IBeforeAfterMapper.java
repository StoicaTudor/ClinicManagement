package com.core.service.beforeAfterMapper;

public interface IBeforeAfterMapper<From, To>{
    To before(From from);
    To after(From from, To partiallyMappedDestination) throws Exception;
}
