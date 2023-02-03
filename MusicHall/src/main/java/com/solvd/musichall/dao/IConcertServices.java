package com.solvd.musichall.dao;

import com.solvd.musichall.models.services.ConcertService;

import java.util.List;

public interface IConcertServices extends IBaseDAO<ConcertService> {

    List<ConcertService> getConcertServiceByConcertId(int id);
}
