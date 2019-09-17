package com.example.demo.dao;
import org.springframework.data.repository.PagingAndSortingRepository;
import com.example.demo.model.Transcation;
public interface TranscationDao  extends  PagingAndSortingRepository
	 <Transcation,Integer> {
	
}
