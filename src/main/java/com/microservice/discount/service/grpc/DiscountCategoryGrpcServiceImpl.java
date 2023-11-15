package com.microservice.discount.service.grpc;

import com.microservice.discount.mapper.GrpcMapper;
import com.microservice.discount.model.AddDiscountCategoryResp;
import com.microservice.discount.service.DiscountProcessService;
import com.microservice.grpc.AddDiscountCategoryRequest;
import com.microservice.grpc.AddDiscountCategoryResponse;
import com.microservice.grpc.DiscountCategoryServiceGrpc;
import com.microservice.grpc.ResponseStatus;
import io.grpc.stub.StreamObserver;
import lombok.RequiredArgsConstructor;
import net.devh.boot.grpc.server.service.GrpcService;

@GrpcService
@RequiredArgsConstructor
public class DiscountCategoryGrpcServiceImpl extends DiscountCategoryServiceGrpc.DiscountCategoryServiceImplBase {
    private final DiscountProcessService discountProcessService;

    @Override
    public void addDiscountCategory(AddDiscountCategoryRequest request, StreamObserver<AddDiscountCategoryResponse> responseObserver) {

        AddDiscountCategoryResp addDiscountCategoryResp =
                discountProcessService.addDiscountCategoryList(GrpcMapper.INSTANCE.grpcAddDiscountCategoryRequestToAddDiscountCategoryRequest(request));

        responseObserver.onNext(AddDiscountCategoryResponse.newBuilder()
                .setResponse(ResponseStatus.newBuilder()
                        .setStatusCode(addDiscountCategoryResp.isResult())
                        .setMessage(addDiscountCategoryResp.getResultExp())
                        .build())
                .build());
        responseObserver.onCompleted();
    }
}
