package com.bailian.activity.check.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.bailian.activity.check.entity.CheckRequest;

public interface CheckRequestRepository extends JpaRepository<CheckRequest, Long> {

}
