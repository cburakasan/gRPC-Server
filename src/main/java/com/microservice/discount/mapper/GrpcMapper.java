package com.microservice.discount.mapper;


import com.microservice.discount.model.DiscountReq;
import com.microservice.grpc.DiscountRequest;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GrpcMapper {
    GrpcMapper INSTANCE = Mappers.getMapper(GrpcMapper.class);

    DiscountReq discountRequestToDiscountReq(DiscountRequest discountRequest);
}
